package yusufcakal.com.stajtakip.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Yusuf on 2.06.2018.
 */

public class StajGun implements Parcelable{


    private int stajGunId;
    private int stajId;
    private String aciklama;
    private List<StajGunResim> stajGunResimList;
    private int firmaOnay, okulOnay;
    private String tarih;
    private String imagePath;
    private boolean isSelected = false;

    public StajGun(int stajGunId, int stajId, String aciklama, List<StajGunResim> stajGunResimList, int firmaOnay, int okulOnay, String tarih) {
        this.stajGunId = stajGunId;
        this.stajId = stajId;
        this.aciklama = aciklama;
        this.stajGunResimList = stajGunResimList;
        this.firmaOnay = firmaOnay;
        this.okulOnay = okulOnay;
        this.tarih = tarih;
    }

    public StajGun() {}

    protected StajGun(Parcel in) {
        stajGunId = in.readInt();
        stajId = in.readInt();
        aciklama = in.readString();
        firmaOnay = in.readInt();
        okulOnay = in.readInt();
        tarih = in.readString();
    }

    public static final Creator<StajGun> CREATOR = new Creator<StajGun>() {
        @Override
        public StajGun createFromParcel(Parcel in) {
            return new StajGun(in);
        }

        @Override
        public StajGun[] newArray(int size) {
            return new StajGun[size];
        }
    };

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public int getStajGunId() {
        return stajGunId;
    }

    public void setStajGunId(int stajGunId) {
        this.stajGunId = stajGunId;
    }

    public int getStajId() {
        return stajId;
    }

    public void setStajId(int stajId) {
        this.stajId = stajId;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public List<StajGunResim> getStajGunResimList() {
        return stajGunResimList;
    }

    public void setStajGunResimList(List<StajGunResim> stajGunResimList) {
        this.stajGunResimList = stajGunResimList;
    }

    public int getFirmaOnay() {
        return firmaOnay;
    }

    public void setFirmaOnay(int firmaOnay) {
        this.firmaOnay = firmaOnay;
    }

    public int getOkulOnay() {
        return okulOnay;
    }

    public void setOkulOnay(int okulOnay) {
        this.okulOnay = okulOnay;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(stajGunId);
        parcel.writeInt(stajId);
        parcel.writeString(aciklama);
        parcel.writeInt(firmaOnay);
        parcel.writeInt(okulOnay);
        parcel.writeString(tarih);
    }
}
