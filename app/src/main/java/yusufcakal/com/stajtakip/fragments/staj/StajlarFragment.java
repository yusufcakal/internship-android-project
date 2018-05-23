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

import yusufcakal.com.stajtakip.R;
import yusufcakal.com.stajtakip.webservices.interfaces.StajListeleListener;
import yusufcakal.com.stajtakip.webservices.services.StajlarService;

/**
 * Created by Yusuf on 21.05.2018.
 */

public class StajlarFragment extends Fragment implements StajListeleListener{

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_stajlar, container, false);

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

                Toast.makeText(getContext(), "" + stajlarimArray.toString(), Toast.LENGTH_SHORT).show();
                /*
                for (int i=0; i<stajlarimArray.length(); i++){
                    JSONObject object = stajlarimArray.getJSONObject(i);

                }
                */
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onError(VolleyError error) {
        Toast.makeText(getContext(), error.toString() + " sasadas", Toast.LENGTH_SHORT).show();
    }
}
