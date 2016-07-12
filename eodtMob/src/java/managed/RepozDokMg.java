/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed;

import eodtwscRepoz.DrzComparator;
import eodtwscRepoz.DrzPF;
import eodtwscRepoz.EodtRepozDocClient;
import eodtwscRepoz.EodtRepozWyslijZal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author arti01
 */
@Named(value = "repozDokMg")
@SessionScoped
public class RepozDokMg implements Serializable {

    private static final long serialVersionUID = 1L;
    DrzPF root;
    List<DrzComparator> biez = new ArrayList<>();
    DrzPF current;
    DrzPF parent;
    EodtRepozDocClient rC;
    EodtRepozWyslijZal wysC;
    DrzPF zalDoWys;
    String email;
    String mess="";

    public RepozDokMg() {
    }

    @PostConstruct
    public void init() {
        rC = new EodtRepozDocClient();
        wysC = new EodtRepozWyslijZal();
        root = rC.getDrzewo();
        doBiezace(root.getChidren());
        Collections.sort(biez);
        current = root;
        current.setParent(null);
        parent=null;
    }

    public void zmienKatalog(){
        doBiezace(current.getChidren());
        Collections.sort(biez);
        current.setParent(parent);
        mess="";
    }
    
    public void wyslijZal(){
        mess=wysC.zZalaczZserwera(email, "wysłany plik: "+zalDoWys.getShortName(), "plik w załączeniu", zalDoWys.getFullPath());
    }
    
    public DrzPF getRoot() {
        return root;
    }

    public void setRoot(DrzPF root) {
        this.root = root;
    }

    public List<DrzComparator> getBiez() {
        return biez;
    }

    public void setBiez(List<DrzComparator> biez) {
        this.biez = biez;
    }

    public DrzPF getCurrent() {
        return current;
    }

    public void setCurrent(DrzPF current) {
        this.current = current;
    }

    private void doBiezace(List<DrzPF> l) {
        biez.clear();
        l.stream().forEach((d) -> {
            biez.add(new DrzComparator(d));
        });
    }

    public DrzPF getParent() {
        return parent;
    }

    public void setParent(DrzPF parent) {
        this.parent = parent;
    }

    public DrzPF getZalDoWys() {
        return zalDoWys;
    }

    public void setZalDoWys(DrzPF zalDoWys) {
        this.zalDoWys = zalDoWys;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

}
