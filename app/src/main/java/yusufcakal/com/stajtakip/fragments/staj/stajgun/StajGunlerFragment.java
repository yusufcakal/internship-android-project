package yusufcakal.com.stajtakip.fragments.staj.stajgun;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import yusufcakal.com.stajtakip.R;
import yusufcakal.com.stajtakip.adapter.firma.StajGunlerAdapter;
import yusufcakal.com.stajtakip.pojo.Staj;
import yusufcakal.com.stajtakip.pojo.StajGun;
import yusufcakal.com.stajtakip.pojo.StajGunResim;
import yusufcakal.com.stajtakip.webservices.interfaces.FragmentListener;
import yusufcakal.com.stajtakip.webservices.interfaces.StajGunListeleListener;
import yusufcakal.com.stajtakip.webservices.services.StajGunlerService;
import yusufcakal.com.stajtakip.webservices.util.SessionUtil;
import yusufcakal.com.stajtakip.webservices.util.SharedPrefsUtils;

/**
 * Created by Yusuf on 21.05.2018.
 */

public class StajGunlerFragment extends Fragment implements
        StajGunListeleListener,
        AdapterView.OnItemClickListener{

    private View view;
    private Button btnStajGunEkle;
    private FragmentListener fragmentListener;
    private final Calendar myCalendar = Calendar.getInstance();
    private Staj staj;
    private TextView tvStajGunYok;
    private ListView lvStajGunler;
    List<StajGun> stajGunList = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_stajgunler, container, false);

        staj = (Staj) getArguments().get("staj");

        StajGunlerService stajGunlerService = new StajGunlerService(getContext(), this);
        stajGunlerService.stajGunler(staj.getId());

        btnStajGunEkle = view.findViewById(R.id.btnStajGunEkle);
        tvStajGunYok = view.findViewById(R.id.tvStajGunYok);
        lvStajGunler = view.findViewById(R.id.lvStajGunler);
        lvStajGunler.setOnItemClickListener(this);
        btnStajGunEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), R.style.AppTheme_DialogTheme,  date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));

                String date = staj.getBaslangicTarihi().split(" ")[0];
                int year = Integer.parseInt(date.split("-")[0]);
                int month = Integer.parseInt(date.split("-")[1]);
                int day = Integer.parseInt(date.split("-")[2]);
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month);
                cal.set(Calendar.DAY_OF_MONTH, day);
                datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis());

                String date1 = staj.getBitisTarihi().split(" ")[0];
                int year1 = Integer.parseInt(date1.split("-")[0]);
                int month1 = Integer.parseInt(date1.split("-")[1]);
                int day1 = Integer.parseInt(date1.split("-")[2]);
                Calendar cal1 = Calendar.getInstance();
                cal1.set(Calendar.YEAR, year1);
                cal1.set(Calendar.MONTH, month1);
                cal1.set(Calendar.DAY_OF_MONTH, day1);

                datePickerDialog.getDatePicker().setMaxDate(cal1.getTimeInMillis());
                datePickerDialog.show();

                datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ekle", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        int year = datePickerDialog.getDatePicker().getYear();
                        int month = datePickerDialog.getDatePicker().getMonth();
                        int day = datePickerDialog.getDatePicker().getDayOfMonth();
                        String date = year + "-" + month + "-" + day;
                        staj.setStajGunTarihi(date);
                        fragmentListener.onStart(new StajGunEkleFragment(), staj);
                    }
                });

            }
        });

        return view;
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        }

    };

    @Override
    public void onSuccess(String result) {
        try {

            JSONObject jsonObject = new JSONObject(result);
            boolean resultFlag = jsonObject.getBoolean("result");
            if (resultFlag){
                stajGunList = new ArrayList<>();
                JSONArray gunlerArray = jsonObject.getJSONArray("gunler");
                for (int i=0; i<gunlerArray.length(); i++){
                    JSONObject stajGun = gunlerArray.getJSONObject(i);
                    int stajGunId = stajGun.getInt("id");
                    int stajId = stajGun.getInt("staj_id");
                    String tarih = stajGun.getString("staj_tarihi");
                    String aciklama = stajGun.getString("aciklama");
                    int firmaOnay = stajGun.getInt("firma_onay");
                    int okulOnay = stajGun.getInt("okul_onay");
                    List<StajGunResim> stajGunResimList = new ArrayList<>();
                    JSONArray stajGunResimler = stajGun.getJSONArray("resimler");
                    for (int j=0; j<stajGunResimler.length(); j++){
                        JSONObject stajGunResimObject = stajGunResimler.getJSONObject(j);
                        int resimId = stajGunResimObject.getInt("id");
                        String resim = stajGunResimObject.getString("resim");
                        StajGunResim stajGunResim = new StajGunResim(resimId, resim);
                        stajGunResimList.add(stajGunResim);
                    }
                    StajGun stajGunPojo = new StajGun(stajGunId, stajId, aciklama, stajGunResimList, firmaOnay, okulOnay, tarih);
                    stajGunList.add(stajGunPojo);
                }
                String resimYolu = jsonObject.getString("url");
                SharedPrefsUtils.setStringPreference(getContext(), "resimYolu", resimYolu);

                StajGunlerAdapter stajGunlerAdapter = new StajGunlerAdapter(getContext(), stajGunList);
                lvStajGunler.setAdapter(stajGunlerAdapter);

                if (stajGunList.size() == 0){
                    tvStajGunYok.setVisibility(View.VISIBLE);
                    lvStajGunler.setVisibility(View.GONE);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onError(VolleyError error) {
        Toast.makeText(getContext(), String.valueOf(error), Toast.LENGTH_SHORT).show();
    }

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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        /**
         * TODO : Staj gün düzenlemek için iç sayfaya gidecek.
         */
        Toast.makeText(getContext(), stajGunList.get(i).getAciklama(), Toast.LENGTH_SHORT).show();
    }

}
