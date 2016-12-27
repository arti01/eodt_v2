/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ek.manage.all;

import ek.abstr.AbstMg;
import ek.encje.EkObceLinki;
import ek.encje.EKObceLinkiKontr;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "ObceLinkiMg")
@SessionScoped
public class ObceLinkiMg extends AbstMg<EkObceLinki, EKObceLinkiKontr> {

    private final int listaSize = 0;
    private List<EkObceLinki> listaPoZalog;
    private boolean poZalog;

    public ObceLinkiMg() throws InstantiationException, IllegalAccessException {
        super("/common/obce_linki", new EKObceLinkiKontr(), new EkObceLinki());
    }

    @PostConstruct
    public void init() {
        try {
            listaPoZalog=dcC.findEntitiesLogin();
            super.refresh();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ObceLinkiMg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String wyswietl() {
        return "/common/includ_www";
    }

    public List<EkObceLinki> getListaPoZalog() {
        return listaPoZalog;
    }

    public void setListaPoZalog(List<EkObceLinki> listaPoZalog) {
        this.listaPoZalog = listaPoZalog;
    }

    public boolean isPoZalog() {
        return poZalog;
    }

    public void setPoZalog(boolean poZalog) {
        this.poZalog = poZalog;
    }    
}
