/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ek.manage.logineod;

import ek.encje.EkConfigKontr;
import ek.encje.UserProfil;
import ek.encje.UserProfilKontr;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import pl.eod.encje.Uzytkownik;
import pl.eod.encje.UzytkownikJpaController;
import pl.esoftking.ekiosk.app.KioskManager;

@ManagedBean(name = "LoginMg")
@SessionScoped
public class LoginMg implements Serializable {

    private static final long serialVersionUID = 1L;
    private UserProfil userProfil;
    private final UserProfilKontr uPK = new UserProfilKontr();
    private Uzytkownik user;
    private String iddle;
    private String iddle30;
    private String stanNad50;
    private String stanNad100;
    private String stanUrlLim;
    private String stanUrlWyk;
    private String stanUrlPoz;
    private String okrRozOd;
    private String okrRozDo;
    private KioskManager km;
    private boolean urodziny = false;
    private boolean potwierdzenie = false;

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        if (request != null) {
            try {
                userProfil = uPK.findByCardno(request.getUserPrincipal().getName());
                potwierdzenie=userProfil.isJestData();
                user=uPK.findUzytkownik(userProfil);
                EkConfigKontr confC = new EkConfigKontr();
                Long iddL = new Long(confC.findEntities("iddle").getWartosc());
                this.iddle = Long.toString(iddL * 60 * 1000);
                this.iddle30 = Long.toString(iddL * 60 * 1000 + 30000);
                km = new KioskManager();
                SimpleDateFormat sdf = new SimpleDateFormat("MM-yyyy");
                SimpleDateFormat sdfY = new SimpleDateFormat("yyyy");
                SimpleDateFormat sdfF = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat sdfMD = new SimpleDateFormat("MM-dd");
                //SimpleDateFormat sdfTest = new SimpleDateFormat("MM-dd hh:mm");
                String stanNadgodzin = km.pobierzStanNadgodzin(sdf.format(Calendar.getInstance().getTime()), userProfil.getEcpId().longValue());
                //stanNadgodzin=stanNadgodzin.replace("Brak", " - ");
                String[] stnS = stanNadgodzin.split("\\|");
                stanNad50 = stnS[0];
                stanNad100 = stnS[0];
                String stanUrlopow = km.pobierzStanUrlopow(new Long(sdfY.format(Calendar.getInstance().getTime())).intValue(), userProfil.getEcpId().longValue());
                String[] stuS = stanUrlopow.split("\\|");
                stanUrlLim = stuS[0];
                if(stuS.length<2){
                    Logger.getLogger(LoginMg.class.getName()).log(Level.SEVERE, "brak komletu informacji z ECP");
                    return;
                }
                stanUrlWyk = stuS[1];
                stanUrlPoz = stuS[2];
                String okresRozl = km.pobierzOkresRozlicz(sdf.format(Calendar.getInstance().getTime()));
                String[] okrS = okresRozl.split("\\|");
                okrRozOd = okrS[0];
                okrRozDo = okrS[1];
                String dataUr;

                dataUr = sdfMD.format(sdfF.parse(km.pobierzDateUrodzin(userProfil.getEcpId().longValue())));
                //System.err.println(dataUr + "data ur");
                String dataCur = sdfMD.format(Calendar.getInstance().getTime());
                urodziny = dataCur.equals(dataUr);
                //System.err.println(dataCur + "data current");
                //System.err.println(sdfTest.format(Calendar.getInstance().getTime()) + "data z godzina");
            } catch (ParseException | java.lang.ArrayIndexOutOfBoundsException ex) {
                Logger.getLogger(LoginMg.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String logOut() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        //FacesContext.getCurrentInstance().getExternalContext().redirect("/");
        return "/common/index.xhtml";
    }

    public void processTimeOut() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().redirect("../common/index.xhtml");
    }

    public void listSave() {
        uPK.save(userProfil);
        String info = "Wyrażam zgodę na przetwarzanie moich danych osobowych zgodnie z ustawą o ochronie danych osobowych w celu przesyłania drogą elektroniczną na podany przeze mnie prywatny adres email oraz prywatny numer telefonu komórkowego dokumentów dotyczących obsługi procesów HR / kadrowo-płacowych, w tym ankiet, newslettera oraz innych informacji z tym związanych, zgodnie z ustawą  o świadczeniu usług drogą elektroniczną. Podanie danych osobowych jest dobrowolne. Zostałem/am poinformowany/a, że przysługuje mi prawo dostępu do swoich danych, możliwości ich poprawiania, żądania zaprzestania ich przetwarzania. Administratorem danych jest Fresenius Nephrocare Polska Sp. z o.o. / Fresenius Medical Care Polska SA z siedzibą  w Poznaniu";
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, info, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void potwierdzSave() {
        if (userProfil.getDataPotwierdzenia() == null) {
            userProfil.setDataPotwierdzenia(new Date());
        } else {
            userProfil.setDataPotwierdzenia(null);
        }
        uPK.save(userProfil);
        String info = "zmiana wykonana";
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, info, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
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

    public String getIddle() {
        return iddle;
    }

    public void setIddle(String iddle) {
        this.iddle = iddle;
    }

    public String getStanNad50() {
        return stanNad50;
    }

    public String getStanNad100() {
        return stanNad100;
    }

    public String getStanUrlLim() {
        return stanUrlLim;
    }

    public String getStanUrlWyk() {
        return stanUrlWyk;
    }

    public String getStanUrlPoz() {
        return stanUrlPoz;
    }

    public String getOkrRozOd() {
        return okrRozOd;
    }

    public String getOkrRozDo() {
        return okrRozDo;
    }

    public boolean isUrodziny() {
        return urodziny;
    }

    public String getIddle30() {
        return iddle30;
    }

    public boolean isPotwierdzenie() {
        return potwierdzenie;
    }

    public void setPotwierdzenie(boolean potwierdzenie) {
        this.potwierdzenie = potwierdzenie;
    }

}
