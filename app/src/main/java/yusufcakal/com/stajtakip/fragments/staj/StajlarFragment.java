package yusufcakal.com.stajtakip.fragments.staj;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import yusufcakal.com.stajtakip.R;
import yusufcakal.com.stajtakip.pojo.Staj;
import yusufcakal.com.stajtakip.webservices.interfaces.StajListeleListener;
import yusufcakal.com.stajtakip.webservices.services.StajlarService;

/**
 * Created by Yusuf on 21.05.2018.
 */

public class StajlarFragment extends Fragment implements StajListeleListener{

    private View view;
    private List<Staj> stajList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_stajlar, container, false);

        stajList = new ArrayList<>();

        StajlarService stajlarService = new StajlarService(getContext(), this);
        stajlarService.getStajlar();

        return view;
    }

    @Override
    public void onSuccess(String result) {

        try {
            JSONObject jsonObject = new JSONObject(result);
            boolean resultFlag = jsonObject.getBoolean("result");
            if (resultFlag){
                JSONArray stajlarimArray = jsonObject.getJSONArray("stajlarim");
                for (int i=0; i<stajlarimArray.length(); i++){
                    JSONObject object = stajlarimArray.getJSONObject(i);
                    int stajId = object.getInt("id");
                    int kullaniciId = object.getInt("kullanici_id");
                    int firmaId = object.getInt("firma_id");
                    int puan = object.getInt("puan");
                    int sonuc = object.getInt("sonuc");
                    int bolumId = object.getInt("bolum_id");
                    String baslangicTarihi = object.getString("baslangic_tarih");
                    String bitisTarihi = object.getString("bitis_tarih");
                    Staj staj = new Staj(stajId, kullaniciId, firmaId, puan, sonuc, bolumId, baslangicTarihi, bitisTarihi);
                    stajList.add(staj);
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(VolleyError error) {
        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
    }
}
