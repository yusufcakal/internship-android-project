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

import yusufcakal.com.stajtakip.webservices.interfaces.FirmaStajSonucListener;
import yusufcakal.com.stajtakip.webservices.interfaces.FirmaStajlarListener;
import yusufcakal.com.stajtakip.webservices.util.LinkUtil;
import yusufcakal.com.stajtakip.webservices.util.SessionUtil;
import yusufcakal.com.stajtakip.webservices.volley.VolleyClient;

/**
 * Created by Yusuf on 8.05.2018.
 */

public class FirmaStajSonucService {

    private RequestQueue requestQueue;
    private Context context;
    private String url = LinkUtil.firmaStajSonucUrl;
    private int requestMethod = Request.Method.POST;
    private FirmaStajSonucListener firmaStajSonucListener;

    public FirmaStajSonucService(Context context, final FirmaStajSonucListener firmaStajSonucListener) {
        this.context = context;
        requestQueue = VolleyClient.getInstance(context).getRequestQueue();
        this.firmaStajSonucListener = firmaStajSonucListener;
    }

    public  void setSonuc(final int staj_id, final int sonuc){
        StringRequest stringRequest = new StringRequest(requestMethod, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        firmaStajSonucListener.onSuccessSonuc(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        firmaStajSonucListener.onErrorSonuc(error);
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("token", SessionUtil.getToken(context));
                params.put("staj_id", String.valueOf(staj_id));
                params.put("sonuc", String.valueOf(sonuc));
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
