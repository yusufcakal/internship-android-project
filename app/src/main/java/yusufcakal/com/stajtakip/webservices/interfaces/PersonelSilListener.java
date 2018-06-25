package yusufcakal.com.stajtakip.webservices.interfaces;

import com.android.volley.VolleyError;

/**
 * Created by Yusuf on 21.05.2018.
 */

public interface PersonelSilListener {

    void onSuccessSil(String result);
    void onErrorSil(VolleyError error);

}
