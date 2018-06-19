package yusufcakal.com.stajtakip.fragments.personel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import yusufcakal.com.stajtakip.R;

/**
 * Created by Yusuf on 21.05.2018.
 */

public class PersonelFragment extends Fragment{

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_personel, container, false);


        return view;
    }

}
