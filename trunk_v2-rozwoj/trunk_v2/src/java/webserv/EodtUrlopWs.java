package webserv;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.eod.encje.Struktura;
import pl.eod.encje.Uzytkownik;
import pl.eod.encje.UzytkownikJpaController;
import pl.eod.encje.WnRodzaje;
import pl.eod.encje.WnUrlop;
import pl.eod.managed.Login;
import pl.eod.managwn.UrlopObceM;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author 103039
 */
@WebService(serviceName = "EodtUrlopWs")
public class EodtUrlopWs implements Serializable {

    private static final long serialVersionUID = 1L;

    private final UzytkownikJpaController userC = new UzytkownikJpaController();

    /**
     * Web service operation
     *
     * @param loginS
     * @return
     */
    @WebMethod(operationName = "getUsers")
    public List<String> getUsers(@WebParam(name = "login") String loginS) {
        List<String> wynik = new ArrayList<>();
        UrlopObceM urOm = new UrlopObceM();
        Login login = new Login();
        login.logFromWebServ(loginS);
        urOm.setLogin(login);
        urOm.getLogin().logFromWebServ(loginS);
        for (Struktura s : urOm.getLogin().getZalogowany().getObceWnioski()) {
            wynik.add(s.getUserId().getAdrEmail());
        }
        return wynik;
    }

    /**
     * Web service operation
     *
     * @param userEmail
     * @param loginS
     * @param dataUrlopu
     * @param godzOd
     * @param godzDo
     * @param idRodzaj
     * @param infoDod
     * @param pracodawca
     * @param kwota
     * @param miejsce
     * @param cel
     * @param srodekLok
     * @return
     */
    //raport id 40 tez ta metoda
    @WebMethod(operationName = "createUrlopGodziny")
    public String createUrlopGodziny(@WebParam(name = "userEmail") String userEmail, @WebParam(name = "login") String loginS,
            @WebParam(name = "dataUrlopu") Date dataUrlopu, @WebParam(name = "godzOd") Date godzOd, @WebParam(name = "godzDo") Date godzDo,
            @WebParam(name = "idRodziaj") String idRodzaj, @WebParam(name = "infoDod") String infoDod,
            @WebParam(name = "pracodawca") boolean pracodawca, @WebParam(name = "kwota") BigDecimal kwota, @WebParam(name = "miejsce") String miejsce,
            @WebParam(name = "cel") String cel, @WebParam(name = "srodekLok") String srodekLok) {
        Login login = new Login();
        login.logFromWebServ(loginS);
        UrlopObceM urOm = new UrlopObceM();
        urOm.setLogin(login);
        urOm.init();
        urOm.setCalyDzien(false);
        urOm.setDataUrlopu(dataUrlopu);
        urOm.setGodzOdT(godzOd);
        urOm.setGodzDoT(godzDo);

        Uzytkownik prac = userC.findUzytkownikByEmail(userEmail);

        WnUrlop urlop = new WnUrlop();
        urlop.setUzytkownik(prac);
        urlop.setInfoDod(infoDod);
        urlop.setPracodawca(pracodawca);
        urlop.setKwotaWs(kwota);
        urlop.setMiejsce(miejsce);
        urlop.setCel(cel);
        urlop.setSrodekLok(srodekLok);
        WnRodzaje rodzaj = new WnRodzaje(new Long(idRodzaj));
        urlop.setRodzajId(rodzaj);
        try {
            urOm.setUrlop(urlop);
            urOm.dodaj();
        } catch (ParseException ex) {
            Logger.getLogger(EodtUrlopWs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return urOm.getWynikWs();
    }

    @WebMethod(operationName = "createUrlopCalyDzien")
    public String createUrlopCalyDzien(@WebParam(name = "userEmail") String userEmail, @WebParam(name = "login") String loginS,
            @WebParam(name = "dataOd") Date dataOd, @WebParam(name = "dataDo") Date dataDo, @WebParam(name = "idRodzaj") String idRodzaj,
            @WebParam(name = "infoDod") String infoDod,
            @WebParam(name = "pracodawca") boolean pracodawca, @WebParam(name = "kwota") BigDecimal kwota, @WebParam(name = "miejsce") String miejsce,
            @WebParam(name = "cel") String cel, @WebParam(name = "srodekLok") String srodekLok) {
        Login login = new Login();
        login.logFromWebServ(loginS);
        UrlopObceM urOm = new UrlopObceM();
        urOm.setLogin(login);
        urOm.init();
        urOm.setCalyDzien(true);

        Uzytkownik prac = userC.findUzytkownikByEmail(userEmail);

        WnUrlop urlop = new WnUrlop();
        urlop.setUzytkownik(prac);
        WnRodzaje rodzaj = new WnRodzaje(new Long(idRodzaj));
        urlop.setRodzajId(rodzaj);
        urlop.setDataOd(dataOd);
        urlop.setDataDo(dataDo);
        urlop.setInfoDod(infoDod);
        urlop.setPracodawca(pracodawca);
        urlop.setKwotaWs(kwota);
        urlop.setMiejsce(miejsce);
        urlop.setCel(cel);
        urlop.setSrodekLok(srodekLok);
        try {
            urOm.setUrlop(urlop);
            urOm.dodaj();
        } catch (ParseException ex) {
            Logger.getLogger(EodtUrlopWs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return urOm.getWynikWs();
    }

    @WebMethod(operationName = "getMojeWnLista")
    @SuppressWarnings("unchecked")
    public List<WnUrlop> getMojeWnLista(@WebParam(name = "login") String loginS) {
    Login login = new Login();
        login.logFromWebServ(loginS);
        UrlopObceM urOm = new UrlopObceM();
        urOm.setLogin(login);
        urOm.init();
        List<WnUrlop> wynik=(List<WnUrlop>) urOm.getUrlopyList().getWrappedData();
        return wynik;
    }

    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    private void ArrayList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
