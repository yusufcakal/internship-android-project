package yusufcakal.com.stajtakip.pojo;

import java.util.List;

/**
 * Created by Yusuf on 2.06.2018.
 */

public class StajGun {

    private int stajGunId;
    private String aciklama;
    private List<String> resimler;

    public StajGun(int stajGunId, String aciklama, List<String> resimler) {
        this.stajGunId = stajGunId;
        this.aciklama = aciklama;
        this.resimler = resimler;
    }

    public StajGun() {}

    public int getStajGunId() {
        return stajGunId;
    }

    public void setStajGunId(int stajGunId) {
        this.stajGunId = stajGunId;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public List<String> getResimler() {
        return resimler;
    }

    public void setResimler(List<String> resimler) {
        this.resimler = resimler;
    }
}
