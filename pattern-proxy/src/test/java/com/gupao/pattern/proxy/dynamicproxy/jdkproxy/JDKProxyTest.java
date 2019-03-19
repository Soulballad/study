package com.gupao.pattern.proxy.dynamicproxy.jdkproxy;

import com.gupao.pattern.proxy.dynamicproxy.jdkproxy.Girl;
import com.gupao.pattern.proxy.dynamicproxy.jdkproxy.JDKMeipo;
import com.gupao.pattern.proxy.dynamicproxy.jdkproxy.Person;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Soulballad
 * @date 2019/3/18/0018 21:31
 * @email soda931vzr@163.com
 * @description
 */
public class JDKProxyTest {

    public static void main(String[] args) throws Exception {

        Object instance = new JDKMeipo().getInstance(new Girl());
        Method method = instance.getClass().getDeclaredMethod("findLove");
        method.invoke(instance);

        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{Person.class});
        FileOutputStream os = new FileOutputStream("D:\\Develop\\Record\\IdeaWork\\gupao\\gupao-root\\gupao-pattern\\pattern-proxy\\src\\main\\java\\com\\gupao\\pattern\\proxy\\dynamicproxy\\jdkproxy\\$Proxy0.class");
        os.write(bytes);
        os.close();
    }
}