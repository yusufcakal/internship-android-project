package yusufcakal.com.stajtakip.fragments.firma;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import yusufcakal.com.stajtakip.R;
import yusufcakal.com.stajtakip.pojo.Firma;

/**
 * Created by Yusuf on 21.05.2018.
 */

public class FirmaIslemleriFragment extends android.support.v4.app.Fragment {

    private View view;
    private List<Firma> firmaList;
    private ListView lvFirma;

    private final String[] PAGE_TITLES = new String[] {
            "FİRMALAR",
            "FİRMA EKLE"
    };

    private final Fragment[] PAGES = new Fragment[] {
            new FirmalarFragment(),
            new FirmaEkleFragment()
    };

    private ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_firma_islemleri, container, false);

        lvFirma = view.findViewById(R.id.lvFirma);
        firmaList = new ArrayList<>();

        mViewPager = view.findViewById(R.id.viewpager);
        mViewPager.setAdapter(new MyPagerAdapter(getActivity().getSupportFragmentManager()));

        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.setTabTextColors(ColorStateList.valueOf(getResources().getColor(R.color.colorWhite)));
        tabLayout.setupWithViewPager(mViewPager);

        Firma firma1 = new Firma(-1, "Koç Sistem");
        Firma firma2 = new Firma(0, "Iyzico");
        Firma firma3 = new Firma(1, "Twentify");

        firmaList.add(firma1);
        firmaList.add(firma2);
        firmaList.add(firma3);

        return view;
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

}
