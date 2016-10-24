/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage.all;

import abstr.AbstMg;
import encje.EkNews;
import encje.EkNewsKontr;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "NewsMg")
@SessionScoped
public class NewsMg extends AbstMg<EkNews, EkNewsKontr> {

    private int listaSize = 0;
    
    @PostConstruct
    public void init(){
        try {
            super.refresh();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(NewsMg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public NewsMg() throws InstantiationException, IllegalAccessException {
        super("/dcarch/listWbrakowanie", new EkNewsKontr(), new EkNews());
        super.refresh();
    }
    
    public int getListaSize() {
        return listaSize;
    }

    public void setListaSize(int listaSize) {
        this.listaSize = listaSize;
    }

}
