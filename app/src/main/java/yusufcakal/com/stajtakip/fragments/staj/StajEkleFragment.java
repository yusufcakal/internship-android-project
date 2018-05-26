package yusufcakal.com.stajtakip.fragments.staj;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import yusufcakal.com.stajtakip.R;

/**
 * Created by Yusuf on 21.05.2018.
 */

public class StajEkleFragment extends android.support.v4.app.Fragment{

    private View view;
    private Spinner spinner;
    private static final String[] firmalar = {"Firma 1", "Firma 2", "Firma 3"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_stajekle, container, false);

        spinner = view.findViewById(R.id.spinner);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, firmalar);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        return view;
    }

}
