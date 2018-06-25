package yusufcakal.com.stajtakip.fragments.personel;

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
import yusufcakal.com.stajtakip.adapter.firma.FirmaAdapter;
import yusufcakal.com.stajtakip.adapter.personel.PersonelAdapter;
import yusufcakal.com.stajtakip.pojo.Firma;
import yusufcakal.com.stajtakip.pojo.User;
import yusufcakal.com.stajtakip.webservices.interfaces.FirmalarListeleListener;
import yusufcakal.com.stajtakip.webservices.interfaces.FragmentListener;
import yusufcakal.com.stajtakip.webservices.interfaces.PersonelListeleListener;
import yusufcakal.com.stajtakip.webservices.services.PersonelService;

/**
 * Created by Yusuf on 21.05.2018.
 */

public class PersonelFragment extends Fragment implements
        View.OnClickListener,
        PersonelListeleListener,
        AdapterView.OnItemClickListener{

    private View view;
    private ListView lvPersonel;
    private Button btnPersonelEkle;
    private List<User> userList;
    private PersonelAdapter personelAdapter;
    private FragmentListener fragmentListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentListener= (FragmentListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentListener = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_personel, container, false);

        lvPersonel = view.findViewById(R.id.lvPersonel);
        lvPersonel.setOnItemClickListener(this);
        btnPersonelEkle = view.findViewById(R.id.btnPersonelEkle);
        btnPersonelEkle.setOnClickListener(this);

        PersonelService personelService = new PersonelService(getActivity(), this);
        personelService.getPersonel();

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.equals(btnPersonelEkle)){
            fragmentListener.onStart(new PersonelEkleFragment());
        }
    }

    @Override
    public void onSuccess(String result) {

        userList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(result);
            boolean resultFlag = jsonObject.getBoolean("result");
            if (resultFlag){
                JSONArray UserListArray = jsonObject.getJSONArray("list");
                for (int i=0; i<UserListArray.length(); i++){
                    JSONObject userObject = UserListArray.getJSONObject(i);
                    int id = userObject.getInt("kullanici_id");
                    String eposta = userObject.getString("eposta");
                    User user = new User();
                    user.setId(id);
                    user.setEmail(eposta);
                    userList.add(user);
                }
            }
            personelAdapter = new PersonelAdapter(getContext(), userList);
            lvPersonel.setAdapter(personelAdapter);
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
        Toast.makeText(getActivity(), userList.get(i).getEmail(), Toast.LENGTH_SHORT).show();
    }
}
