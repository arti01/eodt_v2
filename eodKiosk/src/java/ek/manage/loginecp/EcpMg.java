/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ek.manage.loginecp;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import pl.esoftking.ekiosk.app.KioskManager;

@ManagedBean(name = "EcpMg")
@SessionScoped
public class EcpMg implements Serializable {

    private static final long serialVersionUID = 1L;
    String stanNadgodzin;
    String stanUrlopow;
    KioskManager km;

    @PostConstruct
    public void init() {
        km = new KioskManager();
        stanNadgodzin = km.pobierzStanNadgodzin("10-2016", new Long(2085));
        stanUrlopow = km.pobierzStanUrlopow(2016, new Long(2085));
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
