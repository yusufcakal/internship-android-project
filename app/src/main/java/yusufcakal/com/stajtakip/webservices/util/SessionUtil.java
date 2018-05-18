package yusufcakal.com.stajtakip.webservices.util;

import android.content.Context;

/**
 * Created by Yusuf on 12.05.2018.
 */

public class SessionUtil {

    public static boolean check(Context context){
        if (SharedPrefsUtils.getStringPreference(context, LinkUtil.SESSION_KEY) != null)
            return true;
        return false;
    }

    public static void start(Context context, String token){
        SharedPrefsUtils.setStringPreference(context, LinkUtil.SESSION_KEY, token);
    }

    public static void stop(Context context){
        SharedPrefsUtils.setStringPreference(context, LinkUtil.SESSION_KEY, null);
    }

    public static String getToken(Context context){
        return SharedPrefsUtils.getStringPreference(context, LinkUtil.SESSION_KEY);
    }

}
