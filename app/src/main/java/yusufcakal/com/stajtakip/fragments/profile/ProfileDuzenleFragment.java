package yusufcakal.com.stajtakip.fragments.profile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import yusufcakal.com.stajtakip.R;
import yusufcakal.com.stajtakip.webservices.interfaces.FragmentListener;
import yusufcakal.com.stajtakip.webservices.interfaces.OgrenciProfilDuzenleListener;
import yusufcakal.com.stajtakip.webservices.services.OgrenciProfilDuzenleService;
import yusufcakal.com.stajtakip.webservices.util.ImageFilePath;
import yusufcakal.com.stajtakip.webservices.util.SessionUtil;

/**
 * Created by Yusuf on 21.05.2018.
 */

public class ProfileDuzenleFragment extends Fragment implements View.OnClickListener , OgrenciProfilDuzenleListener{

    private View view;
    private Button btnChangeSave;
    private ImageView imResimEdit;
    private EditText etIsimEdit, etSifreEdit;
    private Bitmap selectedImage;
    private String isim, sifre;
    private FragmentListener fragmentListener;
    private TextView tvBolumEdit;
    private String imageName;

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
        view = inflater.inflate(R.layout.fragment_profile_duzenle, container, false);

        btnChangeSave = view.findViewById(R.id.btnChangeSave);
        imResimEdit = view.findViewById(R.id.imResimEdit);
        tvBolumEdit = view.findViewById(R.id.tvBolumEdit);
        btnChangeSave.setOnClickListener(this);
        imResimEdit.setOnClickListener(this);

        Picasso.get().load(SessionUtil.getResim(getContext())).into(imResimEdit);

        etIsimEdit = view.findViewById(R.id.etIsimEdit);
        etSifreEdit = view.findViewById(R.id.etSifreEdit);
        tvBolumEdit.setText(SessionUtil.getBolum(getContext()));
        etIsimEdit.setText(SessionUtil.getIsim(getContext()));
        etSifreEdit.setText(SessionUtil.getSifre(getContext()));

        return view;
    }


    @Override
    public void onClick(View view) {

        if (view == imResimEdit){
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false);
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent,"Select Picture"), 0);
        }else{

            isim = etIsimEdit.getText().toString();
            sifre = etSifreEdit.getText().toString();

            OgrenciProfilDuzenleService ogrenciProfilDuzenleService = new OgrenciProfilDuzenleService(getContext(), this);
            ogrenciProfilDuzenleService.duzenle(isim, sifre, selectedImage);

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
                imResimEdit.setImageURI(imageUri);

                String realPath = ImageFilePath.getPath(getContext(), data.getData());

                File file = new File(realPath);
                imageName = file.getName();
                Toast.makeText(getContext(), imageName, Toast.LENGTH_SHORT).show();

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

        Log.e("RESIM", result);

        try {
            JSONObject jsonObject = new JSONObject(result);
            boolean resultFlag = jsonObject.getBoolean("result");
            if (resultFlag){
                SessionUtil.setIsim(getContext(), isim);
                SessionUtil.setSifre(getContext(), sifre);

                String newImage = jsonObject.getString("resim");
                if (!newImage.equals("")){
                    SessionUtil.setResim(getContext(),newImage);
                }

                fragmentListener.onStart(new ProfileFragment());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(VolleyError error) {
        Toast.makeText(getContext(), String.valueOf(error), Toast.LENGTH_SHORT).show();
    }
}
