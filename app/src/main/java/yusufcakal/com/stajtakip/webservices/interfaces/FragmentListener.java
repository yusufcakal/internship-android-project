package yusufcakal.com.stajtakip.webservices.interfaces;


import android.support.v4.app.Fragment;

import yusufcakal.com.stajtakip.pojo.Staj;

/**
 * Created by Yusuf on 21.05.2018.
 */

public interface FragmentListener {

    void onStart(Fragment fragment);
    void onStart(Fragment fragment, Staj staj);

}
