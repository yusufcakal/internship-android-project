package yusufcakal.com.stajtakip.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Yusuf on 25.05.2018.
 */

public class Staj implements Parcelable {

    private int id, puan, sonuc, firmaId;
    private String baslangicTarihi, bitisTarihi, bolumAdi, firmaAdi;
    private String stajGunTarihi;
    private String stajyerEposta;

    public Staj() {}

    public Staj(int id, int puan, int sonuc, String baslangicTarihi, String bitisTarihi, String bolumAdi, String firmaAdi) {
        this.id = id;
        this.puan = puan;
        this.sonuc = sonuc;
        this.baslangicTarihi = baslangicTarihi;
        this.bitisTarihi = bitisTarihi;
        this.bolumAdi = bolumAdi;
        this.firmaAdi = firmaAdi;
    }

    protected Staj(Parcel in) {
        id = in.readInt();
        puan = in.readInt();
        sonuc = in.readInt();
        firmaId = in.readInt();
        baslangicTarihi = in.readString();
        bitisTarihi = in.readString();
        bolumAdi = in.readString();
        firmaAdi = in.readString();
    }

    public static final Creator<Staj> CREATOR = new Creator<Staj>() {
        @Override
        public Staj createFromParcel(Parcel in) {
            return new Staj(in);
        }

        @Override
        public Staj[] newArray(int size) {
            return new Staj[size];
        }
    };

    public String getStajyerEposta() {
        return stajyerEposta;
    }

    public void setStajyerEposta(String stajyerEposta) {
        this.stajyerEposta = stajyerEposta;
    }

    public String getStajGunTarihi() {
        return stajGunTarihi;
    }

    public void setStajGunTarihi(String stajGunTarihi) {
        this.stajGunTarihi = stajGunTarihi;
    }

    public int getPuan() {
        return puan;
    }

    public void setPuan(int puan) {
        this.puan = puan;
    }

    public int getSonuc() {
        return sonuc;
    }

    public void setSonuc(int sonuc) {
        this.sonuc = sonuc;
    }

    public String getBolumAdi() {
        return bolumAdi;
    }

    public void setBolumAdi(String bolumAdi) {
        this.bolumAdi = bolumAdi;
    }

    public String getFirmaAdi() {
        return firmaAdi;
    }

    public void setFirmaAdi(String firmaAdi) {
        this.firmaAdi = firmaAdi;
    }

    public int getFirmaId() {
        return firmaId;
    }

    public void setFirmaId(int firmaId) {
        this.firmaId = firmaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBaslangicTarihi() {
        return baslangicTarihi;
    }

    public void setBaslangicTarihi(String baslangicTarihi) {
        this.baslangicTarihi = baslangicTarihi;
    }

    public String getBitisTarihi() {
        return bitisTarihi;
    }

    public void setBitisTarihi(String bitisTarihi) {
        this.bitisTarihi = bitisTarihi;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(puan);
        parcel.writeInt(sonuc);
        parcel.writeInt(firmaId);
        parcel.writeString(baslangicTarihi);
        parcel.writeString(bitisTarihi);
        parcel.writeString(bolumAdi);
        parcel.writeString(firmaAdi);
    }
}
