package managed;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import obiekty.Urlop;


@ManagedBean
@SessionScoped
public class UrlopyMg {
private Urlop urlop;
private boolean calyDzien;
private Date godzOdT;
private Date godzDoT;
private Date dataUrlopu;

    public Urlop getUrlop() {
        return urlop;
    }

    public void setUrlop(Urlop urlop) {
        this.urlop = urlop;
    }

    public boolean isCalyDzien() {
        return calyDzien;
    }

    public void setCalyDzien(boolean calyDzien) {
        this.calyDzien = calyDzien;
    }

    public Date getGodzOdT() {
        return godzOdT;
    }

    public void setGodzOdT(Date godzOdT) {
        this.godzOdT = godzOdT;
    }

    public Date getGodzDoT() {
        return godzDoT;
    }

    public void setGodzDoT(Date godzDoT) {
        this.godzDoT = godzDoT;
    }

    public Date getDataUrlopu() {
        return dataUrlopu;
    }

    public void setDataUrlopu(Date dataUrlopu) {
        this.dataUrlopu = dataUrlopu;
    }
}
