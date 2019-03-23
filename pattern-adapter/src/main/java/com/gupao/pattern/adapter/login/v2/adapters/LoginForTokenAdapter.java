package com.gupao.pattern.adapter.login.v2.adapters;

import com.gupao.pattern.adapter.login.ResultMsg;

/**
 * @author Soulballad
 * @date 2019/3/23/0023 11:20
 * @email soda931vzr@163.com
 * @description
 */
public class LoginForTokenAdapter implements LoginAdapter {

    @Override
    public boolean support(LoginAdapter adapter) {
        return adapter instanceof LoginForTokenAdapter;
    }

    @Override
    public ResultMsg login(String id, Object adapter) {
        return null;
    }
}