package yusufcakal.com.stajtakip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.VolleyError;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import yusufcakal.com.stajtakip.pojo.Bolum;
import yusufcakal.com.stajtakip.pojo.User;
import yusufcakal.com.stajtakip.webservices.interfaces.LoginListener;
import yusufcakal.com.stajtakip.webservices.services.LoginService;
import yusufcakal.com.stajtakip.webservices.util.LinkUtil;
import yusufcakal.com.stajtakip.webservices.util.SessionUtil;
import yusufcakal.com.stajtakip.webservices.util.SharedPrefsUtils;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener, LoginListener{

    private EditText etEmail, etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int rutbe = SharedPrefsUtils.getIntegerPreference(this, LinkUtil.RUTBE , -1);

        if (SessionUtil.check(this)){
            if (rutbe == 1){
                startActivity(new Intent(this, OgrenciDashboardActivity.class));
            }else if (rutbe == 2){
                startActivity(new Intent(this, FirmaDashboardActivity.class));
            }
        }

        setTitle(getResources().getText(R.string.login));

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        
        btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        loginService(user);

    }

    private void loginService(User user){
        LoginService loginService = new LoginService(this, this);
        loginService.login(user);
    }

    @Override
    public void onSuccess(String result) {

        try {
            JSONObject jsonObject = new JSONObject(result);
            boolean loginFlag = jsonObject.getBoolean("result");
            if (loginFlag){
                JSONObject info = jsonObject.getJSONObject("bilgiler");
                String token = info.getString("token");
                int rutbe = info.getInt("rutbe");
                int userId = info.getInt("id");

                List<Bolum> bolumList = new ArrayList<>();

                JSONArray bolumlerArray = info.getJSONArray("bolumler");

                for (int i=0; i<bolumlerArray.length(); i++){
                    JSONObject bolumObject = bolumlerArray.getJSONObject(i);
                    String bolumAdi = bolumObject.getString("bolum_adi");
                    String fakulteAdi = bolumObject.getString("fakulte_adi");
                    int bolum_id = bolumObject.getInt("bolum_id");
                    Bolum bolum1 = new Bolum(bolum_id, bolumAdi, fakulteAdi);
                    bolumList.add(bolum1);
                }

                SessionUtil.start(this, token);

                if (rutbe == 1){

                    String bolum = bolumlerArray.getJSONObject(0).getString("bolum_adi");
                    SessionUtil.setBolum(getApplicationContext() , bolum);

                    String isim = info.getString("ad_soyad");
                    String sifre = info.getString("sifre");
                    String resim = info.getString("resim");

                    SessionUtil.setIsim(getApplicationContext() , isim);
                    SessionUtil.setSifre(getApplicationContext() , sifre);
                    SessionUtil.setResim(getApplicationContext() , resim);

                    SharedPrefsUtils.setIntegerPreference(this, LinkUtil.USER_ID, userId);
                    SharedPrefsUtils.setIntegerPreference(getApplicationContext(), LinkUtil.BOLUM_ID, bolumList.get(0).getId());
                    startActivity(new Intent(this, OgrenciDashboardActivity.class));
                }else if (rutbe == 2){
                    JSONArray firmalarArray = info.getJSONArray("firmalar");
                    JSONObject object = firmalarArray.getJSONObject(0);
                    SharedPrefsUtils.setIntegerPreference(this, LinkUtil.USER_ID, object.getInt("firma_id"));
                    startActivity(new Intent(this, FirmaDashboardActivity.class));
                }else if (rutbe == 3){
                    /**
                     * TODO : Personel stajı
                     */
                    Toast.makeText(this, "Personel Girişi", Toast.LENGTH_SHORT).show();
                }
                SharedPrefsUtils.setIntegerPreference(getApplicationContext(), LinkUtil.RUTBE, rutbe);
            }else{
                String error = jsonObject.getString("error");
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(VolleyError error) {
        Toast.makeText(this, String.valueOf(error), Toast.LENGTH_SHORT).show();
    }
}
