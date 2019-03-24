package com.gupao.pattern.adapter.login.v1;

import com.gupao.pattern.adapter.login.Member;
import com.gupao.pattern.adapter.login.ResultMsg;

/**
 * @author Soulballad
 * @date 2019/3/23/0023 11:10
 * @email soda931vzr@163.com
 * @description
 */
public class SigninService {

    public ResultMsg login(String username, String password) {
        return null;
    }

    public ResultMsg register(String username, String password) {
        return new ResultMsg(200, "注册成功", new Member());
    }
}