package yusufcakal.com.stajtakip;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.Toast;

import com.heinrichreimersoftware.materialdrawer.DrawerActivity;
import com.heinrichreimersoftware.materialdrawer.structure.DrawerItem;
import com.heinrichreimersoftware.materialdrawer.structure.DrawerProfile;

import yusufcakal.com.stajtakip.webservices.util.LinkUtil;
import yusufcakal.com.stajtakip.webservices.util.SharedPrefsUtils;

public class DashboardActivity extends DrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        addItem(
                new DrawerItem()
                        .setImage(getResources().getDrawable(R.drawable.ic_home_black_24dp))
                        .setTextPrimary(getString(R.string.firmalar))
                        .setOnItemClickListener(new DrawerItem.OnItemClickListener() {
                            @Override
                            public void onClick(DrawerItem drawerItem, long id, int position) {
                                Toast.makeText(DashboardActivity.this, "Firmalar" + id, Toast.LENGTH_SHORT).show();
                            }
                        })
        );
        
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SharedPrefsUtils.setStringPreference(this, LinkUtil.SESSION_KEY, null);
    }
}
