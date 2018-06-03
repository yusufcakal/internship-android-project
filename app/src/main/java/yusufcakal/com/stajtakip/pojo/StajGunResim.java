package yusufcakal.com.stajtakip.pojo;

/**
 * Created by Yusuf on 3.06.2018.
 */

public class StajGunResim {

    private int resimId;
    private String resim;

    public StajGunResim(int resimId, String resim) {
        this.resimId = resimId;
        this.resim = resim;
    }

    public StajGunResim() {}

    public int getResimId() {
        return resimId;
    }

    public void setResimId(int resimId) {
        this.resimId = resimId;
    }

    public String getResim() {
        return resim;
    }

    public void setResim(String resim) {
        this.resim = resim;
    }

}
