package com.gupao.pattern.proxy.dynamicproxy.gpproxy;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author Soulballad
 * @date 2019/3/18/0018 21:50
 * @email soda931vzr@163.com
 * @description
 */
public class GPClassLoader extends ClassLoader {

    private File classPathFile;

    public GPClassLoader() {
        String path = GPClassLoader.class.getResource("").getPath();
        this.classPathFile = new File(path);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

//        String className = GPClassLoader.class.getPackage() + "." + name;
        String className = GPClassLoader.class.getPackage().getName() + "." + name;
        if (null != classPathFile) {

            File classFile = new File(classPathFile,name.replaceAll("\\.","/") + ".class");
//            File classFile = new File(this.classPathFile, className.replace("\\.", "/") + ".class");
            if (classFile.exists()) {

                try {
                    FileInputStream fis = new FileInputStream(classFile);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    byte[] buff = new byte[1024];
                    int len;
                    while ((len = fis.read(buff)) != -1) {
                        bos.write(buff, 0, len);
                    }
                    return defineClass(className, bos.toByteArray(), 0, bos.size());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}