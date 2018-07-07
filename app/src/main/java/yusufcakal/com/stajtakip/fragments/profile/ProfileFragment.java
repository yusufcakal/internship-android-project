package yusufcakal.com.stajtakip.fragments.profile;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import yusufcakal.com.stajtakip.adapter.personel.PersonelAdapter;
import yusufcakal.com.stajtakip.fragments.personel.PersonelEkleFragment;
import yusufcakal.com.stajtakip.pojo.User;
import yusufcakal.com.stajtakip.webservices.interfaces.FragmentListener;
import yusufcakal.com.stajtakip.webservices.interfaces.PersonelListeleListener;
import yusufcakal.com.stajtakip.webservices.interfaces.PersonelSilListener;
import yusufcakal.com.stajtakip.webservices.services.PersonelService;
import yusufcakal.com.stajtakip.webservices.services.PersonelSilService;

/**
 * Created by Yusuf on 21.05.2018.
 */

public class ProfileFragment extends Fragment{

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        return view;
    }
}
