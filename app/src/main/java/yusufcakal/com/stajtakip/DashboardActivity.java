package yusufcakal.com.stajtakip;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.util.Log;

import com.heinrichreimersoftware.materialdrawer.DrawerActivity;
import com.heinrichreimersoftware.materialdrawer.structure.DrawerItem;
import yusufcakal.com.stajtakip.fragments.FirmaEkleFragment;
import yusufcakal.com.stajtakip.webservices.util.SessionUtil;

public class DashboardActivity extends DrawerActivity {

    private ActionBar toolbar;

    private final String[] PAGE_TITLES = new String[] {
            "FİRMALAR",
            "FİRMA EKLE"
    };

    private final Fragment[] PAGES = new Fragment[] {
            new FirmaFragment(),
            new FirmaEkleFragment()
    };

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Log.e("TOKEN", SessionUtil.getToken(this));

        toolbar = getSupportActionBar();
        setTitle(getResources().getString(R.string.firmalar));

        mViewPager = findViewById(R.id.viewpager);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setTabTextColors(ColorStateList.valueOf(getResources().getColor(R.color.colorWhite)));
        tabLayout.setupWithViewPager(mViewPager);

        addItem(
                new DrawerItem()
                        .setImage(getResources().getDrawable(R.drawable.ic_home_black_24dp))
                        .setTextPrimary(getString(R.string.firma))
                        .setOnItemClickListener(new DrawerItem.OnItemClickListener() {
                            @Override
                            public void onClick(DrawerItem drawerItem, long id, int position) {
                                //startActivity(new Intent(DashboardActivity.this, FirmaActivity.class));
                                setTitle(getResources().getString(R.string.firmalar));
                                openFragment(new FirmaFragment());
                                DashboardActivity.this.closeDrawer();
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

    public class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return PAGES[position];
        }

        @Override
        public int getCount() {
            return PAGES.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return PAGE_TITLES[position];
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void openFragment(final Fragment fragment)   {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void setTitle(String title){
        toolbar.setTitle(title);
    }


}
