package com.gupao.adapter.login.v2;

import com.gupao.pattern.adapter.login.v2.IPassportForThird;
import com.gupao.pattern.adapter.login.v2.PassportForThirdService;

/**
 * @author Soulballad
 * @date 2019/3/23/0023 11:27
 * @email soda931vzr@163.com
 * @description
 */
public class PassportServiceTest {

    public static void main(String[] args) {

        IPassportForThird passport = new PassportForThirdService();
        passport.loginForToken("adfjasldf");
    }
}