package com.gupao.source.spring.mock.springfamework.webmvc.servlet;

import com.gupao.source.spring.mock.springfamework.annotation.GPController;
import com.gupao.source.spring.mock.springfamework.annotation.GPRequestMapping;
import com.gupao.source.spring.mock.springfamework.context.GPApplicationContext;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Soulballad
 * @date 2019/4/29 20:50
 * @email soda931vzr@163.com
 * @description
 */
@Slf4j
public class GPDispatchServlet extends HttpServlet {

    private final String CONTEXT_CONFIGURATION_LOCATION = "contextConfigLocation";

    private GPApplicationContext context;

    private List<GPHandlerMapping> handlerMappings = new ArrayList<>();

    private Map<GPHandlerMapping, GPHandlerAdapter> handlerAdapters = new HashMap<>();

    private List<GPViewResolver> viewResolvers = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            this.doDispatch(req, resp);
        } catch (Exception e) {
            processDispatchResult(req, resp, new GPModelAndView("500"));
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws InvocationTargetException, IllegalAccessException, IOException {

        //1、通过request中url获取一个HandlerMapping
        GPHandlerMapping handlerMapping = getHandler(req);

        if (null == handlerMapping) {

            processDispatchResult(req, resp, new GPModelAndView("404"));
            //404
            return;
        }

        //2、准备调用前的参数
        GPHandlerAdapter ha = getHandlerAdapter(handlerMapping);

        if (null == ha) {
            return;
        }

        //3、调用方法
        GPModelAndView mv = ha.handle(req, resp, handlerMapping);

        processDispatchResult(req, resp, mv);
    }

    private void processDispatchResult(HttpServletRequest req, HttpServletResponse resp, GPModelAndView mv) throws IOException {
        //把ModelAndView转成Html、outputstream、json、freemarker、velocity
        if (null == mv) {
            return;
        }

        if (this.viewResolvers.isEmpty()) {
            return;
        }

        for (GPViewResolver viewResolver : this.viewResolvers) {
            GPView view = viewResolver.resolveViewName(mv.getViewName(), null);
            view.render(mv.getModel(), req, resp);
            return;
        }
    }

    private GPHandlerAdapter getHandlerAdapter(GPHandlerMapping handlerMapping) {

        if (this.handlerAdapters.isEmpty()) {
            return null;
        }

        GPHandlerAdapter adapter = this.handlerAdapters.get(handlerMapping);

        if (!adapter.supports(handlerMapping)) {
            return null;
        }

        return adapter;
    }

    private GPHandlerMapping getHandler(HttpServletRequest req) {

        if (this.handlerMappings.isEmpty()) {
            return null;
        }
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+", "/");

        for (GPHandlerMapping handlerMapping : this.handlerMappings) {
            Pattern pattern = handlerMapping.getPattern();
            Matcher matcher = pattern.matcher(url);
            if (!matcher.matches()) {
                continue;
            }
            return handlerMapping;
        }

        return null;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {

        //1、初始化ApplicationContext
        context = new GPApplicationContext(config.getInitParameter(CONTEXT_CONFIGURATION_LOCATION));

        //2、初始化九大组件
        initStrategies(context);
    }

    protected void initStrategies(GPApplicationContext context) {

        initMutlipartResolver(context);

        initLocaleResolver(context);

        initThemeResolver(context);
        //必须实现
        initHandlerMappings(context);
        //必须实现
        initHandlerAdapters(context);

        initHandlerExceptionResolver(context);

        initRequestToViewNameTranslator(context);
        //必须实现
        initViewResolver(context);

        initFlashMapManager(context);
    }

    private void initFlashMapManager(GPApplicationContext context) {
    }

    private void initViewResolver(GPApplicationContext context) {
        String templateRoot = context.getConfig().getProperty("templateRoot");
        String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();
        File templateRootDir = new File(templateRootPath);
        for (File template : templateRootDir.listFiles()) {
            this.viewResolvers.add(new GPViewResolver(templateRoot));
        }
    }

    private void initRequestToViewNameTranslator(GPApplicationContext context) {
    }

    private void initHandlerExceptionResolver(GPApplicationContext context) {
    }

    private void initHandlerAdapters(GPApplicationContext context) {

        for (GPHandlerMapping handlerMapping : this.handlerMappings) {
            Pattern pattern = handlerMapping.getPattern();
            this.handlerAdapters.put(handlerMapping, new GPHandlerAdapter());
        }
    }

    private void initHandlerMappings(GPApplicationContext context) {

        String[] beanDefinitionNames = context.getBeanDefinitionNames();

        for (String beanName : beanDefinitionNames) {
            Object bean = context.getBean(beanName);
            Class<?> beanClass = bean.getClass();
            if (!beanClass.isAnnotationPresent(GPController.class)) {
                continue;
            }
            String baseUrl = "";
            if (beanClass.isAnnotationPresent(GPRequestMapping.class)) {
                GPRequestMapping requestMapping = beanClass.getAnnotation(GPRequestMapping.class);
                baseUrl = requestMapping.value();
            }

            Method[] methods = beanClass.getMethods();
            for (Method method : methods) {
                if (!method.isAnnotationPresent(GPRequestMapping.class)) {
                    continue;
                }

                GPRequestMapping requestMapping = method.getAnnotation(GPRequestMapping.class);
                String url = ("/" + baseUrl + "/" + requestMapping.value().replaceAll("\\*", ".*")).replaceAll("/+", "/");

                handlerMappings.add(new GPHandlerMapping(bean, method, Pattern.compile(url)));
                log.info("Mapped " + url + "," + method);
            }
        }
    }

    private void initThemeResolver(GPApplicationContext context) {
    }

    private void initLocaleResolver(GPApplicationContext context) {

    }

    private void initMutlipartResolver(GPApplicationContext context) {

    }
}
