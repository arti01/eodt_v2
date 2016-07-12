/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserv;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import pl.eod.cron4j.MailWyslij;

/**
 *
 * @author arti01
 */
@WebService(serviceName = "WyslijMail")
public class WyslijMailWs {

    /**
     * Web service operation
     * @param adresat
     * @param temat
     * @param tresc
     * @return 
     */
    @WebMethod(operationName = "bezZalacznika")
    public String bezZalacznika(@WebParam(name = "adresat") String adresat, @WebParam(name = "temat") String temat, @WebParam(name = "tresc") String tresc) {
        MailWyslij mailWyslij = new MailWyslij(temat, tresc, adresat);
        return mailWyslij.wyslij();
    }

    /**
     * Web service operation
     * @param adresat
     * @param temat
     * @param tresc
     * @param zalFullPath
     * @return 
     */
    @WebMethod(operationName = "zZalaczZserwera")
    public String zZalaczZserwera(@WebParam(name = "adresat") String adresat, @WebParam(name = "temat") String temat, @WebParam(name = "tresc") String tresc, String zalFullPath) {
        MailWyslij mailWyslij = new MailWyslij(temat, tresc, adresat);
        mailWyslij.setZalacznikZserwera(zalFullPath);
        return mailWyslij.wyslij();
    }

    
}
