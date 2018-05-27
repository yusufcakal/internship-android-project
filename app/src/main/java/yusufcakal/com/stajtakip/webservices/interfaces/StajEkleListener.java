package yusufcakal.com.stajtakip.webservices.interfaces;

import com.android.volley.VolleyError;

/**
 * Created by Yusuf on 21.05.2018.
 */

public interface StajEkleListener {

    void onSuccessStaj(String result);
    void onErrorStaj(VolleyError error);

}
