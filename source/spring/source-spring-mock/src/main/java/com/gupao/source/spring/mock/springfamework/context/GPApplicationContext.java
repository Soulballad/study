package com.gupao.source.spring.mock.springfamework.context;

import com.gupao.source.spring.mock.springfamework.annotation.GPAutowired;
import com.gupao.source.spring.mock.springfamework.annotation.GPController;
import com.gupao.source.spring.mock.springfamework.annotation.GPService;
import com.gupao.source.spring.mock.springfamework.aop.GPAopProxy;
import com.gupao.source.spring.mock.springfamework.aop.GPCglibAopProxy;
import com.gupao.source.spring.mock.springfamework.aop.GPJdkDynamicAopProxy;
import com.gupao.source.spring.mock.springfamework.aop.config.GPAopConfig;
import com.gupao.source.spring.mock.springfamework.aop.support.GPAdvisedSupport;
import com.gupao.source.spring.mock.springfamework.beans.GPBeanWrapper;
import com.gupao.source.spring.mock.springfamework.beans.config.GPBeanDefinition;
import com.gupao.source.spring.mock.springfamework.beans.config.GPBeanPostProcessor;
import com.gupao.source.spring.mock.springfamework.beans.factory.GPBeanFactory;
import com.gupao.source.spring.mock.springfamework.beans.support.DefaultListableBeanFactory;
import com.gupao.source.spring.mock.springfamework.beans.support.GPBeanDefinitionReader;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Soulballad
 * @date 2019/4/21 16:22
 * @email soda931vzr@163.com
 * @description
 */
public class GPApplicationContext extends DefaultListableBeanFactory implements GPBeanFactory {

    private String[] configLocations;
    private GPBeanDefinitionReader reader;

    //单例的IOC容器
    private Map<String, Object> singleObjects = new ConcurrentHashMap<>();
    //通用的IOC容器
    private Map<String, GPBeanWrapper> factoryBeanInstanceCache = new ConcurrentHashMap<>();

    public GPApplicationContext(String... configLocations) {
        this.configLocations = configLocations;
        try {
            this.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refresh() throws Exception {
        //1. 定位，定位配置文件
        reader = new GPBeanDefinitionReader(this.configLocations);

        //2.加载配置文件，扫描相关的类，封装成BeanDefinition
        List<GPBeanDefinition> beanDefinitions = reader.loadBeanDefinitions(configLocations);

        //3.注册，把配置信息放到容器里面（伪IOC容器）
        doRegisterBeanDefinition(beanDefinitions);

        //4.把不是延时加载的类，进行初始化
        doAutowired();
    }

    private void doAutowired() {

        for (Map.Entry<String, GPBeanDefinition> beanDefinitionEntry : super.beanDefinitionMap.entrySet()) {
            String beanName = beanDefinitionEntry.getKey();
            if(!beanDefinitionEntry.getValue().isLazyInit()) {
                try {
                    getBean(beanName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void doRegisterBeanDefinition(List<GPBeanDefinition> beanDefinitions) throws Exception {

        for (GPBeanDefinition beanDefinition: beanDefinitions) {
            if(super.beanDefinitionMap.containsKey(beanDefinition.getFactoryBeanName())){
                throw new Exception("The “" + beanDefinition.getFactoryBeanName() + "” is exists!!");
            }
            super.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(),beanDefinition);
        }
    }
    public Object getBean(Class<?> beanClass) throws Exception {
        return getBean(beanClass.getName());
    }

    @Override
    public Object getBean(String beanName) {

        //1. 初始化
        Object instance = instantiateBean(beanName, this.beanDefinitionMap.get(beanName));

        GPBeanWrapper beanWrapper = new GPBeanWrapper(instance);

        GPBeanPostProcessor postProcessor = new GPBeanPostProcessor();

        postProcessor.postProcessBeforeInitialization(instance, beanName);

        // 循环依赖
        //2. 把BeanWrapper存到IOC容器中
        this.factoryBeanInstanceCache.put(beanName, beanWrapper);

        postProcessor.postProcessAfterInitialization(instance, beanName);

        //3. 注入
        populateBean(beanName, new GPBeanDefinition(), beanWrapper);

        return this.factoryBeanInstanceCache.get(beanName).getWrappedInstance();
    }

    private void populateBean(String beanName, GPBeanDefinition beanDefinition, GPBeanWrapper beanWrapper) {

        Object instance = beanWrapper.getWrappedInstance();

        //判断只有加了注解的类，才执行依赖注入
        Class<?> wrappedClass = beanWrapper.getWrappedClass();
        if (!(wrappedClass.isAnnotationPresent(GPController.class) || wrappedClass.isAnnotationPresent(GPService.class))) {
            return;
        }

        //获得所有的fields
        Field[] fields = wrappedClass.getDeclaredFields();
        for (Field field : fields) {

            if (!field.isAnnotationPresent(GPAutowired.class)) {
                continue;
            }

            GPAutowired autowired = field.getAnnotation(GPAutowired.class);
            String autowiredBeanName = autowired.value().trim();
            if ("".equals(autowiredBeanName)) {
                autowiredBeanName = field.getType().getName();
            }

            field.setAccessible(true);

            try {
                GPBeanWrapper wrapper = this.factoryBeanInstanceCache.get(autowiredBeanName);
                if (null == wrapper) {
                    continue;
                }

                field.set(instance, wrapper.getWrappedInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private Object instantiateBean(String beanName, GPBeanDefinition beanDefinition) {

        //1. 拿到要实例化的对象的类名
        String className = beanDefinition.getBeanClassName();

        //2. 反射实例化，得到一个对象
        Object instance = null;
        try {
            if (this.singleObjects.containsKey(className)) {
                instance = this.singleObjects.get(className);
            } else {
                Class<?> clazz = Class.forName(className);
                instance = clazz.newInstance();

                GPAdvisedSupport config = instantionAopConfig(beanDefinition);
                config.setTarget(instance);
                config.setTargetClass(clazz);

                //如果符合PointCut规则，创建代理对象
                if (config.pointCutMatch()) {
                    instance = createProxy(config).getProxy();
                }

                //如果是单例，保存到singleObjects中
                this.singleObjects.put(className, instance);
                this.singleObjects.put(beanDefinition.getFactoryBeanName(), instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //3. 把这个对象封装到BeanWrapper中
        //singleObjects
        //factoryBeanInstanceCache
//        GPBeanWrapper beanWrapper = new GPBeanWrapper(instance);

        //4. 把BeanWrapper存到IOC容器中
        return instance;
    }

    private GPAopProxy createProxy(GPAdvisedSupport config) {

        Class<?> targetClass = config.getTargetClass();
        if (targetClass.getInterfaces().length > 0) {
            return new GPJdkDynamicAopProxy(config);
        }

        return new GPCglibAopProxy(config);
    }

    private GPAdvisedSupport instantionAopConfig(GPBeanDefinition beanDefinition) {

        GPAopConfig config = new GPAopConfig();
        config.setPointCut(this.reader.getProperties().getProperty("pointCut"));
        config.setAspectBefore(this.reader.getProperties().getProperty("aspectBefore"));
        config.setAspectAfter(this.reader.getProperties().getProperty("aspectAfter"));
        config.setAspectClass(this.reader.getProperties().getProperty("aspectClass"));
        config.setAspectAfterThrow(this.reader.getProperties().getProperty("aspectAfterThrow"));
        config.setAspectAfterThrowingName(this.reader.getProperties().getProperty("aspectAfterThrowingName"));

        return new GPAdvisedSupport(config);
    }

    public String[] getBeanDefinitionNames() {

        return this.beanDefinitionMap.keySet().toArray(new String[this.beanDefinitionMap.size()]);
    }

    public int getBeanDefinitionCount() {

        return this.beanDefinitionMap.size();
    }

    public Properties getConfig() {
        return this.reader.getProperties();
    }
}
