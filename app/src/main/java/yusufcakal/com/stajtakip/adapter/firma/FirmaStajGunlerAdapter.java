package yusufcakal.com.stajtakip.adapter.firma;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import yusufcakal.com.stajtakip.R;
import yusufcakal.com.stajtakip.pojo.StajGun;

/**
 * Created by Yusuf on 21.05.2018.
 */

public class FirmaStajGunlerAdapter extends BaseAdapter {

    LayoutInflater layoutInflater;
    List<StajGun> stajGunList;
    Context context;

    public FirmaStajGunlerAdapter(Context context , List<StajGun> stajGunList){
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

    public void refreshData(List<StajGun> stajGunList){
        this.stajGunList = stajGunList;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1;

        if (view == null) {
            view1 = new View(context);
            view1 = layoutInflater.inflate(R.layout.firma_staj_gunler_item, null);
        } else {
            view1 = view;
        }

        TextView tvAciklama = view1.findViewById(R.id.tvAciklama);
        TextView tvStajGunTarih = view1.findViewById(R.id.tvStajGunTarih);
        TextView tvFirmaOnay = view1.findViewById(R.id.tvFirmaOnay);
        TextView tvOkulOnay = view1.findViewById(R.id.tvOkulOnay);
        ImageView imItemSelect = view1.findViewById(R.id.imItemSelect);

        StajGun stajGun = stajGunList.get(i);

        if (stajGun.isSelected()){
            imItemSelect.setBackgroundResource(R.drawable.checked);
        }else{
            imItemSelect.setBackgroundResource(R.drawable.check);
        }

        if (stajGun.getAciklama().length() >= 40){
            tvAciklama.setText(stajGun.getAciklama().substring(0, 20)+ "..");
        }else{
            tvAciklama.setText(stajGun.getAciklama());
        }
;
        tvStajGunTarih.setText(stajGun.getTarih().split(" ")[0]);
        tvFirmaOnay.setText("Firma Onay : " + stajGun.getFirmaOnay());
        tvOkulOnay.setText("Okul Onay : " + stajGun.getOkulOnay());
        tvFirmaOnay.setVisibility(View.GONE);
        tvOkulOnay.setVisibility(View.GONE);

        return view1;

    }

}
