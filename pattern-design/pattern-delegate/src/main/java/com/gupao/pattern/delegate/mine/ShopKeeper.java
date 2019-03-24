package com.gupao.pattern.delegate.mine;

/**
 * @author Soulballad
 * @date 2019/3/21/0021 21:01
 * @email soda931vzr@163.com
 * @description
 */
public class ShopKeeper implements IStuff {

    private String request;

    public ShopKeeper(String request) {
        this.request = request;
    }

    @Override
    public void work() {

        IStuff stuff;

        if ("买车".equals(this.request)) {
            stuff = new IntroduceStuff();
        } else if ("保养".equals(this.request)) {
            stuff = new PainterStuff();
        }else{
            throw new RuntimeException();
        }

        stuff.work();
    }
}