package yusufcakal.com.stajtakip.presentation;

import android.content.Context;

import yusufcakal.com.stajtakip.User;
import yusufcakal.com.stajtakip.webservices.services.LoginService;

/**
 * Created by Yusuf on 11.05.2018.
 */

public class LoginServiceImpl{

    public void loginSucces(Context context, User user){
        LoginService loginService = new LoginService(context, user);
    }

}
