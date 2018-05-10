package yusufcakal.com.stajtakip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import yusufcakal.com.stajtakip.webservices.services.LoginService;

public class MainActivity extends AppCompatActivity 
    implements View.OnClickListener{

    private EditText etEmail, etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        loginService(email, password);

    }

    private void loginService(String email, String password){
        LoginService loginService = new LoginService(this);
    }

    
}
