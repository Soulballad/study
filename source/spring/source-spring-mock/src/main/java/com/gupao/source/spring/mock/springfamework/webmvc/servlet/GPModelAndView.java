package com.gupao.source.spring.mock.springfamework.webmvc.servlet;

import java.util.Map;

/**
 * @author Soulballad
 * @date 2019/4/29 21:26
 * @email soda931vzr@163.com
 * @description
 */
public class GPModelAndView {

    private String viewName;
    private Map<String, ?> model;

    public GPModelAndView(String viewName) {
        this.viewName = viewName;
    }

    public GPModelAndView(String viewName, Map<String, ?> model) {
        this.viewName = viewName;
        this.model = model;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, ?> getModel() {
        return model;
    }

    public void setModel(Map<String, ?> model) {
        this.model = model;
    }
}
