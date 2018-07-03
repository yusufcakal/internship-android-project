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
                startActivity(new Intent(this, DashboardActivity.class));
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
                SharedPrefsUtils.setIntegerPreference(this, LinkUtil.USER_ID, userId);

                List<Bolum> bolumList = new ArrayList<>();

                JSONArray bolumlerArray = info.getJSONArray("bolumler");
                for (int i=0; i<bolumlerArray.length(); i++){
                    JSONObject bolumObject = bolumlerArray.getJSONObject(i);
                    String bolumAdi = bolumObject.getString("bolum_adi");
                    String fakulteAdi = bolumObject.getString("fakulte_adi");
                    int bolum_id = bolumObject.getInt("bolum_id");
                    Bolum bolum = new Bolum(bolum_id, bolumAdi, fakulteAdi);
                    bolumList.add(bolum);
                }

                SessionUtil.start(this, token);

                if (rutbe == 1){
                    SharedPrefsUtils.setIntegerPreference(getApplicationContext(), LinkUtil.BOLUM_ID, bolumList.get(0).getId());
                    startActivity(new Intent(this, DashboardActivity.class));
                }else if (rutbe == 2){
                    startActivity(new Intent(this, FirmaDashboardActivity.class));
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
