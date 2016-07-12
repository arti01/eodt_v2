/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eodtwscUrlop;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author 103039
 */
public class TestMain {

    public static void main(String[] args) throws MalformedURLException, DatatypeConfigurationException {

        //System.err.println(new eodtUrlopyClient("http://localhost:8080/eodt_j8").hello("ssssssssssss"));

        GregorianCalendar c = new GregorianCalendar();
        c.setTime(new Date());
        c.add(Calendar.DATE, 4);
        XMLGregorianCalendar dateOd = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        c.add(Calendar.DATE, 5);
        XMLGregorianCalendar dateDo = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        Long idWniosku;
        String wynik=new EodtUrlopyClient("http://localhost:8080/eodt_j8").createUrlopCalyDzien("aa@aa.pl", "kk@kk.pl", dateOd, dateDo, "1", "dddddddd",
                false, new BigDecimal("111.11"), "miejsce", "cel", "srodekLok");
        try {//jesli jest ok zwraca nr wniosku, jesli nie pokazuje co bylo nie tak
            idWniosku=new Long(wynik);
            System.out.println("id wniosku urlopowego: "+idWniosku);
        } catch (NumberFormatException nfe){
            System.err.println(wynik);
        }
        
        c.set(Calendar.HOUR_OF_DAY, 7);
        XMLGregorianCalendar godzOd = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        c.set(Calendar.HOUR_OF_DAY, 15);
        XMLGregorianCalendar godzDo = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        wynik=new EodtUrlopyClient("http://localhost:8080/eodt_j8").createUrlopGodziny("aa@aa.pl", "kk@kk.pl", dateOd, godzOd, godzDo, "1","info dod",
               false, new BigDecimal("111.11"), "miejsce", "cel", "srodekLok");
        try {//jesli jest ok zwraca nr wniosku, jesli nie pokazuje co bylo nie tak
            idWniosku=new Long(wynik);
            System.out.println("id wniosku urlopowego: "+idWniosku);
        } catch (NumberFormatException nfe){
            System.err.println(wynik);
        }
        
    }

}
