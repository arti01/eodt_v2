/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod.managwn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import pl.eod.encje.KomKolejka;
import pl.eod.encje.KomKolejkaJpaController;
import pl.eod.encje.Uzytkownik;
import pl.eod.encje.WnHistoria;
import pl.eod.encje.WnRodzajeJpaController;
import pl.eod.encje.WnStatusy;
import pl.eod.encje.WnUrlop;
import pl.eod.encje.WnUrlopJpaController;
import pl.eod.managed.Login;

@ManagedBean(name = "UrlopObceM")
@SessionScoped
public class UrlopObceM {

    private WnUrlop urlop;
    private DataModel<WnUrlop> urlopyList = new ListDataModel<>();
    private WnUrlopJpaController urlopC;
    private WnRodzajeJpaController rodzajeC;
    private KomKolejkaJpaController KomKolC;
    @ManagedProperty(value = "#{login}")
    private Login login;
    private Locale locale;
    private Date godzOdT;
    private Date godzDoT;
    private Date dataUrlopu;
    boolean calyDzien;
    String wynikWs;

    public String list() {
        initUrlop();
        return "/urlop/urlopyListObce";
    }

    public void changeUserList(AjaxBehaviorEvent event) {
        Uzytkownik u = (Uzytkownik) ((UIOutput) event.getSource()).getValue();
        if (u.getId() == null) {
            urlop.setExtraemail(false);
        } else {
            urlop.setExtraemail(u.getStruktura().getExtraemail().length() > 0);
        }
    }

    public void anuluj() {
        String info = "";
        try {
            WnStatusy st = new WnStatusy();
            st.setId(new Long(6));
            urlop.setStatusId(st);
            //urlop.setAkceptant(login.getZalogowany().getSzefId().getUserId());

            WnHistoria wnh = new WnHistoria();
            wnh.setDataZmiany(new Date());
            WnStatusy st1 = new WnStatusy();
            st1.setId(new Long(6));
            wnh.setStatusId(st1);
            wnh.setZmieniajacy(login.getZalogowany().getUserId());
            wnh.setUrlopId(urlop);
            //wnh.setAkceptant(login.getZalogowany().getSzefId().getUserId());
            wnh.setOpisZmiany("uprawniona osoba anulowala po zaakceptowaniu");

            urlop.getWnHistoriaList().add(wnh);

            urlopC.createEdit(urlop);

            //wysylanie maila
            String tresc;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (urlop.isExtraemail()) {
                KomKolejka kk = new KomKolejka();
                kk.setAdresList(urlop.getUzytkownik().getStruktura().getExtraemail());
                kk.setStatus(0);
                kk.setIdDokumenu(urlop.getId().intValue());
                kk.setTemat("Informacja o anulowaniu wniosku urlopowego");
                kk.setTresc("Uprawniona osoba anulowała urlop pracownika " + urlop.getUzytkownik().getFullname() + ". Urlop " + urlop.getRodzajId().getOpis() + " wnioskowany w dniach od:" + sdf.format(urlop.getDataOd()) + " do:" + sdf.format(urlop.getDataDo()) + ". Numer wniosku: " + urlop.getNrWniosku() + ". Dodatkowe informacje: " + urlop.getInfoDod());
                KomKolC.create(kk);
            }

            if (!urlop.getUzytkownik().getStruktura().getSzefId().getUserId().getAdrEmail().equals("")) {
                KomKolejka kk = new KomKolejka();
                kk.setAdresList(urlop.getUzytkownik().getStruktura().getSzefId().getUserId().getAdrEmail());
                kk.setStatus(0);
                kk.setIdDokumenu(urlop.getId().intValue());
                kk.setTemat("Informacja o anulowaniu wniosku urlopowego");
                tresc = "Uprawniona osoba anulowała urlop pracownika " + urlop.getUzytkownik().getFullname() + ". Urlop " + urlop.getRodzajId().getOpis() + " wnioskowany w dniach od:" + sdf.format(urlop.getDataOd()) + " do:" + sdf.format(urlop.getDataDo()) + ". Numer wniosku: " + urlop.getNrWniosku() + ". Dodatkowe informacje: " + urlop.getInfoDod();
                kk.setTresc(tresc);
                KomKolC.create(kk);
            }

            info = "Wniosek anulowany";

        } catch (Exception ex) {
            //if(login.getZalogowany().getSzefId()==null) info = "nie można ustawić akceptanta, brak przełożonego";
            //else 
            info = "Coś poszło nie tak";
            ex.printStackTrace();
        } finally {
            initUrlop();
            FacesContext context = FacesContext.getCurrentInstance();
            UIComponent zapisz = UIComponent.getCurrentComponent(context);
            FacesMessage message = new FacesMessage();
            message.setSummary(info);
            context.addMessage(zapisz.getClientId(context), message);
        }
    }

    public void wyslij() {
        String error;
        WnStatusy st = new WnStatusy();
        st.setId(new Long(2));
        urlop.setStatusId(st);
        urlop.setAkceptant(urlop.getUzytkownik().getStruktura().getSzefId().getUserId());

        WnHistoria wnh = new WnHistoria();
        wnh.setDataZmiany(new Date());
        WnStatusy st1 = new WnStatusy();
        st1.setId(new Long(2));
        wnh.setStatusId(st1);
        wnh.setZmieniajacy(login.getZalogowany().getUserId());
        wnh.setUrlopId(urlop);
        wnh.setAkceptant(urlop.getUzytkownik().getStruktura().getSzefId().getUserId());
        wnh.setOpisZmiany("uprawniona osoba wysłała wniosek do akceptu przełożonemu");

        urlop.getWnHistoriaList().add(wnh);

        error = urlopC.createEdit(urlop);

        if (error == null) {
            //wysylanie maila
            String tresc;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (urlop.isExtraemail()) {
                KomKolejka kk = new KomKolejka();
                kk.setAdresList(urlop.getUzytkownik().getStruktura().getExtraemail());
                kk.setStatus(0);
                kk.setIdDokumenu(urlop.getId().intValue());
                kk.setTemat("Informacja o wniosku urlopowym");
                tresc = "Pracownik " + urlop.getUzytkownik().getFullname() + " wnioskuje o urlop " + urlop.getRodzajId().getOpis() + " w dniach od:" + sdf.format(urlop.getDataOd()) + " do:" + sdf.format(urlop.getDataDo()) + ". Numer wniosku: " + urlop.getNrWniosku();
                if (!urlop.getInfoDod().isEmpty()) {
                    tresc = tresc + ". Dodatkowe informacje: " + urlop.getInfoDod();
                }
                if (urlop.getUzytkownik().getStruktura().getSecUserId() != null) {
                    tresc = tresc + ". Na czas nieobecności pracownika, zastępuje go " + urlop.getUzytkownik().getStruktura().getSecUserId().getFullname() + " (email: " + urlop.getUzytkownik().getStruktura().getSecUserId().getAdrEmail() + ")";
                }
                kk.setTresc(tresc);
                KomKolC.create(kk);
            }

            if (!urlop.getAkceptant().getAdrEmail().equals("")) {
                KomKolejka kk = new KomKolejka();
                kk.setAdresList(urlop.getAkceptant().getAdrEmail());
                kk.setStatus(0);
                kk.setIdDokumenu(urlop.getId().intValue());
                kk.setTemat("Prośba o akceptację wniosku urlopowego");
                tresc = "Proszę o akceptację wniosku urlopowego. " + "Pracownik " + urlop.getUzytkownik().getFullname() + " wnioskuje o urlop " + urlop.getRodzajId().getOpis() + " w dniach od:" + sdf.format(urlop.getDataOd()) + " do:" + sdf.format(urlop.getDataDo()) + ". Numer wniosku: " + urlop.getNrWniosku();
                if (!urlop.getInfoDod().isEmpty()) {
                    tresc = tresc + ". Dodatkowe informacje: " + urlop.getInfoDod();
                }
                if (urlop.getUzytkownik().getStruktura().getSecUserId() != null) {
                    tresc = tresc + ". Na czas nieobecności pracownika, zastępuje go " + urlop.getUzytkownik().getStruktura().getSecUserId().getFullname() + " (email: " + urlop.getUzytkownik().getStruktura().getSecUserId().getAdrEmail() + ")";
                }
                kk.setTresc(tresc);
                KomKolC.create(kk);
            }
        }
        initUrlop();
        FacesContext context = FacesContext.getCurrentInstance();
        UIComponent zapisz = UIComponent.getCurrentComponent(context);
        FacesMessage message = new FacesMessage();
        if (error != null) {
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary(error);
        } else {
            message.setSummary("uprawniona osoba wysłała wniosek");
        }
        context.addMessage(zapisz.getClientId(context), message);

    }

    public void usun() {
        urlopC.destroy(urlop);
        FacesContext context = FacesContext.getCurrentInstance();
        UIComponent zapisz = UIComponent.getCurrentComponent(context);
        FacesMessage message = new FacesMessage();
        message.setSummary("wniosek usunięty");
        context.addMessage(zapisz.getClientId(context), message);
        initUrlop();
    }

    public void dodaj() throws ParseException {
        FacesContext context = FacesContext.getCurrentInstance();
        UIComponent zapisz;
        try {
            zapisz = UIComponent.getCurrentComponent(context);
        } catch (NullPointerException npe) {
            zapisz = null;
        }
        FacesMessage message = new FacesMessage();
        Calendar cal = Calendar.getInstance();
        Calendar calOd = Calendar.getInstance();
        Calendar calDo = Calendar.getInstance();
        if (!calyDzien || urlop.getRodzajId().getId() == 40) {
            calOd.setTime(dataUrlopu);
            calDo.setTime(dataUrlopu);
            cal.setTime(godzOdT);
            calOd.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY));
            calOd.set(Calendar.MINUTE, 0);
            cal.setTime(godzDoT);
            calDo.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY));
            calDo.set(Calendar.MINUTE, 0);
            urlop.setDataOd(calOd.getTime());
            urlop.setDataDo(calDo.getTime());
        } else {
            calOd.setTime(urlop.getDataOd());
            calOd.set(Calendar.HOUR_OF_DAY, 0);
            calOd.set(Calendar.MINUTE, 0);
            calDo.setTime(urlop.getDataDo());
            calDo.set(Calendar.HOUR_OF_DAY, 23);
            calDo.set(Calendar.MINUTE, 59);
            urlop.setDataOd(calOd.getTime());
            urlop.setDataDo(calDo.getTime());
        }
        if (urlop.getUzytkownik() == null) {
            wynikWs = "wybierz pracownika";
            if (zapisz != null) {
                message.setSummary("wybierz pracownika");
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                context.addMessage(zapisz.getClientId(context), message);
            }
            return;
        }
        WnStatusy st = new WnStatusy();
        st.setId(new Long(1));
        urlop.setStatusId(st);
        //urlop.setNrWniosku("ooooooooo");
        urlop.setDataWprowadzenia(new Date());

        String error;
        if (urlop.getId() == null) {
            urlop.setWnHistoriaList(new ArrayList<>());

            WnHistoria wnh = new WnHistoria();
            wnh.setDataZmiany(new Date());
            wnh.setStatusId(st);
            wnh.setZmieniajacy(login.getZalogowany().getUserId());
            wnh.setUrlopId(urlop);
            wnh.setOpisZmiany("uprawniona osoba wprowadziła wniosek");
            urlop.getWnHistoriaList().add(wnh);
            urlop.setPrzyjmujacy(login.getZalogowany().getUserId());
            error = urlopC.createEdit(urlop);
        } else {
            error = urlopC.createEdit(urlop);
        }
        if (error != null) {
            wynikWs = error;
            message.setSummary(error);
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
        } else {
            wynikWs = urlop.getId().toString();
            initUrlop();
            message.setSummary("wniosek zapisany");
            message.setSeverity(FacesMessage.SEVERITY_INFO);
        }
        try {
            if (zapisz != null) {
                context.addMessage(zapisz.getClientId(context), message);
            }
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
    }

    @PostConstruct
    public void init() {
        urlopC = new WnUrlopJpaController();
        rodzajeC = new WnRodzajeJpaController();
        KomKolC = new KomKolejkaJpaController();
        initUrlop();
    }

    private void initUrlop() {
        login.refresh();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        godzOdT = cal.getTime();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        godzDoT = cal.getTime();
        dataUrlopu = new Date();
        calyDzien = true;
        urlop = new WnUrlop();
        urlop.setUzytkownik(new Uzytkownik());
        urlopyList.setWrappedData(login.getZalogowany().getUserId().getWnUrlopListPrzyjmujacy());
    }

    public WnUrlop getUrlop() {
        return urlop;
    }

    public void setUrlop(WnUrlop urlop) {
        this.urlop = urlop;
    }

    public DataModel<WnUrlop> getUrlopyList() {
        return urlopyList;
    }

    public void setUrlopyList(DataModel<WnUrlop> urlopyList) {
        this.urlopyList = urlopyList;
    }

    public WnRodzajeJpaController getRodzajeC() {
        return rodzajeC;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Locale getLocale() {
        locale = new Locale("pl", "PL");
        return locale;
    }

    public Date getGodzOdT() {
        return godzOdT;
    }

    public void setGodzOdT(Date godzOdT) {
        this.godzOdT = godzOdT;
    }

    public Date getGodzDoT() {
        return godzDoT;
    }

    public void setGodzDoT(Date godzDoT) {
        this.godzDoT = godzDoT;
    }

    public Date getDataUrlopu() {
        return dataUrlopu;
    }

    public void setDataUrlopu(Date dataUrlopu) {
        this.dataUrlopu = dataUrlopu;
    }

    public boolean isCalyDzien() {
        return calyDzien;
    }

    public void setCalyDzien(boolean calyDzien) {
        this.calyDzien = calyDzien;
    }

    public String getWynikWs() {
        return wynikWs;
    }

    public void setWynikWs(String wynikWs) {
        this.wynikWs = wynikWs;
    }

}
