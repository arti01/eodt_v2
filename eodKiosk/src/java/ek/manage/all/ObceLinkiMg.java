/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ek.manage.all;

import ek.abstr.AbstMg;
import ek.encje.EkObceLinki;
import ek.encje.EKObceLinkiKontr;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "ObceLinkiMg")
@SessionScoped
public class ObceLinkiMg extends AbstMg<EkObceLinki, EKObceLinkiKontr> {

    private final int listaSize = 0;

    public ObceLinkiMg() throws InstantiationException, IllegalAccessException {
        super("/common/obce_linki", new EKObceLinkiKontr(), new EkObceLinki());
    }

    @PostConstruct
    public void init() {
        try {
            super.refresh();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ObceLinkiMg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String wyswietl() {
        return "/common/includ_www";
    }

}
