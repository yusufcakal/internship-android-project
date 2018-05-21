package yusufcakal.com.stajtakip.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import yusufcakal.com.stajtakip.R;
import yusufcakal.com.stajtakip.pojo.Firma;

/**
 * Created by Yusuf on 21.05.2018.
 */

public class FirmaAdapter extends BaseAdapter {

    LayoutInflater layoutInflater;
    List<Firma> firmaList;
    Context context;

    public FirmaAdapter(Context context , List<Firma> firmaList){
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.firmaList = firmaList;
    }

    @Override
    public int getCount() {
        return firmaList.size();
    }

    @Override
    public Object getItem(int i) {
        return firmaList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView;
        rowView = layoutInflater.inflate(R.layout.firma_item , null);

        String firmaAdi = firmaList.get(i).getAdi();
        int onay = firmaList.get(i).getOnay();

        TextView tvFirmaName = rowView.findViewById(R.id.tvName);
        TextView tvFirmaOnay = rowView.findViewById(R.id.tvOnay);

        tvFirmaName.setText(firmaAdi);

        // -1 : Firma sahibinden onay bekliyor.
        // 0 : Okuldan onay bekliyor.
        // 1  Tamamen onaylandı.

        if (onay == -1){
            tvFirmaOnay.setText(context.getResources().getString(R.string.firmaOnayEksi1));
        }else if (onay == 0){
            tvFirmaOnay.setText(context.getResources().getString(R.string.firmaOnay0));
        }else{
            tvFirmaName.setText(context.getResources().getString(R.string.firmaOnayBir));
        }

        return rowView;


    }

}
