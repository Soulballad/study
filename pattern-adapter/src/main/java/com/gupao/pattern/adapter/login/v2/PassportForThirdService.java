package com.gupao.pattern.adapter.login.v2;

import com.gupao.pattern.adapter.login.ResultMsg;
import com.gupao.pattern.adapter.login.v1.SigninService;
import com.gupao.pattern.adapter.login.v2.adapters.LoginAdapter;
import com.gupao.pattern.adapter.login.v2.adapters.LoginForQQAdapter;
import com.gupao.pattern.adapter.login.v2.adapters.LoginForTelAdapter;
import com.gupao.pattern.adapter.login.v2.adapters.LoginForTokenAdapter;
import com.gupao.pattern.adapter.login.v2.adapters.LoginForWechatAdapter;

/**
 * @author Soulballad
 * @date 2019/3/23/0023 11:21
 * @email soda931vzr@163.com
 * @description
 */
public class PassportForThirdService extends SigninService implements IPassportForThird {

    @Override
    public ResultMsg loginForQQ(String id) {
        return loginProcess(id, new LoginForQQAdapter());
    }

    @Override
    public ResultMsg loginForWechat(String id) {
        return processLogin(id, LoginForWechatAdapter.class);
    }

    @Override
    public ResultMsg loginForToken(String token) {
        return processLogin(token, LoginForTokenAdapter.class);
    }

    @Override
    public ResultMsg loginForTelphone(String telphone, String code) {
        return processLogin(telphone, LoginForTelAdapter.class);
    }

    @Override
    public ResultMsg loginForRegist(String username, String passport) {
        super.register(username,passport);
        return super.login(username,passport);
    }

    private ResultMsg loginProcess(String id, LoginAdapter adapter) {
        if (adapter.support(adapter)) {
            return adapter.login(id, adapter);
        }
        return null;
    }

    private ResultMsg processLogin(String key,Class<? extends LoginAdapter> clazz){
        try{
            //适配器不一定要实现接口
            LoginAdapter adapter = clazz.newInstance();

            //判断传过来的适配器是否能处理指定的逻辑
            if(adapter.support(adapter)){
                return adapter.login(key,adapter);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}