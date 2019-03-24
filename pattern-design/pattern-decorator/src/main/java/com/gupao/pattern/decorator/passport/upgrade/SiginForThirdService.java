package com.gupao.pattern.decorator.passport.upgrade;

import com.gupao.pattern.decorator.passport.old.ResultMsg;

/**
 * @author Soulballad
 * @date 2019/3/23/0023 15:50
 * @email soda931vzr@163.com
 * @description
 */
public class SiginForThirdService implements ISiginForThirdService {

    @Override
    public ResultMsg loginForQQ(String id) {
        return null;
    }

    @Override
    public ResultMsg loginForWechat(String id) {
        return null;
    }

    @Override
    public ResultMsg loginForToken(String token) {
        return null;
    }

    @Override
    public ResultMsg loginForTelphone(String telphone, String code) {
        return null;
    }

    @Override
    public ResultMsg loginForRegist(String username, String passport) {
        return null;
    }

    @Override
    public ResultMsg login(String username, String password) {
        return null;
    }

    @Override
    public ResultMsg register(String username, String password) {
        return null;
    }
}