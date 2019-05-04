package com.gupao.source.spring.orm.framework;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Soulballad
 * @date 2019/5/4 13:52
 * @email soda931vzr@163.com
 * @description
 */
public class QueryRule {

    public static final String AND = "and";
    public static final String OR = "or";
    public static final String LIKE = "like";
    public static final String EQUAL = "=";

    private List<Rule> ruleList;

    public QueryRule() {
        this.ruleList = new ArrayList<>();
    }

    public List<Rule> getRuleList() {
        return ruleList;
    }

    private void add(Rule rule) {
        this.ruleList.add(rule);
    }

    public void andEqual(String propertyName, String propertyValue) {
        add(new Rule(propertyName, propertyValue, AND, EQUAL));
    }

    public void andLike(String propertyName, String propertyValue) {
        add(new Rule(propertyName, propertyValue, AND, LIKE));
    }

    public void orEqual(String propertyName, String propertyValue) {
        add(new Rule(propertyName, propertyValue, OR, EQUAL));
    }

    public void orLike(String propertyName, String propertyValue) {
        add(new Rule(propertyName, propertyValue, OR, LIKE));
    }

    class Rule{

        private String propertyName;
        private String propertyValue;
        private String andOr;
        private String likeEqual;

        public Rule(String propertyName, String propertyValue, String andOr, String likeEqual) {
            this.propertyName = propertyName;
            this.propertyValue = propertyValue;
            this.andOr = andOr;
            this.likeEqual = likeEqual;
        }

        public String getPropertyName() {
            return propertyName;
        }

        public String getPropertyValue() {
            return propertyValue;
        }

        public String getAndOr() {
            return andOr;
        }

        public String getLikeEqual() {
            return likeEqual;
        }
    }
}
