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

import yusufcakal.com.stajtakip.webservices.interfaces.FirmaStajlarListener;
import yusufcakal.com.stajtakip.webservices.interfaces.FirmalarListeleListener;
import yusufcakal.com.stajtakip.webservices.util.LinkUtil;
import yusufcakal.com.stajtakip.webservices.util.SessionUtil;
import yusufcakal.com.stajtakip.webservices.volley.VolleyClient;

/**
 * Created by Yusuf on 8.05.2018.
 */

public class FirmaStajlarService {

    private RequestQueue requestQueue;
    private Context context;
    private String url = LinkUtil.firmaStajlarUrl;
    private int requestMethod = Request.Method.POST;
    private FirmaStajlarListener firmaStajlarListener;

    public FirmaStajlarService(Context context, final FirmaStajlarListener firmaStajlarListener) {
        this.context = context;
        requestQueue = VolleyClient.getInstance(context).getRequestQueue();
        this.firmaStajlarListener = firmaStajlarListener;
    }

    public  void getStajlar(){
        StringRequest stringRequest = new StringRequest(requestMethod, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        firmaStajlarListener.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        firmaStajlarListener.onError(error);
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("token", SessionUtil.getToken(context));
                params.put("firma_id", String.valueOf(SessionUtil.getUserId(context)));
                params.put("sonuc", String.valueOf(0));
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
