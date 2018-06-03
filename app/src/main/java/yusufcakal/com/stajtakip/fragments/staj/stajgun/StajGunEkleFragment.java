package yusufcakal.com.stajtakip.fragments.staj.stajgun;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import yusufcakal.com.stajtakip.R;
import yusufcakal.com.stajtakip.adapter.firma.CustomSpinnerAdapter;
import yusufcakal.com.stajtakip.fragments.staj.StajlarFragment;
import yusufcakal.com.stajtakip.pojo.Firma;
import yusufcakal.com.stajtakip.pojo.Staj;
import yusufcakal.com.stajtakip.webservices.interfaces.FirmalarListeleListener;
import yusufcakal.com.stajtakip.webservices.interfaces.FragmentListener;
import yusufcakal.com.stajtakip.webservices.interfaces.StajEkleListener;
import yusufcakal.com.stajtakip.webservices.services.FirmalarService;
import yusufcakal.com.stajtakip.webservices.services.StajEkleService;
import yusufcakal.com.stajtakip.webservices.util.SessionUtil;

/**
 * Created by Yusuf on 21.05.2018.
 */

public class StajGunEkleFragment extends android.support.v4.app.Fragment {

    private View view;
    private Staj staj;
    private String tarih;
    private TextView tvStajGun;
    private EditText etAciklama;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_stajgunekle, container, false);

        staj = (Staj) getArguments().get("staj");
        tarih = staj.getStajGunTarihi();

        tvStajGun = view.findViewById(R.id.tvStajGun);
        etAciklama = view.findViewById(R.id.etAciklama);
        tvStajGun.setText(staj.getStajGunTarihi());

        return view;
    }

    public String checkDigit (int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

}
