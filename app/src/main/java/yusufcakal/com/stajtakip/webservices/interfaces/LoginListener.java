package yusufcakal.com.stajtakip.webservices.interfaces;

import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created by Yusuf on 10.05.2018.
 */

public interface LoginListener {

    void onSuccess(String result);
    void onError(VolleyError error);

}
