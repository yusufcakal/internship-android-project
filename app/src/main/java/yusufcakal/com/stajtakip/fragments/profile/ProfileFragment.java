package yusufcakal.com.stajtakip.fragments.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import yusufcakal.com.stajtakip.R;
import yusufcakal.com.stajtakip.webservices.util.SessionUtil;

/**
 * Created by Yusuf on 21.05.2018.
 */

public class ProfileFragment extends Fragment implements View.OnClickListener{

    private View view;
    private ImageView imResim, imEdit;
    private TextView tvIsim, tvBolum, tvSifre;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        tvIsim = view.findViewById(R.id.tvIsim);
        tvBolum = view.findViewById(R.id.tvBolum);
        tvSifre = view.findViewById(R.id.tvSifre);
        imResim = view.findViewById(R.id.imResim);
        imEdit = view.findViewById(R.id.imEdit);
        imEdit.setOnClickListener(this);

        try {
            tvIsim.setText(SessionUtil.getIsim(getContext()));
            tvBolum.setText(SessionUtil.getBolum(getContext()));
            tvSifre.setText(SessionUtil.getSifre(getContext()));
            Picasso.get().load(SessionUtil.getResim(getContext())).into(imResim);
        }catch (Exception e){
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(getContext(), "Düzenle", Toast.LENGTH_SHORT).show();
    }
    
}
