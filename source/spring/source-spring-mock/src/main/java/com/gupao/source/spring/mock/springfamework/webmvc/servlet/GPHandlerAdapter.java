package com.gupao.source.spring.mock.springfamework.webmvc.servlet;

import com.gupao.source.spring.mock.springfamework.annotation.GPRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Soulballad
 * @date 2019/4/29 21:24
 * @email soda931vzr@163.com
 * @description
 */
public class GPHandlerAdapter {

    public boolean supports(Object handler) {

        return handler instanceof GPHandlerMapping;
    }

    public GPModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws InvocationTargetException, IllegalAccessException {

        GPHandlerMapping handlerMapping = (GPHandlerMapping) handler;
        Map<String, Integer> paramterIndexMap = new HashMap<>();
        Map<String, Class<?>> parameterTypeMap = new HashMap<>();

        Method method = handlerMapping.getMethod();

        Annotation[][] pa = method.getParameterAnnotations();
        for (int i = 0; i < pa.length; i++) {
            for (Annotation annotation : pa[i]) {
                if (annotation instanceof GPRequestParam) {
                    String paramName = ((GPRequestParam) annotation).value();
                    if (!"".equals(paramName)) {
                        paramterIndexMap.put(paramName, i);
                    }
                }
            }
        }

        Class<?> [] paramsTypes = method.getParameterTypes();
        for (int i = 0; i < paramsTypes.length ; i ++) {
            Class<?> type = paramsTypes[i];
            if(type == HttpServletRequest.class ||
                    type == HttpServletResponse.class){
                paramterIndexMap.put(type.getName(),i);
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

        Map<String, String[]> parameterMap = request.getParameterMap();

        Object[] parameterValues = new Object[parameters.length];

        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String paramName = entry.getKey();
            String[] values = entry.getValue();
            if (paramterIndexMap.containsKey(paramName)) {
                Integer index = paramterIndexMap.get(paramName);
                parameterValues[index] = parseObject(Arrays.toString(values).replaceAll("\\[|\\]", "").replaceAll("\\s", ""), parameterTypeMap.get(paramName));
            }
        }

        if (paramterIndexMap.containsKey(HttpServletRequest.class.getName())) {
            Integer reqIndex = paramterIndexMap.get(HttpServletRequest.class.getName());
            parameterValues[reqIndex] = request;
        }

        if (paramterIndexMap.containsKey(HttpServletResponse.class.getName())) {
            Integer respIndex = paramterIndexMap.get(HttpServletResponse.class.getName());
            parameterValues[respIndex] = response;
        }

        Object result = method.invoke(handlerMapping.getController(), parameterValues);

        if (null == result || result instanceof Void) {
            return null;
        }

        if (handlerMapping.getMethod().getReturnType() == GPModelAndView.class) {
            return (GPModelAndView) result;
        }

        return null;
    }

    private Object parseObject(String paramValue, Class<?> paramType) {

        if (Integer.class == paramType) {
            return Integer.valueOf(paramValue);
        } else if (String.class == paramType) {
            return paramValue;
        } else if (Double.class == paramType) {
            return Double.valueOf(paramValue);
        }
        return null;
    }
}
