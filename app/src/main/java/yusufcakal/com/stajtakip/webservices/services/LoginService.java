package yusufcakal.com.stajtakip.webservices.services;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.*;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import yusufcakal.com.stajtakip.MainActivity;
import yusufcakal.com.stajtakip.User;
import yusufcakal.com.stajtakip.webservices.interfaces.LoginListener;
import yusufcakal.com.stajtakip.webservices.util.LinkUtil;
import yusufcakal.com.stajtakip.webservices.volley.VolleyClient;

/**
 * Created by Yusuf on 8.05.2018.
 */

public class LoginService {

    private RequestQueue requestQueue;
    private Context context;
    private String url = LinkUtil.loginUrl;
    private int requestMethod = Request.Method.POST;
    private LoginListener loginListener;

    public LoginService(final Context context, final User user) {
        requestQueue = VolleyClient.getInstance(context).getRequestQueue();
        this.context = context;
        loginListener = (LoginListener) context;

        StringRequest stringRequest = new StringRequest(requestMethod, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loginListener.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loginListener.onError(error);
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("eposta", user.getEmail());
                params.put("sifre", user.getPassword());
                return params;
            }

        };

        requestQueue.add(stringRequest);

    }

}
