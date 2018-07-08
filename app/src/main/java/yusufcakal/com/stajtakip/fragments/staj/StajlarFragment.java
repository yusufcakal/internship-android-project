package yusufcakal.com.stajtakip.fragments.staj;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.VolleyError;
import com.github.javiersantos.bottomdialogs.BottomDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import yusufcakal.com.stajtakip.R;
import yusufcakal.com.stajtakip.adapter.firma.StajAdapter;
import yusufcakal.com.stajtakip.fragments.firma.FirmaStajlarFragment;
import yusufcakal.com.stajtakip.fragments.staj.stajgun.StajGunlerFragment;
import yusufcakal.com.stajtakip.pojo.Staj;
import yusufcakal.com.stajtakip.webservices.interfaces.FirmaStajSonucListener;
import yusufcakal.com.stajtakip.webservices.interfaces.FragmentListener;
import yusufcakal.com.stajtakip.webservices.interfaces.StajListeleListener;
import yusufcakal.com.stajtakip.webservices.services.FirmaStajSonucService;
import yusufcakal.com.stajtakip.webservices.services.StajlarService;

/**
 * Created by Yusuf on 21.05.2018.
 */

public class StajlarFragment extends Fragment implements StajListeleListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, FirmaStajSonucListener{

    private View view;
    private List<Staj> stajList;
    private ListView lvStajlar;
    private Button btnStajEkle;
    private FragmentListener fragmentListener;
    private int itemSelect;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_stajlar, container, false);

        lvStajlar = view.findViewById(R.id.lvStajlar);
        btnStajEkle = view.findViewById(R.id.btnStajEkle);
        lvStajlar.setOnItemClickListener(this);
        lvStajlar.setOnItemLongClickListener(this);
        btnStajEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentListener.onStart(new StajEkleFragment());
            }
        });

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
                    int stajId = object.getInt("staj_id");
                    int puan = object.getInt("puan");
                    int sonuc = object.getInt("sonuc");
                    String baslangicTarihi = object.getString("baslangic_tarih");
                    String bitisTarihi = object.getString("bitis_tarih");
                    String bolumAdi = object.getString("bolum_adi");
                    String firmaAdi = object.getString("firma_adi");
                    Staj staj = new Staj(stajId, puan, sonuc, baslangicTarihi, bitisTarihi, bolumAdi, firmaAdi);
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
        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (stajList.get(i).getSonuc() == 2){
            fragmentListener.onStart(new StajGunlerFragment(), stajList.get(i));
            Log.e("Staj ID", String.valueOf(stajList.get(i).getId()));
        }
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
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        itemSelect = i;
        if (stajList.get(itemSelect).getSonuc() == 2){
            new BottomDialog.Builder(getContext())
                    .setTitle("Staj Onay Bekliyor.")
                    .setContent("Öğrenci bu stajı henüz tamamlamadı tamamlamak istiyor musunuz.")
                    .setPositiveText("Tamamla")
                    .setPositiveBackgroundColorResource(R.color.colorPrimary)
                    .setPositiveTextColorResource(android.R.color.white)
                    .onPositive(new BottomDialog.ButtonCallback() {
                        @Override
                        public void onClick(BottomDialog dialog) {
                            /**
                             * Stajı Tamamla
                             */
                            stajList.clear();
                            FirmaStajSonucService firmaStajSonucService = new FirmaStajSonucService(getContext(), StajlarFragment.this);
                            firmaStajSonucService.setSonuc(stajList.get(itemSelect).getId(), 3); // Okul onay bekliyor yapıyoruz.
                        }
                    })
                    .show();
        }

        return true;
    }

    @Override
    public void onSuccessSonuc(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            boolean resultFlag = jsonObject.getBoolean("result");
            if (resultFlag){
                fragmentListener.onStart(new StajlarFragment());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorSonuc(VolleyError error) {

    }
}
