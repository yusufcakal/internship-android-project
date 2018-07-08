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

/**
 * Created by Yusuf on 21.05.2018.
 */

public class StajAdapter extends BaseAdapter {

    LayoutInflater layoutInflater;
    List<Staj> stajList;
    Context context;

    public StajAdapter(Context context , List<Staj> stajList){
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.stajList = stajList;
    }

    @Override
    public int getCount() {
        return stajList.size();
    }

    @Override
    public Object getItem(int i) {
        return stajList.get(i);
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
            view1 = layoutInflater.inflate(R.layout.staj_firma_item, null);
        } else {
            view1 = view;
        }

        TextView tvFirmaAdi = view1.findViewById(R.id.tvFirmaAdi);
        TextView tvBaslangicTarih = view1.findViewById(R.id.tvBaslangicTarih);
        TextView tvBitisTarih = view1.findViewById(R.id.tvBitisTarih);
        TextView tvPuan = view1.findViewById(R.id.tvPuan);
        TextView tvSonuc = view1.findViewById(R.id.tvSonuc);

        Staj staj = stajList.get(i);

        tvFirmaAdi.setText(staj.getFirmaAdi());
        tvBaslangicTarih.setText(staj.getBaslangicTarihi().split(" ")[0] + " - ");
        tvBitisTarih.setText(staj.getBitisTarihi().split(" ")[0]);
        if (staj.getSonuc() == 0){
            tvSonuc.setText("Firma Onayı Bekliyor");
        }else if (staj.getSonuc() == 4){
            tvSonuc.setText("Okul Sonuç Bekliyor");
        }else if (staj.getSonuc() == 1){
            tvSonuc.setText("Okul Onayı Bekliyor");
        }else if (staj.getSonuc() == -1){
            tvSonuc.setText("Firma Reddetti");
        }else if (staj.getSonuc() == -2){
            tvSonuc.setText("Okul Reddetti");
        }else if (staj.getSonuc() == 2){
            tvSonuc.setText("Detay Girilebilir");
        }else if (staj.getSonuc() == 3){
            tvSonuc.setText("Firma Sonuç Bekliyor");
        }else if (staj.getSonuc() == 5){
            tvSonuc.setText("Staj Tamamlandı");
        }

        if (staj.getPuan() != 0){
            tvPuan.setText("İş Devam Durumu: " + String.valueOf(staj.getPuan()).charAt(0) + "\n" + "Çalışma Gayreti: " + String.valueOf(staj.getPuan()).charAt(1) + "\n" + "Zamanında Yapma: " + String.valueOf(staj.getPuan()).charAt(2) + "\n" + "İşyerinde Tutumu: " + String.valueOf(staj.getPuan()).charAt(3) + "\n");
        }else{
            tvPuan.setText("Puan   : " + staj.getPuan());
        }

        return view1;


    }

}
