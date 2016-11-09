/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ek.manage.logineod;

import ek.encje.EkConfigKontr;
import ek.encje.EkLog;
import ek.encje.EkLogKontr;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "ObecnoscMg")
@RequestScoped
public class ObecnoscMg implements Serializable {

    private static final long serialVersionUID = 1L;
    private String cardno;
    private String ipAdd;
    private boolean ipGreen=false;

    @PostConstruct
    private void init() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        ipAdd = request.getHeader("X-FORWARDED-FOR");
        if (ipAdd != null) {
            ipAdd = ipAdd.replaceFirst(",.*", "");  // cares only about the first IP if there is a list
        } else {
            ipAdd = request.getRemoteAddr();
        }
        EkConfigKontr confC = new EkConfigKontr();
        String ipList = confC.findEntities("ip_green").getWartosc();
        for(String ip:Arrays.asList(ipList.split(","))){
            if(ip.equals(ipAdd)){
                ipGreen=true;
                break;
            }
        }
    }

    public String wpisz() {
        EkLog log = new EkLog();
        log.setNazwa("logowanie");
        log.setOpis(cardno);
        log.setIp(ipAdd);
        new EkLogKontr().create(log);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Obecność odnotowana", null);
        FacesContext.getCurrentInstance().addMessage(null, message);
        cardno = null;
        return "/common/index";
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getIpAdd() {
        return ipAdd;
    }

    public void setIpAdd(String ipAdd) {
        this.ipAdd = ipAdd;
    }

    public boolean isIpGreen() {
        return ipGreen;
    }

    public void setIpGreen(boolean ipGreen) {
        this.ipGreen = ipGreen;
    }

}
