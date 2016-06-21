package webserv;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedProperty;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
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
     * @return
     */
    @WebMethod(operationName = "createUrlop")
    public HashMap createUrlop(@WebParam(name = "userEmail") String userEmail, @WebParam(name = "login") String loginS) {
        Map<String, String>wynik=new HashMap<>();
        Login login = new Login();
        login.logFromWebServ(loginS);
        UrlopObceM urOm = new UrlopObceM();
        urOm.setLogin(login);
        urOm.init();

        Uzytkownik prac = userC.findUzytkownikByEmail(userEmail);

        WnUrlop urlop = new WnUrlop();
        urlop.setUzytkownik(prac);
        WnRodzaje rodzaj = new WnRodzaje(new Long("1"));
        urlop.setRodzajId(rodzaj);
        urlop.setDataOd(new Date());
        urlop.setDataDo(new Date());
        try {
            urOm.setUrlop(urlop);
            urOm.dodaj();
        } catch (ParseException ex) {
            Logger.getLogger(EodtUrlopWs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return urOm.getWynikWS();
    }

    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

}
