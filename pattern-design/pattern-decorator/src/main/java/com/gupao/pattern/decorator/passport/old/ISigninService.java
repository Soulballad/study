package com.gupao.pattern.decorator.passport.old;

/**
 * @author Soulballad
 * @date 2019/3/23/0023 14:05
 * @email soda931vzr@163.com
 * @description
 */
public interface ISigninService {

    public ResultMsg login(String username, String password);

    public ResultMsg register(String username, String password);
}
