package yusufcakal.com.stajtakip.fragments.firma;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.eightbitlab.bottomnavigationbar.BottomBarItem;
import com.eightbitlab.bottomnavigationbar.BottomNavigationBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import yusufcakal.com.stajtakip.R;
import yusufcakal.com.stajtakip.adapter.firma.StajAdapter;
import yusufcakal.com.stajtakip.fragments.staj.stajgun.FirmaStajGunlerFragment;
import yusufcakal.com.stajtakip.fragments.staj.stajgun.StajGunlerFragment;
import yusufcakal.com.stajtakip.pojo.Staj;
import yusufcakal.com.stajtakip.webservices.interfaces.FirmaStajlarListener;
import yusufcakal.com.stajtakip.webservices.interfaces.FragmentListener;
import yusufcakal.com.stajtakip.webservices.services.FirmaStajlarService;

/**
 * Created by Yusuf on 21.05.2018.
 */

public class FirmaStajlarFragment extends Fragment
        implements FirmaStajlarListener,
        AdapterView.OnItemClickListener{

    private StajAdapter stajAdapter;
    private BottomNavigationBar bottomNavigationBar;
    private View view;
    private ListView lvStajlar;
    private Staj staj;
    private List<Staj> stajList, stajListAnlik;
    private FragmentListener fragmentListener;
    @SuppressLint("ResourceType")

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
        view = inflater.inflate(R.layout.fragment_firmastajlarlar, container, false);

        bottomNavigationBar = view.findViewById(R.id.bottom_bar);

        stajList = new ArrayList<>();
        stajListAnlik = new ArrayList<>();
        lvStajlar = view.findViewById(R.id.lvStajlar);
        lvStajlar.setOnItemClickListener(this);

        FirmaStajlarService firmaStajlarService = new FirmaStajlarService(getContext(), this);
        firmaStajlarService.getStajlar();

        @SuppressLint("ResourceType") BottomBarItem item1 = new BottomBarItem(getResources().getDrawable(R.drawable.ic_home_black_24dp), R.string.staj_onay_bekleyen);
        @SuppressLint("ResourceType") BottomBarItem item2 = new BottomBarItem(getResources().getDrawable(R.drawable.ic_home_black_24dp), R.string.staj_onaylananlar);

        bottomNavigationBar.addTab(item1);
        bottomNavigationBar.addTab(item2);

        bottomNavigationBar.setOnSelectListener(new BottomNavigationBar.OnSelectListener() {
            @Override
            public void onSelect(int position) {

                if (position == 0){
                    onayBekleyenler();
                }else{
                    onaylananlar();
                }
            }
        });

        return view;
    }

    private void onayBekleyenler(){
        List<Staj> stajListOnayBekleyenler = new ArrayList<>();
        for (int i=0; i<stajList.size(); i++){
            if (stajList.get(i).getSonuc() == 0){
                stajListOnayBekleyenler.add(stajList.get(i));
            }
        }
        stajAdapter = new StajAdapter(getContext(), stajListOnayBekleyenler);
        stajListAnlik = stajListOnayBekleyenler;
        stajAdapter.notifyDataSetChanged();
        lvStajlar.setAdapter(stajAdapter);
    }

    private void onaylananlar(){
        List<Staj> stajList1 = new ArrayList<>();
        for (int i=0; i<stajList.size(); i++){
            if (stajList.get(i).getSonuc() != 0){
                stajList1.add(stajList.get(i));
            }
        }
        stajAdapter = new StajAdapter(getContext(), stajList1);
        stajListAnlik = stajList1;
        stajAdapter.notifyDataSetChanged();
        lvStajlar.setAdapter(stajAdapter);
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

            stajAdapter = new StajAdapter(getContext(), stajList);
            lvStajlar.setAdapter(stajAdapter);

            onayBekleyenler();


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
        if (stajListAnlik.get(i).getSonuc() == 4){
            Toast.makeText(getContext(), "Bu staj zaten değerlendirildi.", Toast.LENGTH_SHORT).show();
        }else{
            fragmentListener.onStart(new FirmaStajGunlerFragment(), stajListAnlik.get(i));
        }
    }
}
