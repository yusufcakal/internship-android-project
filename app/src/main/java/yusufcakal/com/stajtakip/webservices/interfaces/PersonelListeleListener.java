package yusufcakal.com.stajtakip.webservices.interfaces;

import com.android.volley.VolleyError;

/**
 * Created by Yusuf on 10.05.2018.
 */

public interface PersonelListeleListener {

    void onSuccess(String result);
    void onError(VolleyError error);

}
