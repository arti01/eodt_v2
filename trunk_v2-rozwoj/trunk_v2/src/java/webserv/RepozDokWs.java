/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserv;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.primefaces.model.DefaultTreeNode;
import pl.eod2.managedRep.DrzPF;
import pl.eod2.managedRep.RepozytMg;

/**
 *
 * @author arti01
 */
@WebService(serviceName = "RepozDokWs")
public class RepozDokWs {

    /**
     * This is a sample web service operation
     * @param txt
     * @return 
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "HelloRepoz " + txt + " !";
    }

    /**
     * Web service operation
     * @return 
     */
    @WebMethod(operationName = "getDrzewo")
    public DrzPF getDrzewo() {
        RepozytMg repMg=new RepozytMg();
        repMg.init();
        return repMg.getDrR();
    }
    
    
}
