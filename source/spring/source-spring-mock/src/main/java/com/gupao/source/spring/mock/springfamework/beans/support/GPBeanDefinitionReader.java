package com.gupao.source.spring.mock.springfamework.beans.support;

import com.gupao.source.spring.mock.springfamework.beans.config.GPBeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Soulballad
 * @date 2019/4/21 19:52
 * @email soda931vzr@163.com
 * @description
 */
public class GPBeanDefinitionReader {

    private Properties properties = new Properties();
    private List<String> registryBeanClasses = new ArrayList<>();

    private final String SCAN_PACKAGE = "scanPackage";

    public GPBeanDefinitionReader(String... configLocations) {
        //通过URL定位到具体的文件，然后转化为文件流
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(configLocations[0].replace("classpath:", ""));
        try {
            properties.load(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        doScanner(properties.getProperty(SCAN_PACKAGE));
    }

    private void doScanner(String scanPackage) {

        try {
            URL url = this.getClass().getResource("/" + scanPackage.replaceAll("\\.", "/"));
            File classPath = new File(url.getFile());
            File[] listFiles = classPath.listFiles();
            for (File file : listFiles) {
                if (file.isDirectory()) {
                    doScanner(scanPackage + "." + file.getName());
                } else {
                    if (!file.getName().endsWith(".class")) {
                        continue;
                    }
                    String className = scanPackage + "." + file.getName().replace(".class", "");
                    registryBeanClasses.add(className);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Properties getProperties() {
        return this.properties;
    }

    //把配置文件中扫描到的所有的配置信息转换为BeanDefinition对象
    public List<GPBeanDefinition> loadBeanDefinitions(String... locations) {

        List<GPBeanDefinition> resultList = new ArrayList<>();

        try {
            for (String className : registryBeanClasses) {

                Class<?> beanClass = Class.forName(className);
                //如果是一个接口，是不能实例化的
                //用它实现类来实例化
                if(beanClass.isInterface()) { continue; }

                //beanName有三种情况:
                //1、默认是类名首字母小写
                //2、自定义名字
                //3、接口注入
                resultList.add(doCreateBeanDefinition(toLowerFirstCase(beanClass.getSimpleName()),beanClass.getName()));
//                result.add(doCreateBeanDefinition(beanClass.getName(),beanClass.getName()));

                Class<?> [] interfaces = beanClass.getInterfaces();
                for (Class<?> i : interfaces) {
                    //如果是多个实现类，只能覆盖
                    //为什么？因为Spring没那么智能，就是这么傻
                    //这个时候，可以自定义名字
                    resultList.add(doCreateBeanDefinition(i.getName(),beanClass.getName()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultList;
    }

    // 把每一个配置信息解析成BeanDefinition
    private GPBeanDefinition doCreateBeanDefinition(String factoryBeanName, String beanClassName) {
        GPBeanDefinition beanDefinition = new GPBeanDefinition();
        beanDefinition.setBeanClassName(beanClassName);
        beanDefinition.setFactoryBeanName(factoryBeanName);
        return beanDefinition;
    }

    private String toLowerFirstCase(String simpleName) {
        char [] chars = simpleName.toCharArray();
        //之所以加，是因为大小写字母的ASCII码相差32，
        // 而且大写字母的ASCII码要小于小写字母的ASCII码
        //在Java中，对char做算学运算，实际上就是对ASCII码做算学运算
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
