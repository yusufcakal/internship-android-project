package yusufcakal.com.stajtakip.adapter.firma;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import yusufcakal.com.stajtakip.pojo.Firma;

/**
 * Created by Yusuf on 27.05.2018.
 */

public class CustomSpinnerAdapter extends ArrayAdapter<Firma>{

    private Context context;
    private List<Firma> firmaList;

    public CustomSpinnerAdapter(Context context, int textViewResourceId,
                       List<Firma> firmaList) {
        super(context, textViewResourceId, firmaList);
        this.context = context;
        this.firmaList = firmaList;
    }

    @Override
    public int getCount(){
        return firmaList.size();
    }

    @Override
    public Firma getItem(int position){
        return firmaList.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setText(firmaList.get(position).getAdi());
        return label;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView tvFirmaAdi = (TextView) super.getDropDownView(position, convertView, parent);
        tvFirmaAdi.setTextAppearance(android.R.style.TextAppearance_Medium);
        tvFirmaAdi.setTextColor(Color.BLACK);
        tvFirmaAdi.setText(firmaList.get(position).getAdi());
        return tvFirmaAdi;
    }
}