package yusufcakal.com.stajtakip.fragments.firma;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import yusufcakal.com.stajtakip.R;
import yusufcakal.com.stajtakip.adapter.firma.StajAdapter;
import yusufcakal.com.stajtakip.pojo.Staj;
import yusufcakal.com.stajtakip.webservices.interfaces.FirmaStajlarListener;
import yusufcakal.com.stajtakip.webservices.services.FirmaStajlarService;

/**
 * Created by Yusuf on 21.05.2018.
 */

public class FirmaStajlarFragment extends Fragment
        implements FirmaStajlarListener,
        AdapterView.OnItemClickListener{

    private View view;
    private ListView lvStajlar;
    private Staj staj;
    private List<Staj> stajList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_firmastajlarlar, container, false);

        stajList = new ArrayList<>();
        lvStajlar = view.findViewById(R.id.lvStajlar);
        lvStajlar.setOnItemClickListener(this);

        FirmaStajlarService firmaStajlarService = new FirmaStajlarService(getContext(), this);
        firmaStajlarService.getStajlar();

        return view;
    }

    @Override
    public void onSuccess(String result) {

        try {
            JSONObject jsonObject = new JSONObject(result);
            boolean resultFlag = jsonObject.getBoolean("result");
            if (resultFlag){
                JSONArray stajlarimArray = jsonObject.getJSONArray("list");
                for (int i=0; i<stajlarimArray.length(); i++){
                    JSONObject object = stajlarimArray.getJSONObject(i);
                    int stajId = object.getInt("staj_id");
                    int puan = object.getInt("puan");
                    int sonuc = object.getInt("sonuc");
                    String baslangicTarihi = object.getString("baslangic_tarih");
                    String bitisTarihi = object.getString("bitis_tarih");
                    String bolumAdi = object.getString("bolum_adi");
                    String eposta = object.getString("eposta");
                    Staj staj = new Staj(stajId, puan, sonuc, baslangicTarihi, bitisTarihi, bolumAdi, eposta);
                    stajList.add(staj);
                }
            }

            StajAdapter stajAdapter = new StajAdapter(getContext(), stajList);
            lvStajlar.setAdapter(stajAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(VolleyError error) {
        Toast.makeText(getContext(), String.valueOf(error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getContext(), stajList.get(i).getFirmaAdi(), Toast.LENGTH_SHORT).show();
    }
}
