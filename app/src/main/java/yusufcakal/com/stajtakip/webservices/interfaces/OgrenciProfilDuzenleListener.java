package yusufcakal.com.stajtakip.webservices.interfaces;

import com.android.volley.VolleyError;

/**
 * Created by Yusuf on 21.05.2018.
 */

public interface OgrenciProfilDuzenleListener {

    void onSuccess(String result);
    void onError(VolleyError error);

}
