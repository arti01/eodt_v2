/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webserv;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedProperty;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import pl.eod.encje.Struktura;
import pl.eod.managed.Login;
import pl.eod.managwn.UrlopM;
import pl.eod.managwn.UrlopObceM;

@WebService(serviceName = "eodtUrlop")
public class eodtUrlop implements Serializable{
@ManagedProperty(value = "#{UrlopObceM}")
private UrlopObceM urObcM;

    private static final long serialVersionUID = 1L;

    /**
     * This is a sample web service operation
     * @param txt
     * @return 
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     * @param loginS
     * @return 
     */
    @WebMethod(operationName = "testUrlop")
    public List<Struktura> testUrlop(@WebParam(name = "login") String loginS) {
        UrlopObceM urOm=new UrlopObceM();
        Login login=new Login();
        login.logFromWebServ(loginS);
        urOm.setLogin(login);
        //return login.getZalogowany().getUserId().getFullname();
        urOm.getLogin().logFromWebServ(loginS);
        return urOm.getLogin().getZalogowany().getObceWnioski();
    }

    public UrlopObceM getUrObcM() {
        return urObcM;
    }

    public void setUrObcM(UrlopObceM urObcM) {
        this.urObcM = urObcM;
    }

}
