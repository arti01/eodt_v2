/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ek.manage.logineod;

import ek.encje.EkLog;
import ek.encje.EkLogKontr;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "ObecnoscMg")
@SessionScoped
public class ObecnoscMg  implements Serializable {

    private static final long serialVersionUID = 1L;
    private String cardno;

    public String wpisz() {
        EkLog log = new EkLog();
        log.setNazwa("logowanie");
        log.setOpis(cardno);
        new EkLogKontr().create(log);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, cardno+": Obecność odnotowana",  null);
        cardno=null;
        FacesContext.getCurrentInstance().addMessage(null, message);
        return "/common/index";
    }
    
    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

}
