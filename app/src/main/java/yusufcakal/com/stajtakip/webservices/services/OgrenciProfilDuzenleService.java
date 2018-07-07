package yusufcakal.com.stajtakip.webservices.services;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import java.util.HashMap;
import java.util.Map;
import yusufcakal.com.stajtakip.webservices.interfaces.OgrenciProfilDuzenleListener;
import yusufcakal.com.stajtakip.webservices.util.LinkUtil;
import yusufcakal.com.stajtakip.webservices.util.SessionUtil;
import yusufcakal.com.stajtakip.webservices.volley.AppHelper;
import yusufcakal.com.stajtakip.webservices.volley.VolleyClient;
import yusufcakal.com.stajtakip.webservices.volley.VolleyMultipartRequest;

/**
 * Created by Yusuf on 8.05.2018.
 */

public class OgrenciProfilDuzenleService {

    private RequestQueue requestQueue;
    private Context context;
    private String url = LinkUtil.ogrenciProfilDuzenle;
    private int requestMethod = Request.Method.POST;
    private OgrenciProfilDuzenleListener ogrenciProfilDuzenleListener;

    public OgrenciProfilDuzenleService(Context context, final OgrenciProfilDuzenleListener ogrenciProfilDuzenleListener) {
        this.context = context;
        requestQueue = VolleyClient.getInstance(context).getRequestQueue();
        this.ogrenciProfilDuzenleListener = ogrenciProfilDuzenleListener;
    }

    public void duzenle(final String isim, final String sifre, final Bitmap resim){
        VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(requestMethod, url, new Response.Listener<NetworkResponse>() {

            @Override
            public void onResponse(NetworkResponse response) {
                String resultResponse = new String(response.data);
                ogrenciProfilDuzenleListener.onSuccess(resultResponse);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ogrenciProfilDuzenleListener.onError(error);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("token", SessionUtil.getToken(context));
                params.put("kullanici_id", String.valueOf(SessionUtil.getUserId(context)));
                params.put("ad_soyad", isim);
                params.put("sifre", sifre);
                return params;

            }

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                if (resim != null){
                    params.put("resim", new DataPart("image.jpg", AppHelper.getFileDataFromDrawable(context, resim), "image/jpeg"));
                }
                return params;
            }
        };

        requestQueue.add(multipartRequest);

    }


}
