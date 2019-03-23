package com.gupao.pattern.adapter.login.v2.adapters;

import com.gupao.pattern.adapter.login.ResultMsg;

/**
 * @author Soulballad
 * @date 2019/3/23/0023 11:17
 * @email soda931vzr@163.com
 * @description
 */
public interface LoginAdapter {

    public boolean support(LoginAdapter adapter);

    public ResultMsg login(String id, Object adapter);
}