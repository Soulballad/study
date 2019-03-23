package com.gupao.pattern.observer.classwork;

/**
 * @author Soulballad
 * @date 2019/3/23/0023 16:10
 * @email soda931vzr@163.com
 * @description
 */
public class GuavaQuestion {

    private String content;
    private String username;
    private GuavaGPer guavaGPer;

    public GuavaGPer getGuavaGPer() {
        return guavaGPer;
    }

    public void setGuavaGPer(GuavaGPer guavaGPer) {
        this.guavaGPer = guavaGPer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}