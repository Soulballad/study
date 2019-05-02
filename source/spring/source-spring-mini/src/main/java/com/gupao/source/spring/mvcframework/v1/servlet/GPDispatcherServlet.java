package com.gupao.source.spring.mvcframework.v1.servlet;

import com.gupao.source.spring.mvcframework.annotation.GPAutowired;
import com.gupao.source.spring.mvcframework.annotation.GPController;
import com.gupao.source.spring.mvcframework.annotation.GPRequestMapping;
import com.gupao.source.spring.mvcframework.annotation.GPService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Soulballad
 * @date 2019/3/25/0025 20:14
 * @email soda931vzr@163.com
 * @description
 */
public class GPDispatcherServlet extends HttpServlet {

    private Map<String, Object> ioc = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        String requestURI = req.getRequestURI();
        String contextPath = req.getContextPath();
        Map<String, String[]> parameterMap = req.getParameterMap();
        String url = requestURI.replace(contextPath, "");
        if(!ioc.containsKey(url)){resp.getWriter().write("404 Not Found!!!");}
        Method method = (Method) ioc.get(url);
        String simpleName = method.getDeclaringClass().getSimpleName();
        method.invoke(ioc.get(toLowerFirstCase(simpleName)), new Object[]{req, resp, parameterMap.get("name")[0]});
//        new Object[]{req,resp,params.get("name")[0]}
    }

    @Override
    public void init(ServletConfig config) throws ServletException {

        String contextConfigLocation = config.getInitParameter("contextConfigLocation");
        Properties properties = new Properties();
        try {
            String resourcePath = this.getClass().getResource("/").getPath();
            InputStream is = new FileInputStream(new File(resourcePath + contextConfigLocation));
            properties.load(is);
            String basePackage = properties.getProperty("basePackage");
            this.doScanner(basePackage);
            if(ioc.isEmpty()){return;}
            for (Map.Entry<String, Object> iocEntry : ioc.entrySet()) {
                String className = iocEntry.getKey();
                String baseUrl = "";
                Object object = iocEntry.getValue();
                Class<?> aClass = object.getClass();
                Field[] fields = aClass.getDeclaredFields();
                for (Field field : fields) {
                    if(!field.isAnnotationPresent(GPAutowired.class)){continue;}
                    GPAutowired autowired = field.getAnnotation(GPAutowired.class);
                    String value = autowired.value();
                    if("".equals(value)){value = field.getType().getSimpleName();}
                    field.setAccessible(true);
                    field.set(object, ioc.get(toLowerFirstCase(value)));
                }

                if (aClass.isAnnotationPresent(GPController.class)) {
                    if (aClass.isAnnotationPresent(GPRequestMapping.class)) {
                        GPRequestMapping gpRequestMapping = aClass.getAnnotation(GPRequestMapping.class);
                        baseUrl = gpRequestMapping.value();
                    }
                    Method[] methods = aClass.getMethods();
                    for (Method method : methods) {
                        if(!method.isAnnotationPresent(GPRequestMapping.class)){continue;}
                        GPRequestMapping methodMapping = method.getAnnotation(GPRequestMapping.class);
                        ioc.put(baseUrl + methodMapping.value(), method);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void doScanner(String basePackage) throws Exception {
        String packagePath = this.getClass().getClassLoader().getResource("/").getPath() + basePackage.replaceAll("\\.", "/");
        File classFile = new File(packagePath);
        for (File file : classFile.listFiles()) {
            String fileName = file.getName();
            if (file.isDirectory()) {
                this.doScanner(basePackage + "." + fileName);
            }else{
//                String classPath = file.getPath().replace(packagePath, "").replaceAll("/", ".");
                if (!fileName.endsWith(".class")) {
                    continue;
                }
                Class<?> clazz = Class.forName(basePackage + "." + fileName.replace(".class", ""));
                String key;

                if (clazz.isAnnotationPresent(GPController.class)) {
                    GPController gpController = clazz.getAnnotation(GPController.class);
                    key = "".equals(gpController.value()) ? clazz.getSimpleName() : gpController.value();
                }else if(clazz.isAnnotationPresent(GPService.class)) {
                    GPService gpService = clazz.getAnnotation(GPService.class);
                    key = "".equals(gpService.value()) ? clazz.getInterfaces()[0].getSimpleName() : gpService.value();
                }else{
                    continue;
                }

                ioc.put(toLowerFirstCase(key), clazz.newInstance());
            }
        }
    }

    private String toLowerFirstCase(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
}