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
import yusufcakal.com.stajtakip.pojo.Staj;
import yusufcakal.com.stajtakip.webservices.interfaces.StajEkleListener;
import yusufcakal.com.stajtakip.webservices.util.LinkUtil;
import yusufcakal.com.stajtakip.webservices.util.SessionUtil;
import yusufcakal.com.stajtakip.webservices.volley.VolleyClient;

/**
 * Created by Yusuf on 8.05.2018.
 */

public class StajGunService {

    private RequestQueue requestQueue;
    private Context context;
    private String url = LinkUtil.stajEkleUrl;
    private int requestMethod = Request.Method.POST;
    private StajEkleListener stajEkleListener;

    public StajGunService(Context context, final StajEkleListener stajEkleListener) {
        this.context = context;
        requestQueue = VolleyClient.getInstance(context).getRequestQueue();
        this.stajEkleListener = stajEkleListener;
    }

    public void stajEkle(final Staj staj){
        StringRequest stringRequest = new StringRequest(requestMethod, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        stajEkleListener.onSuccessStaj(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        stajEkleListener.onErrorStaj(error);
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("token", SessionUtil.getToken(context));
                params.put("kullanici_id", String.valueOf(SessionUtil.getUserId(context)));
                params.put("bolum_id", String.valueOf(SessionUtil.getBolumId(context)));
                params.put("baslangic_tarih", staj.getBaslangicTarihi());
                params.put("bitis_tarih", staj.getBitisTarihi());
                params.put("firma_id", String.valueOf(staj.getFirmaId()));
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
