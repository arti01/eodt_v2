/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;


import eodtwsc.TestWsC;
import java.net.MalformedURLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.xml.datatype.DatatypeConfigurationException;

/**
 *
 * @author arti01
 */
@ManagedBean
@RequestScoped
public class Test {
    String testS="ffffffffffff";

public void testUrl() throws MalformedURLException, DatatypeConfigurationException{
    System.err.println("ddddddd");
    TestWsC tc=new TestWsC();
    testS=tc.wywolaj();
}

    public String getTestS() {
        return testS;
    }

    public void setTestS(String testS) {
        this.testS = testS;
    }
    
}
