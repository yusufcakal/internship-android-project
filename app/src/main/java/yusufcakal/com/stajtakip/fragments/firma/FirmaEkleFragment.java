package yusufcakal.com.stajtakip.fragments.firma;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import yusufcakal.com.stajtakip.OgrenciDashboardActivity;
import yusufcakal.com.stajtakip.R;
import yusufcakal.com.stajtakip.pojo.Firma;
import yusufcakal.com.stajtakip.webservices.interfaces.FirmaEkleListener;
import yusufcakal.com.stajtakip.webservices.services.FirmaEkleService;

/**
 * Created by Yusuf on 21.05.2018.
 */

public class FirmaEkleFragment extends android.support.v4.app.Fragment
        implements View.OnClickListener,
        FirmaEkleListener{

    private View view;
    private EditText etFirmaAdi, etFirmaEmail;
    private Button btnFirmaEkle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_firmaekle, container, false);

        etFirmaAdi = view.findViewById(R.id.etFirmaAdi);
        etFirmaEmail = view.findViewById(R.id.etFirmaMail);
        btnFirmaEkle = view.findViewById(R.id.btnFirmaEkle);
        btnFirmaEkle.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        String firmaAdi = etFirmaAdi.getText().toString();
        String firmaMail = etFirmaEmail.getText().toString();

        Firma firma = new Firma();
        firma.setAdi(firmaAdi);
        firma.setEposta(firmaMail);

        FirmaEkleService firmaEkleService = new FirmaEkleService(getContext(), this);
        firmaEkleService.firmaEkle(firma);

    }

    @Override
    public void onSuccess(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            boolean resultFlag = jsonObject.getBoolean("result");
            if (resultFlag){
                startActivity(new Intent(getActivity(), OgrenciDashboardActivity.class));
                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }else{
                String errorMessage = jsonObject.getString("error");
                Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(VolleyError error) {

    }
}
