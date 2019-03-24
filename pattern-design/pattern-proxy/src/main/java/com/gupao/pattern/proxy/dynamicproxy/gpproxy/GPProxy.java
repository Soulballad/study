package com.gupao.pattern.proxy.dynamicproxy.gpproxy;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Soulballad
 * @date 2019/3/18/0018 21:50
 * @email soda931vzr@163.com
 * @description
 */
public class GPProxy {

    private static final String Ln = "\r\n";

    public static Object newProxyInstance(GPClassLoader classLoader, Class<?>[] interfaces, GPInvocationHandler h) {
        try {
            //1、动态生成源代码.java文件
            String src = generateSrc(interfaces);

//           System.out.println(src);
            //2、Java文件输出磁盘
            String filePath = GPProxy.class.getResource("").getPath();
//           System.out.println(filePath);
            File f = new File(filePath + "$Proxy0.java");
            FileWriter fw = new FileWriter(f);
            fw.write(src);
            fw.flush();
            fw.close();

            //3、把生成的.java文件编译成.class文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manage = compiler.getStandardFileManager(null,null,null);
            Iterable iterable = manage.getJavaFileObjects(f);

            JavaCompiler.CompilationTask task = compiler.getTask(null,manage,null,null,null,iterable);
            task.call();
            manage.close();

            //4、编译生成的.class文件加载到JVM中来
            Class proxyClass =  classLoader.findClass("$Proxy0");
            Constructor c = proxyClass.getConstructor(GPInvocationHandler.class);
            f.delete();

            //5、返回字节码重组以后的新的代理对象
            return c.newInstance(h);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String generateSrc(Class<?>[] interfaces) {

        StringBuffer sb = new StringBuffer();

        sb.append("package com.gupao.pattern.proxy.dynamicproxy.gpproxy;").append(Ln);
        sb.append("import com.gupao.pattern.proxy.dynamicproxy.jdkproxy.Person;" + Ln);
        sb.append("import java.lang.reflect.*;").append(Ln);
        sb.append("public class $Proxy0 implements " + interfaces[0].getName() + "{" + Ln);
            sb.append("GPInvocationHandler h;" + Ln);
            sb.append("public $Proxy0(GPInvocationHandler h){" + Ln);
            sb.append("this.h = h;" + Ln);
            sb.append("}" + Ln);

        for (Method method : interfaces[0].getMethods()) {

            Class<?>[] params = method.getParameterTypes();
            StringBuffer paramNames = new StringBuffer();
            StringBuffer paramValuess = new StringBuffer();
            StringBuffer paramClasses = new StringBuffer();

            for (int i = 0; i < params.length; i++) {
                Class<?> param = params[i];
                String type = param.getName();
                String paramName = toLowerFirstCase(param.getSimpleName());
                paramNames.append(type + " " + paramName);
                paramValuess.append(paramName);
                paramClasses.append(param.getName() + ".class");
                if (i > 0 && i < params.length - 1) {
                    paramNames.append(",");
                    paramValuess.append(",");
                    paramClasses.append(",");
                }
            }

            sb.append("public " + method.getReturnType().getName() + " " + method.getName() + "(" + paramNames.toString() + ") {" + Ln);
                sb.append("try{" + Ln);
                    sb.append("Method m = " + interfaces[0].getName() + ".class.getMethod(\"" + method.getName() + "\",new Class[]{" + paramClasses.toString() + "});" + Ln);
                    sb.append((hasReturnValue(method.getReturnType()) ? "return " : "") + getCaseCode("this.h.invoke(this,m,new Object[]{" + paramValuess + "})",method.getReturnType()) + ";" + Ln);
                    sb.append("}catch(Error _ex) { }");
                    sb.append("catch(Throwable e){" + Ln);
                    sb.append("throw new UndeclaredThrowableException(e);" + Ln);
                sb.append("}");
                sb.append(getReturnEmptyCode(method.getReturnType()));
            sb.append("}");
        }
        sb.append("}");
        return sb.toString();
    }

    private static Map<Class,Class> mappings = new HashMap<Class, Class>();
    static {
        mappings.put(int.class,Integer.class);
    }

    private static String getReturnEmptyCode(Class<?> returnClass){
        if(mappings.containsKey(returnClass)){
            return "return 0;";
        }else if(returnClass == void.class){
            return "";
        }else {
            return "return null;";
        }
    }

    private static String getCaseCode(String code,Class<?> returnClass){
        if(mappings.containsKey(returnClass)){
            return "((" + mappings.get(returnClass).getName() +  ")" + code + ")." + returnClass.getSimpleName() + "Value()";
        }
        return code;
    }

    private static boolean hasReturnValue(Class<?> clazz){
        return clazz != void.class;
    }

    private static String toLowerFirstCase(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
}