package yusufcakal.com.stajtakip.fragments.staj;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import yusufcakal.com.stajtakip.R;
import yusufcakal.com.stajtakip.adapter.firma.CustomSpinnerAdapter;
import yusufcakal.com.stajtakip.pojo.Firma;
import yusufcakal.com.stajtakip.webservices.interfaces.FirmalarListeleListener;
import yusufcakal.com.stajtakip.webservices.services.FirmalarService;

/**
 * Created by Yusuf on 21.05.2018.
 */

public class StajEkleFragment extends android.support.v4.app.Fragment implements
        FirmalarListeleListener{

    private List<Firma> firmaList = new ArrayList<>();
    private View view;
    private Spinner spinner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_stajekle, container, false);

        spinner = view.findViewById(R.id.spinner);

        FirmalarService firmalarService = new FirmalarService(getContext(), this);
        firmalarService.getFirmalar();

        return view;
    }

    @Override
    public void onSuccess(String result) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(result);
            boolean resultFlag = jsonObject.getBoolean("result");
            if (resultFlag){
                JSONArray firmaListArray = jsonObject.getJSONArray("list");
                for (int i=0; i<firmaListArray.length(); i++){
                    JSONObject firmaObject = firmaListArray.getJSONObject(i);
                    int id = firmaObject.getInt("id");
                    int onay = firmaObject.getInt("onay");
                    String adi = firmaObject.getString("adi");
                    Firma firma = new Firma(onay, adi);
                    firma.setId(id);
                    firmaList.add(firma);
                }

                CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(getContext(), android.R.layout.simple_spinner_item, firmaList);
                customSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(customSpinnerAdapter);

                Firma firma = (Firma) spinner.getSelectedItem();
                Toast.makeText(getContext(), firma.getAdi(), Toast.LENGTH_SHORT).show();

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
