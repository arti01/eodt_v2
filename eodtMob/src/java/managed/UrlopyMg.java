package managed;

import encje.Config;
import encje.ConfigFacade;
import eodtwsc.WnUrlop;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;

@ManagedBean
@RequestScoped
public class UrlopyMg implements Serializable {

    private static final long serialVersionUID = 1L;
    private WnUrlop urlop;
    private boolean calyDzien;
    private Date godzOdT;
    private Date godzDoT;
    private Date dataUrlopu;
    private List<WnUrlop> listaUrlopow;
    @EJB
    private ConfigFacade cF;
    List<Config> lconf;
    
    @PostConstruct
    void init(){
        listaUrlopow=new test.Test().getLista();
        lconf=cF.findAll();
    }

    public String gotoUrlopWiecej() {
        lconf=cF.findAll();
        return "pm:urlopWiecej?transition=flip";
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

    public WnUrlop getUrlop() {
        return urlop;
    }

    public void setUrlop(WnUrlop urlop) {
        this.urlop = urlop;
    }

    public ConfigFacade getcF() {
        return cF;
    }

    public void setcF(ConfigFacade cF) {
        this.cF = cF;
    }

    public List<Config> getLconf() {
        return lconf;
    }

    public void setLconf(List<Config> lconf) {
        this.lconf = lconf;
    }

}
