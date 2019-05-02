package com.gupao.source.spring.mock.springfamework.aop.support;

import com.gupao.source.spring.mock.springfamework.aop.aspect.GPAfterReturnAdviceInterceptor;
import com.gupao.source.spring.mock.springfamework.aop.aspect.GPAfterThrowingAdviceInterceptor;
import com.gupao.source.spring.mock.springfamework.aop.aspect.GPMethodBeforeAdviceInterceptor;
import com.gupao.source.spring.mock.springfamework.aop.config.GPAopConfig;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Soulballad
 * @date 2019/5/2 12:52
 * @email soda931vzr@163.com
 * @description
 */
public class GPAdvisedSupport {

    private Class<?> targetClass;

    private Object target;

    private GPAopConfig config;

    private Pattern pointCutClassPattern;

    private transient Map<Method, List<Object>> methodCache;

    public GPAdvisedSupport(GPAopConfig config) {
        this.config = config;
    }

    public Class<?> getTargetClass() {
        return this.targetClass;
    }

    public Object getTarget() {
        return this.target;
    }

    public List<Object> getInterceptorsAndDynamicInterceptionAdvice(Method method, Class<?> targetClass) throws Exception{
        List<Object> cached = methodCache.get(method);
        if(cached == null){
            Method m = targetClass.getMethod(method.getName(),method.getParameterTypes());

            cached = methodCache.get(m);

            //底层逻辑，对代理方法进行一个兼容处理
            this.methodCache.put(m,cached);
        }

        return cached;
    }
    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
        parse();
    }



    private void parse() {
        String pointCut = config.getPointCut()
                .replaceAll("\\.", "\\\\.")
                .replaceAll("\\\\.\\*", ".*")
                .replaceAll("\\(", "\\\\(")
                .replaceAll("\\)", "\\\\)");

        String pointCutForClassRegex = pointCut.substring(0,pointCut.lastIndexOf("\\(") - 4);

        pointCutClassPattern = Pattern.compile("class " + pointCutForClassRegex.substring(
                pointCutForClassRegex.lastIndexOf(" ") + 1));

        try {

            methodCache = new HashMap<>();

            Pattern pattern = Pattern.compile(pointCut);
            Class<?> aspectClass = Class.forName(this.config.getAspectClass());
            Method[] methods = this.targetClass.getMethods();

            Map<String, Method> aspectMethodMap = new HashMap<>();

            for (Method method : aspectClass.getMethods()) {
                aspectMethodMap.put(method.getName(), method);
            }

            for (Method method : methods) {
                String methodStr = method.toString();
                if (methodStr.contains("throws")) {
                    methodStr = methodStr.substring(0, methodStr.lastIndexOf("throws")).trim();
                }
                Matcher matcher = pattern.matcher(methodStr);
                if (matcher.matches()) {
                    List<Object> advices = new LinkedList<>();
                    //把每一个方法包装成MethodInterceptor
                    //before
                    if (!(null == config.getAspectBefore() || "".equals(config.getAspectBefore()))) {
                        //创建一个Advice
                        advices.add(new GPMethodBeforeAdviceInterceptor(aspectMethodMap.get(config.getAspectBefore()), aspectClass.newInstance()));
                    }
                    //after
                    if (!(null == config.getAspectAfter() || "".equals(config.getAspectAfter()))) {
                        //创建一个Advice
                        advices.add(new GPAfterReturnAdviceInterceptor(aspectMethodMap.get(config.getAspectAfter()), aspectClass.newInstance()));
                    }
                    //afterThrow
                    if (!(null == config.getAspectAfterThrow() || "".equals(config.getAspectAfterThrow()))) {
                        //创建一个Advice
                        GPAfterThrowingAdviceInterceptor afterThrowingAdviceInterceptor = new GPAfterThrowingAdviceInterceptor(aspectMethodMap.get(config.getAspectAfterThrow()), aspectClass.newInstance());
                        afterThrowingAdviceInterceptor.setThrowName(config.getAspectAfterThrowingName());
                        advices.add(afterThrowingAdviceInterceptor);
                    }
                    methodCache.put(method, advices);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setTarget(Object target) {
        this.target = target;
    }

    public boolean pointCutMatch() {
        return pointCutClassPattern.matcher(this.targetClass.toString()).matches();
    }
}
