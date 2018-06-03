package yusufcakal.com.stajtakip.adapter.firma;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import yusufcakal.com.stajtakip.R;
import yusufcakal.com.stajtakip.pojo.Staj;
import yusufcakal.com.stajtakip.pojo.StajGun;

/**
 * Created by Yusuf on 21.05.2018.
 */

public class StajGunlerAdapter extends BaseAdapter {

    LayoutInflater layoutInflater;
    List<StajGun> stajGunList;
    Context context;

    public StajGunlerAdapter(Context context , List<StajGun> stajGunList){
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.stajGunList = stajGunList;
    }

    @Override
    public int getCount() {
        return stajGunList.size();
    }

    @Override
    public Object getItem(int i) {
        return stajGunList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1;

        if (view == null) {
            view1 = new View(context);
            view1 = layoutInflater.inflate(R.layout.staj_gunler_item, null);
        } else {
            view1 = view;
        }

        TextView tvAciklama = view1.findViewById(R.id.tvAciklama);
        TextView tvStajGunTarih = view1.findViewById(R.id.tvStajGunTarih);
        TextView tvFirmaOnay = view1.findViewById(R.id.tvFirmaOnay);
        TextView tvOkulOnay = view1.findViewById(R.id.tvOkulOnay);

        StajGun stajGun = stajGunList.get(i);

        tvAciklama.setText(stajGun.getAciklama());
        tvStajGunTarih.setText(stajGun.getTarih());
        tvFirmaOnay.setText("Firma Onay : " + stajGun.getFirmaOnay());
        tvOkulOnay.setText("Okul Onay : " + stajGun.getOkulOnay());

        return view1;


    }

}
