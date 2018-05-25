package yusufcakal.com.stajtakip.pojo;

import java.util.Date;

/**
 * Created by Yusuf on 25.05.2018.
 */

public class Staj {

    private int id, kullaniciId, firmaId, puan, sonuc, bolum_id;
    private String baslangicTarihi, bitisTarihi;

    public Staj() {}

    public Staj(int id, int kullaniciId, int firmaId, int puan, int sonuc, int bolum_id, String baslangicTarihi, String bitisTarihi) {
        this.id = id;
        this.kullaniciId = kullaniciId;
        this.firmaId = firmaId;
        this.puan = puan;
        this.sonuc = sonuc;
        this.bolum_id = bolum_id;
        this.baslangicTarihi = baslangicTarihi;
        this.bitisTarihi = bitisTarihi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKullaniciId() {
        return kullaniciId;
    }

    public void setKullaniciId(int kullaniciId) {
        this.kullaniciId = kullaniciId;
    }

    public int getFirmaId() {
        return firmaId;
    }

    public void setFirmaId(int firmaId) {
        this.firmaId = firmaId;
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

    public int getBolum_id() {
        return bolum_id;
    }

    public void setBolum_id(int bolum_id) {
        this.bolum_id = bolum_id;
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
}
