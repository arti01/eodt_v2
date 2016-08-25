/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eodtwscUrlop;

import przyklady.WprowadzWniosek;
import eodtwsc.EodtUrlopWs;
import eodtwsc.EodtUrlopWs_Service;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebParam;

public class EodtUrlopyClient {

    private String urlS;
    private eodtwsc.EodtUrlopWs_Service service;
    //private eodtwsc.EodtUrlopWs port;//pamietac, aby uzywac getera
    private static String WSDL="/EodtUrlopWs?wsdl";

    public EodtUrlopyClient() {
    }

    public EodtUrlopyClient(String urlS) {
        this.urlS = urlS;
    }

    public String hello(java.lang.String name) throws MalformedURLException {
        return this.getPort().hello(name);
    }

    public String getUrlS() {
        if (urlS == null) {
            urlS = "http://127.0.0.1:8080/eodt_ws";
        }
        return urlS;
    }

    public void setUrlS(String urlS) {
        this.urlS = urlS;
    }

    private EodtUrlopWs_Service getService() {
        try {
            service = new eodtwsc.EodtUrlopWs_Service(new URL(this.getUrlS() + WSDL));
        } catch (MalformedURLException ex) {
            Logger.getLogger(WprowadzWniosek.class.getName()).log(Level.SEVERE, null, ex);
        }
        return service;
    }

    public EodtUrlopWs getPort() {
        return this.getService().getEodtUrlopWsPort();
    }

    public String createUrlopCalyDzien(String userEmail, String login, javax.xml.datatype.XMLGregorianCalendar dataOd,
            javax.xml.datatype.XMLGregorianCalendar dataDo, String rodzajId, String infoDod, boolean pracodawca, BigDecimal kwota,
            String miejsce, String cel, String srodekLok) {
        return getPort().createUrlopCalyDzien(userEmail, login, dataOd, dataDo, rodzajId, infoDod, pracodawca, kwota, miejsce, cel, srodekLok);
    }

    public String createUrlopGodziny(String userEmail, String login, javax.xml.datatype.XMLGregorianCalendar dataUrlopu, javax.xml.datatype.XMLGregorianCalendar godzOd,
            javax.xml.datatype.XMLGregorianCalendar godzDo, String rodzajId, String infoDod,
            boolean pracodawca, BigDecimal kwota, String miejsce, String cel, String srodekLok) {
        return getPort().createUrlopGodziny(userEmail, login, dataUrlopu, godzOd, godzDo, rodzajId, infoDod, pracodawca, kwota, miejsce, cel, srodekLok);
    }

    public java.util.List<eodtwsc.WnUrlop> getMojeWnLista(java.lang.String login) {
        return getPort().getMojeWnLista(login);
    }

    
    
}
