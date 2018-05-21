package yusufcakal.com.stajtakip.fragments.firma;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import yusufcakal.com.stajtakip.R;
import yusufcakal.com.stajtakip.adapter.firma.FirmaAdapter;
import yusufcakal.com.stajtakip.pojo.Firma;

/**
 * Created by Yusuf on 21.05.2018.
 */

public class FirmalarFragment extends Fragment {

    private View view;
    private List<Firma> firmaList;
    private ListView lvFirma;
    private FirmaAdapter firmaAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_firmalar, container, false);

        lvFirma = view.findViewById(R.id.lvFirma);
        firmaList = new ArrayList<>();

        Firma firma1 = new Firma(-1, "Koç Sistem");
        Firma firma2 = new Firma(0, "Iyzico");
        Firma firma3 = new Firma(1, "Twentify");

        firmaList.add(firma1);
        firmaList.add(firma2);
        firmaList.add(firma3);

        firmaAdapter = new FirmaAdapter(getContext(), firmaList);
        lvFirma.setAdapter(firmaAdapter);

        return view;
    }
}
