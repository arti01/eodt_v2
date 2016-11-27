/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ek.manage.admin;

import ek.abstr.AbstMg;
import ek.encje.EkNews;
import ek.encje.EkNewsKontr;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.RowEditEvent;
import pl.eod2.encje.UmGrupa;
import pl.eod2.encje.exceptions.NonexistentEntityException;

@ManagedBean(name = "AdmNewsMg")
@SessionScoped
public class AdmNewsMg extends AbstMg<EkNews, EkNewsKontr> {

    private int listaSize = 0;
    String naglowek;

    public AdmNewsMg() throws InstantiationException, IllegalAccessException {
        super("/admin/news_list", new EkNewsKontr(), new EkNews());
    }
    
    public void listener(){
        lista=dcC.findEntities(5);
    }
    
    public int getListaSize() {
        return listaSize;
    }

    public void setListaSize(int listaSize) {
        this.listaSize = listaSize;
    }

    public String detale(){
        return super.link;
    }
    public void editList(RowEditEvent event) throws NonexistentEntityException, Exception {
        obiekt = (EkNews) event.getObject();
        edytujList();
    }
    
}
