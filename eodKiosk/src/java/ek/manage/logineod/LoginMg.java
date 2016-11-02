/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ek.manage.logineod;

import ek.encje.UserProfil;
import ek.encje.UserProfilKontr;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import pl.eod.encje.Uzytkownik;
import pl.eod.encje.UzytkownikJpaController;

@ManagedBean(name = "LoginMg")
@SessionScoped
public class LoginMg implements Serializable {

    private static final long serialVersionUID = 1L;
    private UserProfil userProfil;
    private final UserProfilKontr uPK=new UserProfilKontr();
    private UzytkownikJpaController userC;
    private Uzytkownik user;
    
    @PostConstruct
    public void init(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        userProfil=uPK.findByCardno(request.getUserPrincipal().getName());
        userC = new UzytkownikJpaController();
        user = userC.findUzytkownik(userProfil.getEodId().longValue());
    }

    public String logOut() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        //FacesContext.getCurrentInstance().getExternalContext().redirect("/");
        return "/common/index.xhtml";

    }

    public UserProfil getUserProfil() {
        return userProfil;
    }

    public Uzytkownik getUser() {
        return user;
    }

    public void setUser(Uzytkownik user) {
        this.user = user;
    }
    
    
}
