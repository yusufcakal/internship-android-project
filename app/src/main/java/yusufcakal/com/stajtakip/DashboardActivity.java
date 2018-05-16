package yusufcakal.com.stajtakip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import yusufcakal.com.stajtakip.webservices.util.LinkUtil;
import yusufcakal.com.stajtakip.webservices.util.SharedPrefsUtils;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        setTitle(getResources().getText(R.string.my_internship));
        
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SharedPrefsUtils.setStringPreference(this, LinkUtil.SESSION_KEY, null);
    }
}
