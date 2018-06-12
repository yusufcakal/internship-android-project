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

    }
}
