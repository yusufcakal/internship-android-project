package yusufcakal.com.stajtakip.pojo;

/**
 * Created by Yusuf on 21.05.2018.
 */

public class Firma {

    private int id, onay;
    private String adi;

    public Firma(int onay, String adi) {
        this.onay = onay;
        this.adi = adi;
    }

    public Firma() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOnay() {
        return onay;
    }

    public void setOnay(int onay) {
        this.onay = onay;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

}
