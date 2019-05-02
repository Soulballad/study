package com.gupao.source.spring.mock.springfamework.webmvc.servlet;

import java.io.File;
import java.util.Locale;

/**
 * @author Soulballad
 * @date 2019/4/29 21:49
 * @email soda931vzr@163.com
 * @description
 */
public class GPViewResolver {

    private final String DEFAULT_TEMPLATE_SUFFIX = ".html";

    private File templateRootDir;

    public GPViewResolver(String templateRoot) {

        String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();
        templateRootDir = new File(templateRootPath);
    }

    public GPView resolveViewName(String viewName, Locale locale) {

        if (null == viewName || "".equals(viewName.trim())) {
            return null;
        }

        viewName = viewName.endsWith(DEFAULT_TEMPLATE_SUFFIX) ? viewName : viewName + DEFAULT_TEMPLATE_SUFFIX;
        File tempalteFile = new File((templateRootDir.getPath() + "/" + viewName).replaceAll("/+", "/"));

        return new GPView(tempalteFile);
    }
}
