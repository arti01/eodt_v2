package eodtwscRepoz;

import eodtwscMail.WyslijMail;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

public class EodtRepozWyslijZal implements Serializable {

    private static final long serialVersionUID = 1L;

    private String urlS;
    private eodtwscMail.WyslijMail service;
    //private eodtwsc.EodtUrlopWs port;//pamietac, aby uzywac getera
    private final String WSDL = "/WyslijMailWs?wsdl";

    public EodtRepozWyslijZal() {
    }
    
    public EodtRepozWyslijZal(String urlS) {
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

    private eodtwscMail.WyslijMail getService() {
        try {
            service = new WyslijMail(new URL(this.getUrlS() + WSDL));
        } catch (MalformedURLException ex) {
            //Logger.getLogger(TestMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        return service;
    }

    public eodtwscMail.WyslijMailWs getPort() {
        return this.getService().getWyslijMailWsPort();
    }

    public String zZalaczZserwera(java.lang.String adresat, java.lang.String temat, java.lang.String tresc, java.lang.String arg3) {
        return getPort().zZalaczZserwera(adresat, temat, tresc, arg3);
    }

}
