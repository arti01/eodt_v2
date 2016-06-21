/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eodtwsc;

import javax.xml.ws.WebServiceRef;

/**
 *
 * @author arti01
 */
public class EodtWSC {
@WebServiceRef(wsdlLocation="http://localhost:8080/eodt_j8/EodtUrlopWs?wsdl")
    static EodtUrlopWs service;

    public static void main(String[] args) {
        try {
            EodtWSC client = new EodtWSC();
            client.doTest(args);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void doTest(String[] args) {
        try {
            service.
            EodtUrlopWs ws =service.getEodtUrlopWsPort();
            System.out.println("Retrieving the port fromthe following service: " + service);
            ws.hello("sssssssss");
            System.out.println("Invoking the sayHello operationon the port.");


            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
}
