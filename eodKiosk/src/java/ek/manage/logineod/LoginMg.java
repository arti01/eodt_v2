/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ek.manage.logineod;

import ek.encje.EkConfigKontr;
import ek.encje.EkLog;
import ek.encje.EkLogKontr;
import ek.encje.UserProfil;
import ek.encje.UserProfilKontr;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import pl.eod.encje.Uzytkownik;
import pl.eod.encje.UzytkownikJpaController;
import pl.esoftking.ekiosk.app.KioskManager;

@ManagedBean(name = "LoginMg")
@SessionScoped
public class LoginMg implements Serializable {

    private static final long serialVersionUID = 1L;
    private UserProfil userProfil;
    private final UserProfilKontr uPK = new UserProfilKontr();
    private UzytkownikJpaController userC;
    private Uzytkownik user;
    private String iddle;
    String stanNadgodzin;
    String stanUrlopow;
    KioskManager km;

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        userProfil = uPK.findByCardno(request.getUserPrincipal().getName());
        userC = new UzytkownikJpaController();
        user = userC.findUzytkownik(userProfil.getEodId().longValue());
        EkConfigKontr confC = new EkConfigKontr();
        Long iddL = new Long(confC.findEntities("iddle").getWartosc());
        this.iddle = Long.toString(iddL * 60 * 1000);
        km = new KioskManager();
        stanNadgodzin = km.pobierzStanNadgodzin("10-2016", new Long(2085));
        stanUrlopow = km.pobierzStanUrlopow(2016, new Long(2085));
        EkLog log=new EkLog();
        log.setNazwa("logowanie");
        log.setOpis(userProfil.getCardno());
        new EkLogKontr().create(log);
    }

    public String logOut() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        //FacesContext.getCurrentInstance().getExternalContext().redirect("/");
        return "/common/index.xhtml";
    }

    public UserProfil getUserProfil() {
        return userProfil;
    }

    public Uzytkownik getUser() {
        return user;
    }

    public void setUser(Uzytkownik user) {
        this.user = user;
    }

    public String getIddle() {
        return iddle;
    }

    public void setIddle(String iddle) {
        this.iddle = iddle;
    }

    public String getStanNadgodzin() {
        return stanNadgodzin;
    }

    public void setStanNadgodzin(String stanNadgodzin) {
        this.stanNadgodzin = stanNadgodzin;
    }

    public String getStanUrlopow() {
        return stanUrlopow;
    }

    public void setStanUrlopow(String stanUrlopow) {
        this.stanUrlopow = stanUrlopow;
    }

}
