/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import eodtwscUrlop.ListaWn;
import eodtwsc.WnUrlop;
import eodtwscUrlop.TworzUrlopy;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.xml.datatype.DatatypeConfigurationException;

/**
 *
 * @author arti01
 */
@ManagedBean
@RequestScoped
public class Test implements Serializable{

    private static final long serialVersionUID = 1L;

    String testS = "";

    public void testUrl() throws MalformedURLException, DatatypeConfigurationException {
        TworzUrlopy tc = new TworzUrlopy();
        testS = tc.wywolaj();
        System.err.println("zlozono wniosek" + testS);
    }
    
    public List<WnUrlop> getLista(){
        ListaWn lwn=new ListaWn();
        return  lwn.wywolaj();
    }

    public String getTestS() {
        return testS;
    }

    public void setTestS(String testS) {
        this.testS = testS;
    }

}
