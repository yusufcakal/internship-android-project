package yusufcakal.com.stajtakip.webservices.services;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import yusufcakal.com.stajtakip.pojo.StajGun;
import yusufcakal.com.stajtakip.webservices.interfaces.StajGunEkleListener;
import yusufcakal.com.stajtakip.webservices.util.LinkUtil;
import yusufcakal.com.stajtakip.webservices.util.SessionUtil;
import yusufcakal.com.stajtakip.webservices.volley.AppHelper;
import yusufcakal.com.stajtakip.webservices.volley.VolleyClient;
import yusufcakal.com.stajtakip.webservices.volley.VolleyMultipartRequest;

/**
 * Created by Yusuf on 8.05.2018.
 */

public class StajGunEkleService {

    private RequestQueue requestQueue;
    private Context context;
    private String url = LinkUtil.stajGunEkleUrl;
    private int requestMethod = Request.Method.POST;
    private StajGunEkleListener stajGunEkleListener;

    public StajGunEkleService(Context context, final StajGunEkleListener stajGunEkleListener) {
        this.context = context;
        requestQueue = VolleyClient.getInstance(context).getRequestQueue();
        this.stajGunEkleListener = stajGunEkleListener;
    }

    public void gunEkle(final StajGun stajGun, final Bitmap resim){
        VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(com.android.volley.Request.Method.POST, url, new Response.Listener<NetworkResponse>() {

            @Override
            public void onResponse(NetworkResponse response) {
                String resultResponse = new String(response.data);
                stajGunEkleListener.onSuccess(resultResponse);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                stajGunEkleListener.onError(error);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("token", SessionUtil.getToken(context));
                params.put("kullanici_id", String.valueOf(SessionUtil.getUserId(context)));
                params.put("staj_id", String.valueOf(stajGun.getStajId()));
                params.put("tarih", String.valueOf(stajGun.getTarih()));
                params.put("aciklama", String.valueOf(stajGun.getAciklama()));
                return params;

            }

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                if (resim != null){
                    params.put("image", new DataPart("image.jpg", AppHelper.getFileDataFromDrawable(context, resim), "image/jpeg"));
                }
                return params;
            }
        };

        requestQueue.add(multipartRequest);

    }



}
