package yusufcakal.com.stajtakip.webservices;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.*;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yusuf on 8.05.2018.
 */

public class LoginService {

    private RequestQueue requestQueue;
    private Context context;
    private String url = LinkUtil.loginUrl;
    private int requestMethod = Request.Method.POST;

    public LoginService(final Context context) {
        requestQueue = VolleyClient.getInstance(context).getRequestQueue();
        this.context = context;

        StringRequest stringRequest = new StringRequest(requestMethod, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("eposta", "eylmz058@gmail.com");
                params.put("sifre", "deneme");
                return params;
            }

        };

        requestQueue.add(stringRequest);

    }

}
