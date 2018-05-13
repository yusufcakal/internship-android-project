package yusufcakal.com.stajtakip.presentation;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import yusufcakal.com.stajtakip.User;
import yusufcakal.com.stajtakip.webservices.interfaces.LoginListener;
import yusufcakal.com.stajtakip.webservices.services.LoginService;
import yusufcakal.com.stajtakip.webservices.util.SessionUtil;

/**
 * Created by Yusuf on 11.05.2018.
 */

public class LoginServiceImpl implements LoginListener{

    private Context context;

    public void loginSucces(Context context, User user){
        LoginListener loginListener = this;
        this.context = context;
        LoginService loginService = new LoginService(context, loginListener);
        loginService.login(user);
    }

    @Override
    public void onSuccess(String result) {
        try {
            JSONObject object = new JSONObject(result);
            //JSONObject resultObject = object.getJSONObject("result");
            if (object.get("result"))
            Log.e("result", object.get("result"));
            //SessionUtil.start(context, token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(VolleyError error) {
        SessionUtil.stop(context);
    }
}
