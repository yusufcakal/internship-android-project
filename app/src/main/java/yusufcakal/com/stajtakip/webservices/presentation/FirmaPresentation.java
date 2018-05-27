package yusufcakal.com.stajtakip.webservices.presentation;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import yusufcakal.com.stajtakip.pojo.Firma;
import yusufcakal.com.stajtakip.webservices.interfaces.FirmalarListeleListener;
import yusufcakal.com.stajtakip.webservices.services.FirmalarService;

/**
 * Created by Yusuf on 27.05.2018.
 */

public class FirmaPresentation implements FirmalarListeleListener{

    private List<Firma> firmaList;
    private Context context;

    public FirmaPresentation(Context context) {
        this.context = context;
        firmaList = new ArrayList<>();
    }

    public List<Firma> getFirmalar(){
        FirmalarService firmalarService = new FirmalarService(context, this);
        firmalarService.getFirmalar();
        return firmaList;
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
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onError(VolleyError error) {
        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
    }
}
