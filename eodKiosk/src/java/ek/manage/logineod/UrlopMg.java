/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ek.manage.logineod;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import pl.eod.encje.WnRodzajeJpaController;
import pl.eod.encje.WnUrlop;
import pl.eod.encje.WnUrlopJpaController;

@ManagedBean(name = "UrlopMg")
@SessionScoped
public class UrlopMg implements Serializable {

    private static final long serialVersionUID = 1L;
    WnUrlop urlop = new WnUrlop();
    WnUrlopJpaController urlC;
    WnRodzajeJpaController rodzajeC;
    List<WnUrlop> list = new ArrayList<>();
    @ManagedProperty(value = "#{LoginMg}")
    LoginMg loginMg;
    private Date godzOdT;
    private Date godzDoT;
    private Date dataUrlopu;

    @PostConstruct
    public void init() {
        urlop = new WnUrlop();
        urlC = new WnUrlopJpaController();
        rodzajeC = new WnRodzajeJpaController();
        if (loginMg.getUser() != null) {
            list = loginMg.getUser().getWnUrlopList();
            urlop.setUzytkownik(loginMg.getUser());
        }
    }

    public void dodaj() throws ParseException {
        FacesContext context = FacesContext.getCurrentInstance();
        UIComponent zapisz = UIComponent.getCurrentComponent(context);
        FacesMessage message = new FacesMessage();
        String error = urlC.dodajUrlop(urlop, dataUrlopu, godzOdT, godzDoT);
        if (error != null) {
            message.setSummary(error);
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
        } else {
            loginMg.init();
            init();
            message.setSummary("wniosek zapisany");
            message.setSeverity(FacesMessage.SEVERITY_INFO);
        }
        context.addMessage(zapisz.getClientId(context), message);
    }

    public void usun() {
        urlC.destroy(urlop);
        FacesContext context = FacesContext.getCurrentInstance();
        UIComponent zapisz = UIComponent.getCurrentComponent(context);
        FacesMessage message = new FacesMessage();
        message.setSummary("wniosek usunięty");
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        context.addMessage(zapisz.getClientId(context), message);
        loginMg.init();
        init();
    }

    public void wyslij() {
        String info;
        info = urlC.wyslijUrlop(urlop);
        loginMg.init();
        init();
        FacesContext context = FacesContext.getCurrentInstance();
        UIComponent zapisz = UIComponent.getCurrentComponent(context);
        FacesMessage message = new FacesMessage();
        message.setSummary(info);
        context.addMessage(zapisz.getClientId(context), message);
    }
    
    public void anuluj() {
        String info;
        info = urlC.anulujUrlop(urlop);
        loginMg.init();
        init();
        FacesContext context = FacesContext.getCurrentInstance();
        UIComponent zapisz = UIComponent.getCurrentComponent(context);
        FacesMessage message = new FacesMessage();
        message.setSummary(info);
        context.addMessage(zapisz.getClientId(context), message);
    }

    public String lista() {
        init();
        return "/logineod/urlop_list";
    }

    public WnUrlop getUrlop() {
        return urlop;
    }

    public void setUrlop(WnUrlop urlop) {
        this.urlop = urlop;
    }

    public List<WnUrlop> getList() {
        return list;
    }

    public void setList(List<WnUrlop> list) {
        this.list = list;
    }

    public LoginMg getLoginMg() {
        return loginMg;
    }

    public void setLoginMg(LoginMg loginMg) {
        this.loginMg = loginMg;
    }

    public WnRodzajeJpaController getRodzajeC() {
        return rodzajeC;
    }

    public void setRodzajeC(WnRodzajeJpaController rodzajeC) {
        this.rodzajeC = rodzajeC;
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
