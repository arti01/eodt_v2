/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ek.manage.admin;

import ek.abstr.AbstMg;
import ek.encje.UserProfil;
import ek.encje.UserProfilKontr;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;
import pl.eod.encje.Uzytkownik;
import pl.eod.encje.UzytkownikJpaController;
import pl.eod2.encje.exceptions.NonexistentEntityException;
import pl.esoftking.ekiosk.app.KioskManager;

@ManagedBean(name = "AdmUserMg")
@SessionScoped
public class AdmUserMg extends AbstMg<UserProfil, UserProfilKontr> {

    boolean skip;
    List<Uzytkownik> uzytList = new ArrayList<>();
    List<UserEcp> userEcpList = new ArrayList<>();

    public AdmUserMg() throws InstantiationException, IllegalAccessException {
        super("/admin/user_list", new UserProfilKontr(), new UserProfil());
    }

    @PostConstruct
    void init() {
        KioskManager kM = new KioskManager();
        for (String s : kM.pobierzListePracownikow()) {
            String[] spl = s.split("\\|");
            UserEcp uEcp = new UserEcp();
            uEcp.setId(spl[0]);
            uEcp.setFullname(spl[1]);
            uEcp.setEmail(spl[2]);
            uEcp.setCardno(spl[3]);
            userEcpList.add(uEcp);
        }
        UzytkownikJpaController uC = new UzytkownikJpaController();
        uzytList = uC.findUzytkownikEntities();
        try {
            refresh();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(AdmUserMg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void refresh() throws InstantiationException, IllegalAccessException {
        super.refresh(); //To change body of generated methods, choose Tools | Templates.
        lista = dcC.findAll();
        for(UserProfil up:lista){
            up.setUserEcp(findEcp(up.getEcpId().longValue()));
        }
    }

    public String addForm() throws InstantiationException, IllegalAccessException {
        refresh();
        return "/admin/user_add";
    }

    public void editList(RowEditEvent event) throws NonexistentEntityException, Exception {
        obiekt = (UserProfil) event.getObject();
        edytujList();
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String dodajProf() throws InstantiationException, IllegalAccessException, Exception {
        dodaj(); //To change body of generated methods, choose Tools | Templates.
        return "/admin/user_list?faces-redirect=true";
    }

    public String onFlowProcess(FlowEvent event) {
        UserEcp uE = findEcp(obiekt.getEcpId().longValue());
        obiekt.setCardno(uE.getCardno());
        if (obiekt.getEmail() == null) {
            obiekt.setEmail(uE.getEmail());
        }
        if (obiekt.getFullname() == null) {
            obiekt.setFullname(uE.getFullname());
        }
        obiekt.setUzytkownik(dcC.findUzytkownik(obiekt));
        obiekt.setUserEcp(uE);
        obiekt.setPin(1111);
        return event.getNewStep();
    }

    private UserEcp findEcp(Long id) {
        for (UserEcp u : userEcpList) {
            if (new Long(u.getId()).equals(id)) {
                return u;
            }
        }
        return null;
    }

    public List<Uzytkownik> completeUser(String query) {
        List<Uzytkownik> filteredThemes = new ArrayList<>();
        uzLi:
        for (Uzytkownik u : uzytList) {
            //jesli juz istnieje to nie pokazujemy
            for (UserProfil up : lista) {
                if (u.getId().compareTo(up.getEodId().longValue()) == 0) {
                    continue uzLi;
                }
            }
            if (u.getAdrEmail().toLowerCase().startsWith(query) || u.getFullname().toLowerCase().startsWith(query)) {
                filteredThemes.add(u);
            }
        }
        return filteredThemes;
    }

    public List<UserEcp> completeEcpUser(String query) {
        List<UserEcp> filteredThemes = new ArrayList<>();
        uzLi:
        for (UserEcp u : userEcpList) {
            //jesli juz istnieje to nie pokazujemy
            for (UserProfil up : lista) {
                if (new Long(u.getId()).compareTo(up.getEcpId().longValue()) == 0) {
                    continue uzLi;
                }
            }
            if (u.getFullname().toLowerCase().startsWith(query)) {
                filteredThemes.add(u);
            }
        }
        return filteredThemes;
    }
}
