package yusufcakal.com.stajtakip.fragments.staj.stajgun;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.VolleyError;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.FileNotFoundException;
import java.io.InputStream;
import yusufcakal.com.stajtakip.R;
import yusufcakal.com.stajtakip.pojo.Staj;
import yusufcakal.com.stajtakip.pojo.StajGun;
import yusufcakal.com.stajtakip.webservices.interfaces.FragmentListener;
import yusufcakal.com.stajtakip.webservices.interfaces.StajGunEkleListener;
import yusufcakal.com.stajtakip.webservices.services.StajGunEkleService;

/**
 * Created by Yusuf on 21.05.2018.
 */

public class StajGunEkleFragment extends android.support.v4.app.Fragment
        implements View.OnClickListener,
        StajGunEkleListener{

    private View view;
    private Staj staj;
    private String tarih;
    private TextView tvStajGun;
    private EditText etAciklama;
    private Button btnStajGunEkleButon, btnResimEkle;
    private ImageView imResim;
    private Bitmap selectedImage;
    private StajGun stajGun;
    private ProgressDialog dialog;
    private FragmentListener fragmentListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentListener = (FragmentListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentListener = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_stajgunekle, container, false);

        staj = (Staj) getArguments().get("staj");
        tarih = staj.getStajGunTarihi();

        String year = staj.getStajGunTarihi().split("-")[0];
        String month = staj.getStajGunTarihi().split("-")[1];
        String day = staj.getStajGunTarihi().split("-")[2];

        month = checkDigit(Integer.parseInt(month));
        day = checkDigit(Integer.parseInt(day));

        String date = year + "-" + month + "-" + day;
        staj.setStajGunTarihi(date);

        tvStajGun = view.findViewById(R.id.tvStajGun);
        etAciklama = view.findViewById(R.id.etAciklama);

        btnStajGunEkleButon = view.findViewById(R.id.btnStajGunEkleButon);
        btnResimEkle = view.findViewById(R.id.btnResimEkle);
        imResim = view.findViewById(R.id.imResim);

        btnStajGunEkleButon.setOnClickListener(this);
        btnResimEkle.setOnClickListener(this);

        tvStajGun.setText(staj.getStajGunTarihi());


        stajGun = new StajGun();
        stajGun.setStajId(staj.getId());
        stajGun.setTarih(staj.getStajGunTarihi());

        return view;
    }

    public String checkDigit (int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

    @Override
    public void onClick(View view) {
        if (view == btnStajGunEkleButon){
            String aciklama = etAciklama.getText().toString();
            if (aciklama.equals("")){
                Toast.makeText(getContext(), "Boş Bırakmayınız.", Toast.LENGTH_SHORT).show();
            }else{
                stajGun.setAciklama(aciklama);
                StajGunEkleService stajGunEkleService = new StajGunEkleService(getContext(), this);
                stajGunEkleService.gunEkle(stajGun, selectedImage);
                dialog = ProgressDialog.show(getActivity(), "Yükleniyor..",
                        "Staj Günü Ekleniyor..", true);
            }
        }else if (view == btnResimEkle){
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false);
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent,"Select Picture"), 0);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                //Display an error
                return;
            }else{
                Uri imageUri = data.getData();
                imResim.setImageURI(imageUri);
                btnResimEkle.setText("Resim Değiştir");

                final InputStream imageStream;
                try {
                    assert imageUri != null;
                    imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                    selectedImage = BitmapFactory.decodeStream(imageStream);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    @Override
    public void onSuccess(String result) {
        Log.e("CEVAP", result);
        try {
            JSONObject jsonObject = new JSONObject(result);
            boolean resultFlag = jsonObject.getBoolean("result");
            if (!resultFlag){
                String error = jsonObject.getString("error"); 
                Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getActivity(), "Başarı ile eklendi.", Toast.LENGTH_SHORT).show();
            }
            fragmentListener.onStart(new StajGunlerFragment());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        dialog.cancel();
        dialog.dismiss();
    }

    @Override
    public void onError(VolleyError error) {
        Toast.makeText(getActivity(), String.valueOf(error), Toast.LENGTH_SHORT).show();
        dialog.cancel();
        dialog.dismiss();
    }
}
