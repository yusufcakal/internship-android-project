package yusufcakal.com.stajtakip.fragments.firma;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import yusufcakal.com.stajtakip.R;
import yusufcakal.com.stajtakip.adapter.firma.FirmaAdapter;
import yusufcakal.com.stajtakip.pojo.Firma;
import yusufcakal.com.stajtakip.webservices.interfaces.FirmalarListeleListener;
import yusufcakal.com.stajtakip.webservices.interfaces.FragmentListener;
import yusufcakal.com.stajtakip.webservices.services.FirmalarService;
import yusufcakal.com.stajtakip.webservices.util.SessionUtil;

/**
 * Created by Yusuf on 21.05.2018.
 */

public class FirmalarFragment extends Fragment implements FirmalarListeleListener{

    private FragmentListener fragmentListener;
    private View view;
    private List<Firma> firmaList;
    private ListView lvFirma;
    private FirmaAdapter firmaAdapter;
    private Button btnFirmaEkle;

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
        view = inflater.inflate(R.layout.fragment_firmalar, container, false);

        Log.e("TOKEN", SessionUtil.getToken(getContext()));

        lvFirma = view.findViewById(R.id.lvFirma);
        btnFirmaEkle = view.findViewById(R.id.btnFirmaEkle);
        btnFirmaEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentListener.onStart(new FirmaEkleFragment());
            }
        });

        firmaList = new ArrayList<>();
        FirmalarService firmalarService = new FirmalarService(getContext(), this);
        firmalarService.getFirmalar();

        return view;
    }

    @Override
    public void onSuccess(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
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
            }
            firmaAdapter = new FirmaAdapter(getContext(), firmaList);
            lvFirma.setAdapter(firmaAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(VolleyError error) {

    }
}
