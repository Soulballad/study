package com.gupao.source.spring.mvcframework.v3;

import com.gupao.source.spring.mvcframework.annotation.GPRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Soulballad
 * @date 2019/3/30/0030 15:56
 * @email soda931vzr@163.com
 * @description
 */
public class Handler {

    private Object controller;
    private Method method;
    private Pattern pattern;
    private Map<String, Integer> parameterIndexMap;
    private Map<String, Class<?>> parameterTypeMap;

    public Object getController() {
        return controller;
    }

    public Method getMethod() {
        return method;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public Map<String, Integer> getParameterIndexMap() {
        return parameterIndexMap;
    }

    public Map<String, Class<?>> getParameterTypeMap() {
        return parameterTypeMap;
    }

    public Handler(Object controller, Method method, Pattern pattern) {
        this.controller = controller;
        this.method = method;
        this.pattern = pattern;

        parameterIndexMap = new HashMap<>();
        parameterTypeMap = new HashMap<>();

        initParamMap(method);
    }

    private void initParamMap(Method method) {
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            for (int j = 0; j < parameterAnnotations[i].length; j++) {
                Annotation annotation = parameterAnnotations[i][j];
                if (annotation instanceof GPRequestParam) {
                    String value = ((GPRequestParam) annotation).value();
                    if (!"".equals(value)) {
                        parameterIndexMap.put(value, i);
                    }
                }
            }
        }

        Class<?>[] parameterTypes = method.getParameterTypes();
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> parameterType = parameterTypes[i];
            if (HttpServletRequest.class == parameterType || HttpServletResponse.class == parameterType) {
                parameterIndexMap.put(parameterType.getName(), i);
            }
        }

        Parameter[] parameters = method.getParameters();
        for (Parameter parameter : parameters) {
            if (!parameter.isAnnotationPresent(GPRequestParam.class)) {
                continue;
            }

            GPRequestParam annotation = parameter.getAnnotation(GPRequestParam.class);
            String value = annotation.value();
            if("".equals(value)){continue;}

            parameterTypeMap.put(value, parameter.getType());
        }
    }
}