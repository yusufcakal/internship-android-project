package yusufcakal.com.stajtakip.webservices.services;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yusufcakal.com.stajtakip.pojo.User;
import yusufcakal.com.stajtakip.webservices.interfaces.FirmaStajDegerlendirListener;
import yusufcakal.com.stajtakip.webservices.interfaces.PersonelEkleListener;
import yusufcakal.com.stajtakip.webservices.util.LinkUtil;
import yusufcakal.com.stajtakip.webservices.util.SessionUtil;
import yusufcakal.com.stajtakip.webservices.volley.VolleyClient;

/**
 * Created by Yusuf on 8.05.2018.
 */

public class FirmaStajDegerlendirService {

    private RequestQueue requestQueue;
    private Context context;
    private String url = LinkUtil.firmaStajDegerlendirUrl;
    private int requestMethod = Request.Method.POST;
    private FirmaStajDegerlendirListener firmaStajDegerlendirListener;

    public FirmaStajDegerlendirService(Context context, final FirmaStajDegerlendirListener firmaStajDegerlendirListener) {
        this.context = context;
        requestQueue = VolleyClient.getInstance(context).getRequestQueue();
        this.firmaStajDegerlendirListener = firmaStajDegerlendirListener;
    }

    public void stajDegerlendir(final int puan, final List<Integer> stajGunOnayList, final int stajId){
        StringRequest stringRequest = new StringRequest(requestMethod, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        firmaStajDegerlendirListener.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        firmaStajDegerlendirListener.onError(error);
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("token", SessionUtil.getToken(context));
                params.put("staj_id", String.valueOf(stajId));
                params.put("raporlar", String.valueOf(stajGunOnayList));
                params.put("puan", String.valueOf(puan));
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
