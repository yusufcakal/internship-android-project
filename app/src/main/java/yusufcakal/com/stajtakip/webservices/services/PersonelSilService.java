package yusufcakal.com.stajtakip.webservices.services;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import yusufcakal.com.stajtakip.pojo.User;
import yusufcakal.com.stajtakip.webservices.interfaces.PersonelEkleListener;
import yusufcakal.com.stajtakip.webservices.interfaces.PersonelSilListener;
import yusufcakal.com.stajtakip.webservices.util.LinkUtil;
import yusufcakal.com.stajtakip.webservices.util.SessionUtil;
import yusufcakal.com.stajtakip.webservices.volley.VolleyClient;

/**
 * Created by Yusuf on 8.05.2018.
 */

public class PersonelSilService {

    private RequestQueue requestQueue;
    private Context context;
    private String url = LinkUtil.personelSilUrl;
    private int requestMethod = Request.Method.POST;
    private PersonelSilListener personelSilListener;

    public PersonelSilService(Context context, final PersonelSilListener personelSilListener) {
        this.context = context;
        requestQueue = VolleyClient.getInstance(context).getRequestQueue();
        this.personelSilListener = personelSilListener;
    }

    public void personelSil(final User user){
        StringRequest stringRequest = new StringRequest(requestMethod, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        personelSilListener.onSuccessSil(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        personelSilListener.onErrorSil(error);
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("token", SessionUtil.getToken(context));
                params.put("firma_id", String.valueOf(SessionUtil.getUserId(context)));
                params.put("personel_id", String.valueOf(user.getId()));
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                return headers;
            }

        };

        requestQueue.add(stringRequest);
    }



}
