package yusufcakal.com.stajtakip.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yusufcakal.com.stajtakip.R;

/**
 * Created by Yusuf on 21.05.2018.
 */

public class FirmaEkleFragment extends android.support.v4.app.Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_firmaekle, container, false);



        return view;
    }
}
