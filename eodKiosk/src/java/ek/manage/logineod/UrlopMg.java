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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;
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
    private boolean widokKalData1=false;
    private boolean widokKalData2=false;
    private boolean wprowadzanie=false;

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

    public void dodaj() {
        FacesContext context = FacesContext.getCurrentInstance();
        UIComponent zapisz = UIComponent.getCurrentComponent(context);
        FacesMessage message = new FacesMessage();
        String error = null;
        try {
            error = urlC.dodajUrlop(urlop, dataUrlopu, godzOdT, godzDoT);
        } catch (ParseException ex) {
            Logger.getLogger(UrlopMg.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (error != null) {
            message.setSummary(error);
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
        } else {
            loginMg.init();
            init();
            message.setSummary("wniosek zapisany");
            message.setSeverity(FacesMessage.SEVERITY_INFO);
        }
        wprowadzanie=false;
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

    public void ustawData1(){
        widokKalData1=false;
    }
    
    public void ustawData2(){
        widokKalData2=false;
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

    public boolean isWidokKalData1() {
        return widokKalData1;
    }

    public void setWidokKalData1(boolean widokKalData1) {
        this.widokKalData1 = widokKalData1;
    }

    public boolean isWidokKalData2() {
        return widokKalData2;
    }

    public void setWidokKalData2(boolean widokKalData2) {
        this.widokKalData2 = widokKalData2;
    }

    public boolean isWprowadzanie() {
        return wprowadzanie;
    }

    public void setWprowadzanie(boolean wprowadzanie) {
        System.err.println(wprowadzanie);
        this.wprowadzanie = wprowadzanie;
    }
    
    public void handleToggleWprow(ToggleEvent event) {
        if(event.getVisibility().compareTo(Visibility.VISIBLE)==0){
            wprowadzanie=true;
        }
        if(event.getVisibility().compareTo(Visibility.HIDDEN)==0){
            wprowadzanie=false;
        }
    }
}
