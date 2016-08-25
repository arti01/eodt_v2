package eodtwscRepoz;

import przyklady.WprowadzWniosek;
import eodtwscUrlop.*;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EodtRepozDocClient implements Serializable{

    private static final long serialVersionUID = 1L;

    private String urlS;
    private eodtwscRepoz.RepozDokWs_Service service;
    //private eodtwsc.EodtUrlopWs port;//pamietac, aby uzywac getera
    private static String WSDL="/RepozDokWs?wsdl";

    public EodtRepozDocClient() {
    }

    public EodtRepozDocClient(String urlS) {
        this.urlS = urlS;
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

    private RepozDokWs_Service getService() {
        try {
            service=new RepozDokWs_Service(new URL(this.getUrlS() + WSDL));
        } catch (MalformedURLException ex) {
            Logger.getLogger(WprowadzWniosek.class.getName()).log(Level.SEVERE, null, ex);
        }
        return service;
    }

    public RepozDokWs getPort() {
        return this.getService().getRepozDokWsPort();
    }


    public String hello(java.lang.String name) {
        return getPort().hello(name);
    }

    public DrzPF getDrzewo() {
        return getPort().getDrzewo();
    }
    
}
