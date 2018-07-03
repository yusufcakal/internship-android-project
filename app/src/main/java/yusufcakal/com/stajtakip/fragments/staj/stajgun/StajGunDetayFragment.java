package yusufcakal.com.stajtakip.fragments.staj.stajgun;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.InputStream;

import yusufcakal.com.stajtakip.R;
import yusufcakal.com.stajtakip.pojo.Staj;
import yusufcakal.com.stajtakip.pojo.StajGun;
import yusufcakal.com.stajtakip.webservices.interfaces.FragmentListener;
import yusufcakal.com.stajtakip.webservices.interfaces.StajGunEkleListener;
import yusufcakal.com.stajtakip.webservices.services.StajGunEkleService;
import yusufcakal.com.stajtakip.webservices.util.LinkUtil;
import yusufcakal.com.stajtakip.webservices.util.SharedPrefsUtils;

/**
 * Created by Yusuf on 21.05.2018.
 */

public class StajGunDetayFragment extends android.support.v4.app.Fragment
        implements View.OnClickListener{

    private View view;
    private String tarih;
    private TextView tvStajGun;
    private TextView tvAciklama;
    private Button btnStajGunDuzenle, btnResimEkle;
    private ImageView imResim;
    private Bitmap selectedImage;
    private StajGun stajGun;
    private ProgressDialog dialog;
    private FragmentListener fragmentListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentListener = (FragmentListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentListener = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_stajgundetayview, container, false);

        stajGun = (StajGun) getArguments().get("stajGun");
        tarih = stajGun.getTarih();

        tvStajGun = view.findViewById(R.id.tvStajGun);
        tvAciklama = view.findViewById(R.id.tvAciklama);

        btnStajGunDuzenle = view.findViewById(R.id.btnStajGunDuzenle);
        btnResimEkle = view.findViewById(R.id.btnResimEkle);
        imResim = view.findViewById(R.id.imResim);

        String date = stajGun.getTarih().split(" ")[0];

        String year = date.split("-")[0];
        String month = date.split("-")[1];
        String day = date.split("-")[2];

        month = checkDigit(Integer.parseInt(month));
        day = checkDigit(Integer.parseInt(day));

        String dateLast = year + "-" + month + "-" + day;

        tvStajGun.setText(dateLast);
        tvAciklama.setText(stajGun.getAciklama());
        try {
            Log.e("RESİM DETAY", stajGun.getStajGunResimList().get(0).getResim());
            String resim = stajGun.getImagePath() + stajGun.getStajGunResimList().get(0).getResim();
            Picasso.get().load(resim).into(imResim);
        }catch (Exception e){
            e.printStackTrace();
        }


        return view;
    }

    public String checkDigit (int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

    @Override
    public void onClick(View view) {

    }

}
