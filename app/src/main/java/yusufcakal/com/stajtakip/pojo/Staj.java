package yusufcakal.com.stajtakip.pojo;

import java.util.Date;

/**
 * Created by Yusuf on 25.05.2018.
 */

public class Staj {

    private int id, puan, sonuc, firmaId;
    private String baslangicTarihi, bitisTarihi, bolumAdi, firmaAdi;

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
}
