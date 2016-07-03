package managed;

import eodtwsc.WnUrlop;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import obiekty.Urlop;

@ManagedBean
@SessionScoped
public class UrlopyMg implements Serializable {

    private static final long serialVersionUID = 1L;
    private Urlop urlop;
    private boolean calyDzien;
    private Date godzOdT;
    private Date godzDoT;
    private Date dataUrlopu;
    private List<WnUrlop> listaUrlopow;
    
    @PostConstruct
    void init(){
        listaUrlopow=new test.Test().getLista();
    }

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

    public List<WnUrlop> getListaUrlopow() {
        return listaUrlopow;
    }

    public void setListaUrlopow(List<WnUrlop> listaUrlopow) {
        this.listaUrlopow = listaUrlopow;
    }

}
