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

import yusufcakal.com.stajtakip.pojo.Firma;
import yusufcakal.com.stajtakip.webservices.interfaces.FirmaEkleListener;
import yusufcakal.com.stajtakip.webservices.interfaces.FirmalarListeleListener;
import yusufcakal.com.stajtakip.webservices.util.LinkUtil;
import yusufcakal.com.stajtakip.webservices.util.SessionUtil;
import yusufcakal.com.stajtakip.webservices.volley.VolleyClient;

/**
 * Created by Yusuf on 8.05.2018.
 */

public class FirmaEkleService {

    private RequestQueue requestQueue;
    private Context context;
    private String url = LinkUtil.firmaEkleUrl;
    private int requestMethod = Request.Method.POST;
    private FirmaEkleListener firmaEkleListener;

    public FirmaEkleService(Context context, final FirmaEkleListener firmaEkleListener) {
        this.context = context;
        requestQueue = VolleyClient.getInstance(context).getRequestQueue();
        this.firmaEkleListener = firmaEkleListener;
    }

    public void firmaEkle(final Firma firma){
        StringRequest stringRequest = new StringRequest(requestMethod, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        firmaEkleListener.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        firmaEkleListener.onError(error);
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("token", SessionUtil.getToken(context));
                params.put("adi", firma.getAdi());
                params.put("eposta", firma.getEposta());
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
