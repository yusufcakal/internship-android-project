package yusufcakal.com.stajtakip;

import android.content.Intent;
import android.os.Bundle;

import com.heinrichreimersoftware.materialdrawer.DrawerActivity;
import com.heinrichreimersoftware.materialdrawer.structure.DrawerItem;

import yusufcakal.com.stajtakip.webservices.util.SessionUtil;

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
                                startActivity(new Intent(DashboardActivity.this, FirmaActivity.class));
                            }
                        })
        );
        addDivider();
        addItem(
                new DrawerItem()
                        .setImage(getResources().getDrawable(R.drawable.ic_last_page_black_24dp))
                        .setTextPrimary(getString(R.string.logout))
                        .setOnItemClickListener(new DrawerItem.OnItemClickListener() {
                            @Override
                            public void onClick(DrawerItem drawerItem, long id, int position) {
                                SessionUtil.stop(DashboardActivity.this);
                                startActivity(new Intent(DashboardActivity.this, MainActivity.class));
                            }
                        })
        );

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
