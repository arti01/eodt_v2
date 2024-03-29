/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod.managed;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import pl.eod.encje.Dzial;
import pl.eod.encje.DzialJpaController;
import pl.eod.encje.KomKolejka;
import pl.eod.encje.KomKolejkaJpaController;
import pl.eod.encje.Struktura;
import pl.eod.encje.StrukturaJpaController;
import pl.eod.encje.UserRoles;
import pl.eod.encje.UserRolesJpaController;
import pl.eod.encje.Uzytkownik;
import pl.eod.encje.UzytkownikJpaController;
import pl.eod.encje.WnRodzaje;
import pl.eod.encje.WnRodzajeJpaController;
import pl.eod.encje.WnStatusy;
import pl.eod.encje.WnStatusyJpaController;
import pl.eod.encje.exceptions.NonexistentEntityException;

@ManagedBean(name = "UsersM")
@SessionScoped
public class UsersM implements Serializable {

    private static final long serialVersionUID = 1L;
    //List<Uzytkownik> users = new ArrayList<>();-bo richfaces
    List<UserRoles> roleAll = new ArrayList<>();
    List<Dzial> dzialyAll = new ArrayList<>();
    List<Struktura> kierownicyAll = new ArrayList<>();
    List<WnRodzaje> wnRodzajeAll = new ArrayList<>();
    //DataModel<Struktura> struktury = new ListDataModel<Struktura>();
    UzytkownikJpaController userC;
    Uzytkownik user;
    List<UserRoles> rolesKlon;
    boolean edytuj = false;
    String nameFilter;
    Dzial dzialFilter;
    StrukturaJpaController struktC;
    UserRolesJpaController urC;
    WnRodzajeJpaController wsC;
    Struktura strukt;
    Struktura newSzef;
    DzialJpaController dzialC;
    @ManagedProperty(value = "#{login}")
    private Login login;
    boolean sprawdzacUnikEmail;
    //private Map<String, String> filterValues = Maps.newHashMap(); - bo RF
    //private Map<String, SortOrder> sortOrders = Maps.newHashMapWithExpectedSize(1);
    //private StrukturaDataModel dataModel;
    List<Struktura> dataModelPF = new ArrayList<>();
    private KomKolejkaJpaController KomKolC;
    String error;

    @PostConstruct
    public void init() {
        userC = new UzytkownikJpaController();
        struktC = new StrukturaJpaController();
        dzialC = new DzialJpaController();
        urC = new UserRolesJpaController();
        wsC=new WnRodzajeJpaController();
        login.refresh();
        //dataModel = new StrukturaDataModel(login.zalogowany.getUserId().getSpolkaId());
        //dataModelPF = login.zalogowany.getUserId().getSpolkaId().getStrukturalist();
        //users = userC.findUzytkownikEntities(login.zalogowany.getUserId().getSpolkaId(), true);
        //sortOrders.put("userId.fullname", SortOrder.descending);
        KomKolC = new KomKolejkaJpaController();
//        System.err.println("init"+new Date());
    }

    private void initUser() {
        //System.err.println("initUser"+new Date());
        strukt = new Struktura();
        strukt.setPrzyjmowanieWnioskow(false);
        user = new Uzytkownik();
        strukt.setUserId(user);
        Dzial dzial = new Dzial();
        strukt.setDzialId(dzial);
        //struktury = new ListDataModel<Struktura>();
        //struktury.setWrappedData(struktC.findStrukturaWidoczni(login.zalogowany.getUserId().getSpolkaId()));
        roleAll = urC.findDostepneDoEdycji();
        wnRodzajeAll=wsC.getFindWnRodzajeEntities();
        dzialyAll = dzialC.findDzialEntities(login.zalogowany.getUserId().getSpolkaId());
        kierownicyAll = struktC.getFindKierownicy(login.zalogowany.getUserId().getSpolkaId());
        //users = userC.findUzytkownikEntities(login.zalogowany.getUserId().getSpolkaId(), true);
        //System.err.println(new Date().getTime());
        dataModelPF = login.zalogowany.getUserId().getSpolkaId().getStrukturalist();
        //System.err.println(new Date().getTime());
        //dataModelPF = struktC.strukturyNieUsuniete(login.zalogowany.getUserId().getSpolkaId());
        //System.err.println(new Date().getTime());
        //System.err.println("initUser"+new Date());
        //System.out.println(struktury.getRowCount()+"initUser");
    }

    public String lista() {
        edytuj = false;
        //nameFilter=null;
        //dzialFilter=new Dzial(new Long(0));
        initUser();
        return "/all/usersList";
    }

    public String nowy() {
        edytuj = false;
        nameFilter = null;
        dzialFilter = null;
        initUser();
        return "/all/usersAdd";
    }

    public String edycja() {
        rolesKlon = new CopyOnWriteArrayList<>(strukt.getUserId().getRole());
        return "/all/usersEdit";
    }

    public String ustawZastepce() throws IOException {
        strukt = login.getZalogowany();
        rolesKlon = new CopyOnWriteArrayList<>(strukt.getUserId().getRole());
        return "/common/ustawZastepce";
    }

    public String ustawZastepceZapisz() throws NonexistentEntityException, Exception {
        boolean czyWyslac = strukt.getSecUserId() != null;
        String adresMail = "";
        if (czyWyslac) {
            adresMail = strukt.getSecUserId().getAdrEmail();
        }
        zapisz();
        if (error == null) {
            if (czyWyslac) {
                KomKolejka kk = new KomKolejka();
                kk.setAdresList(adresMail);
                kk.setStatus(0);
                kk.setIdDokumenu(0);
                kk.setTemat("Informacja o ustanowieniu zastępcy");
                kk.setTresc("Pracownik " + login.getZalogowany().getUserId().getFullname() + " ustanowił Cię zastępcą. Możesz wykonywać w jego imieniu operacje na wnioskach urlopowych.");
                KomKolC.create(kk);
            }
            return "/logowanie/index?faces-redirect=true";
        } else {
            /*FacesMessage message = new FacesMessage(error);
            FacesContext context = FacesContext.getCurrentInstance();
            UIComponent zapisz = UIComponent.getCurrentComponent(context);
            context.addMessage(zapisz.getClientId(context), message);*/
            return "/common/ustawZastepce";
        }
    }

    public String listaFiltr() {
        edytuj = false;
        initUser();
        //System.err.println("c"+nameFilter+"c");
        return "/all/usersList";
    }

    public void dodaj() throws NonexistentEntityException, Exception {

        String error1 = struktC.create(strukt);

        if (error1 != null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error1, error1);
            FacesContext context = FacesContext.getCurrentInstance();
            UIComponent zapisz = UIComponent.getCurrentComponent(context);
            context.addMessage(zapisz.getClientId(context), message);
        } else {
            FacesMessage message = new FacesMessage("dodano");
            FacesContext context = FacesContext.getCurrentInstance();
            UIComponent zapisz = UIComponent.getCurrentComponent(context);
            context.addMessage(zapisz.getClientId(context), message);
            initUser();
        }
    }

    public void usun() {
        struktC.zrobNiewidczony(strukt);
        initUser();
        edytuj = false;
    }

    @Deprecated
    public void usunOld() throws NonexistentEntityException, Exception {
        struktC.destroyArti(strukt);
        initUser();
        edytuj = false;
    }

    public String zapisz() throws NonexistentEntityException, Exception {
        //obsluga dla readonly roli w formularzu
        if (!strukt.getUserId().getRole().equals(rolesKlon) && rolesKlon != null) {
            rolesKlon.removeAll(roleAll);
            // System.err.println(rolesKlon);
            strukt.getUserId().getRole().addAll(rolesKlon);
        }
        error = struktC.editArti(strukt);
        if (error == null) {
            initUser();
            return "/all/usersList?faces-redirect=true";
        }
        FacesMessage message = new FacesMessage(error);
        FacesContext context = FacesContext.getCurrentInstance();
        UIComponent zapisz = UIComponent.getCurrentComponent(context);
        context.addMessage(zapisz.getClientId(context), message);
        edytuj = true;
        //dataModel = new StrukturaDataModel(login.zalogowany.getUserId().getSpolkaId());
        return "/all/usersEdit";
    }

    public void kierListener(ValueChangeEvent e) {
        Boolean kier;
        kier = (Boolean) e.getNewValue();
        try {
            if (kier) {
                strukt.getDzialId().setNazwa("Nowy dział");
            } else {
                strukt.getDzialId().setNazwa(strukt.getSzefId().getDzialId().getNazwa());
            }
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        } catch (Exception ex1) {
            ex1.printStackTrace();
        }
        //strukt.setStKier(true);
    }

    public void changeKierListener(ValueChangeEvent e) throws NonexistentEntityException, Exception {
        Boolean kier;
        kier = (Boolean) e.getNewValue();
        strukt.setStKier(kier);
        String error = null;
        try {
            error = struktC.changeKier(strukt, strukt.getDzialId());
            strukt = struktC.findStruktura(strukt.getId());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(UsersM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UsersM.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (error != null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
            FacesContext context = FacesContext.getCurrentInstance();
            UIComponent kierownikE = UIComponent.getCurrentComponent(context);
            context.addMessage(kierownikE.getClientId(context), message);
        }
    }

    public void dzialListener(ValueChangeEvent e) throws NullPointerException {
        Struktura str = (Struktura) e.getNewValue();
        //System.out.println(strukt.getDzialId());
        if (!strukt.isStKier()) {
            if (str != null) {
                strukt.getDzialId().setNazwa(str.getDzialId().getNazwa());
                strukt.getDzialId().setSymbol(str.getDzialId().getSymbol());
            } else {
                strukt.getDzialId().setNazwa("");
                strukt.getDzialId().setSymbol("");
            }
        }
    }

    public List<Uzytkownik> uzytkownicyAucoComp(String query) {
        query = query.toLowerCase();
        List<Uzytkownik> wynik = new ArrayList<>();
        for (Uzytkownik u : userC.findUzytkownikEntities(login.zalogowany.getUserId().getSpolkaId(), true)) {
            if (u.getAdrEmail().toLowerCase().contains(query) || u.getFullname().toLowerCase().contains(query)) {
                wynik.add(u);
            }
        }
        wynik.remove(strukt.getUserId());
        return wynik;
    }

    public void zmienPrzelozonegoDo() throws NonexistentEntityException, NullPointerException, Exception {
        for (Struktura s : strukt.getBezpPod()) {
            s.setSzefId(newSzef);
            struktC.editArti(s);
        }
        //struktC.editArti(struktC.findStruktura(strukt.getId()));
        strukt.setBezpPod(new ArrayList<>());
        struktC.editArti(strukt);
        System.err.println(strukt.getBezpPodWidoczni());
        FacesMessage message = new FacesMessage("zmiana wykonana");
        FacesContext context = FacesContext.getCurrentInstance();
        UIComponent zapisz = UIComponent.getCurrentComponent(context);
        context.addMessage(zapisz.getClientId(context), message);
        //initUser();
        //return "/all/usersList?faces-redirect=true";
    }

    public Uzytkownik getUser() {
        return user;
    }

    public void setUser(Uzytkownik user) {
        this.user = user;
    }

    public boolean isEdytuj() {
        return edytuj;
    }

    public void setEdytuj(boolean edytuj) {
        this.edytuj = edytuj;
    }

    public String getNameFilter() {
        return nameFilter;
    }

    public void setNameFilter(String nameFilter) {
        edytuj = false;
        this.nameFilter = nameFilter;
    }

    public Dzial getDzialFilter() {
        return dzialFilter;
    }

    public void setDzialFilter(Dzial dzialFilter) {
        edytuj = false;
        this.dzialFilter = dzialFilter;
    }

    public DzialJpaController getDzialC() {
        return dzialC;
    }

    public StrukturaJpaController getStruktC() {
        return struktC;
    }

    public Struktura getStrukt() {
        return strukt;
    }

    public void setStrukt(Struktura strukt) {
        this.strukt = strukt;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public List<UserRoles> getRoleAll() {
        return roleAll;
    }

    public void setRoleAll(List<UserRoles> roleAll) {
        this.roleAll = roleAll;
    }

    public List<Dzial> getDzialyAll() {
        return dzialyAll;
    }

    public void setDzialyAll(List<Dzial> dzialyAll) {
        this.dzialyAll = dzialyAll;
    }

    public List<Struktura> getKierownicyAll() {
        return kierownicyAll;
    }

    public void setKierownicyAll(List<Struktura> kierownicyAll) {
        this.kierownicyAll = kierownicyAll;
    }

    public Struktura getNewSzef() {
        return newSzef;
    }

    public void setNewSzef(Struktura newSzef) {
        this.newSzef = newSzef;
    }

    public List<Struktura> getDataModelPF() {
        return dataModelPF;
    }

    public void setDataModelPF(List<Struktura> dataModelPF) {
        this.dataModelPF = dataModelPF;
    }

    public List<WnRodzaje> getWnRodzajeAll() {
        return wnRodzajeAll;
    }

    public void setWnRodzajeAll(List<WnRodzaje> wnRodzajeAll) {
        this.wnRodzajeAll = wnRodzajeAll;
    }

}
