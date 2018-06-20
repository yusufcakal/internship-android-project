package yusufcakal.com.stajtakip.adapter.personel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import yusufcakal.com.stajtakip.R;
import yusufcakal.com.stajtakip.pojo.User;

/**
 * Created by Yusuf on 21.05.2018.
 */

public class PersonelAdapter extends BaseAdapter {

    LayoutInflater layoutInflater;
    List<User> userList;
    Context context;

    public PersonelAdapter(Context context , List<User> userList){
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int i) {
        return userList.get(i);
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
            view1 = layoutInflater.inflate(R.layout.firma_personel_item, null);
        } else {
            view1 = view;
        }

        String email = userList.get(i).getEmail();
        TextView tvPersonelEmail = view1.findViewById(R.id.tvPersonelEmail);

        tvPersonelEmail.setText(email);

        return view1;


    }

}
