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

    public static int getUserId(Context context){
        return SharedPrefsUtils.getIntegerPreference(context, LinkUtil.USER_ID, -1);
    }

    public static int getBolumId(Context context){
        return SharedPrefsUtils.getIntegerPreference(context, LinkUtil.BOLUM_ID, -1);
    }

    public static void setIsim(Context context, String isim){
        SharedPrefsUtils.setStringPreference(context, LinkUtil.ISIM, isim);
    }

    public static void setSifre(Context context, String sifre){
        SharedPrefsUtils.setStringPreference(context, LinkUtil.SIFRE, sifre);
    }

    public static void setResim(Context context, String resim){
        SharedPrefsUtils.setStringPreference(context, LinkUtil.RESIM, resim);
    }

    public static void setBolum(Context context, String bolum){
        SharedPrefsUtils.setStringPreference(context, LinkUtil.BOLUM, bolum);
    }

    public static String getIsim(Context context){
        return SharedPrefsUtils.getStringPreference(context, LinkUtil.ISIM);
    }

    public static String getSifre(Context context){
        return SharedPrefsUtils.getStringPreference(context, LinkUtil.SIFRE);
    }

    public static String getResim(Context context){
        return SharedPrefsUtils.getStringPreference(context, LinkUtil.RESIM);
    }

    public static String getBolum(Context context){
        return SharedPrefsUtils.getStringPreference(context, LinkUtil.BOLUM);
    }

}
