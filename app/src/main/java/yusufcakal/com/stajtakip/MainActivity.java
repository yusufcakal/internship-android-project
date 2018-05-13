package yusufcakal.com.stajtakip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import yusufcakal.com.stajtakip.presentation.LoginServiceImpl;
import yusufcakal.com.stajtakip.webservices.util.SessionUtil;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener{

    private EditText etEmail, etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (SessionUtil.check(this))
            startActivity(new Intent(this, DashboardActivity.class));

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
        LoginServiceImpl loginService = new LoginServiceImpl();
        loginService.loginSucces(this, user);
        if (SessionUtil.check(this)){
            Toast.makeText(this, "Giriş Yapıldı", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "hatalı giriş", Toast.LENGTH_SHORT).show();
        }

    }

    
}
