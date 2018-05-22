package yusufcakal.com.stajtakip.pojo;

/**
 * Created by Yusuf on 22.05.2018.
 */

public class Bolum {

    private int id;
    private String bolumAdi, fakulteAdi;

    public Bolum(int id, String bolumAdi, String fakulteAdi) {
        this.id = id;
        this.bolumAdi = bolumAdi;
        this.fakulteAdi = fakulteAdi;
    }

    public Bolum() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBolumAdi() {
        return bolumAdi;
    }

    public void setBolumAdi(String bolumAdi) {
        this.bolumAdi = bolumAdi;
    }

    public String getFakulteAdi() {
        return fakulteAdi;
    }

    public void setFakulteAdi(String fakulteAdi) {
        this.fakulteAdi = fakulteAdi;
    }
}
