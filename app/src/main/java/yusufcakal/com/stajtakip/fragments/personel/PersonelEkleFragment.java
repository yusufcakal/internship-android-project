package yusufcakal.com.stajtakip.fragments.personel;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import yusufcakal.com.stajtakip.R;
import yusufcakal.com.stajtakip.pojo.User;
import yusufcakal.com.stajtakip.webservices.interfaces.FragmentListener;
import yusufcakal.com.stajtakip.webservices.interfaces.PersonelEkleListener;
import yusufcakal.com.stajtakip.webservices.services.PersonelEkleService;

/**
 * Created by Yusuf on 21.05.2018.
 */

public class PersonelEkleFragment extends Fragment implements
        View.OnClickListener,
        PersonelEkleListener
    {

    private View view;
    private Button btnPersonelEkle;
    private EditText etPersonelMail, etPersonelSifre;
    private FragmentListener fragmentListener;

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
        view = inflater.inflate(R.layout.fragment_personel_ekle, container, false);

        etPersonelMail = view.findViewById(R.id.etPersonelMail);
        etPersonelSifre = view.findViewById(R.id.etPersonelSifre);
        btnPersonelEkle = view.findViewById(R.id.btnPersonelEkle);
        btnPersonelEkle.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.equals(btnPersonelEkle)){
            String mail = etPersonelMail.getText().toString();
            String sifre = etPersonelSifre.getText().toString();
            User user = new User();
            if (isEmailValid(mail)){
                user.setEmail(mail);
                user.setPassword(sifre);
                PersonelEkleService personelEkleService = new PersonelEkleService(getContext(), this);
                personelEkleService.personelEkle(user);
            }else{
                Toast.makeText(getContext(), "Lütfen doğru bir mail giriniz.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public void onSuccess(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            boolean resultFlag = jsonObject.getBoolean("result");
            if (resultFlag){
                Toast.makeText(getContext(), "Personel Başarı ile Eklendi.", Toast.LENGTH_SHORT).show();
                fragmentListener.onStart(new PersonelFragment());
            }else{
                Toast.makeText(getContext(), "Bir Hata Oluştu.", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(VolleyError error) {
        Toast.makeText(getContext(), String.valueOf(error), Toast.LENGTH_SHORT).show();
    }
}
