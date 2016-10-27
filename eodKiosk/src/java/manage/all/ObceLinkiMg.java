/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage.all;

import abstr.AbstMg;
import encje.EkObceLinki;
import encje.EKObceLinkiKontr;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "ObceLinkiMg")
@SessionScoped
public class ObceLinkiMg extends AbstMg<EkObceLinki, EKObceLinkiKontr> {

    private int listaSize = 0;
    
    @PostConstruct
    public void init(){
        try {
            super.refresh();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ObceLinkiMg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObceLinkiMg() throws InstantiationException, IllegalAccessException {
        super("/dcarch/listWbrakowanie", new EKObceLinkiKontr(), new EkObceLinki());
        super.refresh();
    }
    
    public int getListaSize() {
        return listaSize;
    }

    public void setListaSize(int listaSize) {
        this.listaSize = listaSize;
    }

}
