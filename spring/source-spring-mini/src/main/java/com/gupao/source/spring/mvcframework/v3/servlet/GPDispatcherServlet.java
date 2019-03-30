package com.gupao.source.spring.mvcframework.v3.servlet;

import com.gupao.source.spring.mvcframework.annotation.GPAutowired;
import com.gupao.source.spring.mvcframework.annotation.GPController;
import com.gupao.source.spring.mvcframework.annotation.GPRequestMapping;
import com.gupao.source.spring.mvcframework.annotation.GPService;
import com.gupao.source.spring.mvcframework.v3.Handler;

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
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Soulballad
 * @date 2019/3/25/0025 20:14
 * @email soda931vzr@163.com
 * @description
 */
public class GPDispatcherServlet extends HttpServlet {

    private List<String> classNames = new ArrayList<>();

    // ioc容器，对象初始化
    private Map<String, Object> ioc = new HashMap<>();

//    private Map<String, Method> handlerMapping = new HashMap<String, Method>();

    private Properties properties = new Properties();

    private List<Handler> handlerMapping = new ArrayList<>();

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

        Handler handler = getHandler(req);

        if (null == handler) {
            resp.getWriter().write("404 Not Found!!!");
            return;
        }

        Map<String, String[]> parameterMap = req.getParameterMap();

        Method method = handler.getMethod();
        Map<String, Integer> paramterIndexMap = handler.getParameterIndexMap();
        Map<String, Class<?>> parameterTypeMap = handler.getParameterTypeMap();

        Parameter[] parameters = method.getParameters();
        Object[] paramterValues = new Object[parameters.length];

        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String paramName = entry.getKey();
            String[] values = entry.getValue();
            if (paramterIndexMap.containsKey(paramName)) {
                Integer index = paramterIndexMap.get(paramName);
                paramterValues[index] = parseObject(Arrays.toString(values).replaceAll("\\[|\\]", "").replaceAll("\\s", ""), parameterTypeMap.get(paramName));
            }
        }

        Integer reqIndex = paramterIndexMap.get(HttpServletRequest.class.getName());
        paramterValues[reqIndex] = req;
        Integer respIndex = paramterIndexMap.get(HttpServletResponse.class.getName());
        paramterValues[respIndex] = resp;

        method.invoke(handler.getController(), paramterValues);
    }

    private Handler getHandler(HttpServletRequest req) {

        String requestURI = req.getRequestURI();
        String contextPath = req.getContextPath();
        String url = requestURI.replace(contextPath, "").replaceAll("/+", "/");

        for (Handler handler : handlerMapping) {
            Matcher matcher = handler.getPattern().matcher(url);
            if (!matcher.matches()) {
                continue;
            }
            return handler;
        }

        return null;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {

        try {
            this.doLoadConfig(config.getInitParameter("contextConfigLocation"));

            this.doScanner(properties.getProperty("basePackage"));

            this.doInstance();

            if (ioc.isEmpty()) {
                return;
            }

            this.doAutowired();

            this.initHandlerMapping();

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    private void initHandlerMapping() {

        for (Map.Entry<String, Object> iocEntry : ioc.entrySet()) {

            String baseUrl = "";
            Object object = iocEntry.getValue();
            Class<?> aClass = object.getClass();

            if (aClass.isAnnotationPresent(GPController.class)) {
                if (aClass.isAnnotationPresent(GPRequestMapping.class)) {
                    GPRequestMapping gpRequestMapping = aClass.getAnnotation(GPRequestMapping.class);
                    baseUrl = gpRequestMapping.value();
                }
                Method[] methods = aClass.getMethods();
                for (Method method : methods) {
                    if (!method.isAnnotationPresent(GPRequestMapping.class)) {
                        continue;
                    }
                    GPRequestMapping methodMapping = method.getAnnotation(GPRequestMapping.class);
                    String regex = baseUrl + methodMapping.value().replaceAll("/+", "/");
                    Pattern pattern = Pattern.compile(regex);
                    handlerMapping.add(new Handler(object, method, pattern));
                }
            }
        }

    }

    private void doAutowired() throws IllegalAccessException {

        for (Map.Entry<String, Object> iocEntry : ioc.entrySet()) {

            Object object = iocEntry.getValue();
            Class<?> aClass = object.getClass();
            Field[] fields = aClass.getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAnnotationPresent(GPAutowired.class)) {
                    continue;
                }
                GPAutowired autowired = field.getAnnotation(GPAutowired.class);
                String value = autowired.value();
                if ("".equals(value)) {
                    value = field.getType().getSimpleName();
                }
                field.setAccessible(true);
                field.set(object, ioc.get(toLowerFirstCase(value)));
            }
        }
    }

    private void doInstance() throws Exception {

        if (classNames.isEmpty()) {
            return;
        }

        for (String className : classNames) {

            Class<?> clazz = Class.forName(className);
            String key;

            if (clazz.isAnnotationPresent(GPController.class)) {
                GPController gpController = clazz.getAnnotation(GPController.class);
                key = "".equals(gpController.value()) ? clazz.getSimpleName() : gpController.value();
                ioc.put(toLowerFirstCase(key), clazz.newInstance());

            } else if (clazz.isAnnotationPresent(GPService.class)) {
                GPService gpService = clazz.getAnnotation(GPService.class);
                key = gpService.value();
                if (!"".equals(key)) {
                    ioc.put(toLowerFirstCase(key), clazz.newInstance());
                    return;
                }
                Class<?>[] interfaces = clazz.getInterfaces();
                for (Class<?> inter : interfaces) {
                    ioc.put(toLowerFirstCase(inter.getSimpleName()), clazz.newInstance());
                }
            }
        }
    }

    private void doScanner(String basePackage) throws Exception {

        String packagePath = this.getClass().getClassLoader().getResource("/").getPath() + basePackage.replaceAll("\\.", "/");
        File classFile = new File(packagePath);
        for (File file : classFile.listFiles()) {
            String fileName = file.getName();
            if (file.isDirectory()) {
                this.doScanner(basePackage + "." + fileName);
            } else {
                if (!fileName.endsWith(".class")) {
                    continue;
                }

                String className = basePackage + "." + fileName.replace(".class", "");
                classNames.add(className);
            }
        }
    }

    private void doLoadConfig(String contextConfigLocation) throws IOException {

        String resourcePath = this.getClass().getClassLoader().getResource("/").getPath();
        InputStream is = new FileInputStream(new File(resourcePath + contextConfigLocation));
        properties.load(is);

    }

    private String toLowerFirstCase(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
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