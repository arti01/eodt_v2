/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.arti01.encja;

import abst.AbstKontroler;
import encje.exceptions.BrakOprocentowaniaEx;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class KredytKontr extends AbstKontroler<Kredyt> {
    
    public KredytKontr() {
        super(new Kredyt());
    }
    
    public Wynik oblicz(Kredyt kredyt) throws BrakOprocentowaniaEx {
        //stworzenie wyniku
        Wynik wynik = new Wynik(kredyt);
        //pętla dla rat
        Date dataRaty = kredyt.getDataPierwSplaty();
        BigDecimal rataKapitalowa = new BigDecimal(kredyt.getKwota()).divide(new BigDecimal(kredyt.getLiczbaRat()), 2, RoundingMode.HALF_EVEN);
        BigDecimal doSplaty = new BigDecimal(kredyt.getKwota()).setScale(2);
        BigDecimal odsetki;
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataRaty);
        for (int i = 1; i <= kredyt.getLiczbaRat(); i++) {
            Double oproc;
            try {
                oproc = new OkresOdsKontr().findDlaDaty(cal.getTime(), kredyt.getBank()).getOprocentowanie();
            } catch (NullPointerException|ArrayIndexOutOfBoundsException ex) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
                throw new BrakOprocentowaniaEx("brak oprocentowania dla" + sdf.format(cal.getTime()));
            }
            odsetki = doSplaty.multiply(new BigDecimal(oproc)).divide(new BigDecimal(12), 2, RoundingMode.HALF_EVEN);
            doSplaty = doSplaty.subtract(rataKapitalowa);
            WynikRata rata = new WynikRata();
            rata.setRataData(cal.getTime());
            rata.setDoSplaty(doSplaty);
            rata.setRataKapitalowa(rataKapitalowa);
            rata.setRataNumer(i);
            rata.setRataOdsetkowa(odsetki);
            rata.setOprocentowanie(oproc);
            rata.setWynik(wynik);
            wynik.getWynikRataList().add(rata);
            cal.add(Calendar.MONTH, 1);
        }
        WynikKontr wkC = new WynikKontr();
        kredyt.getWynikList().add(wynik);
        this.edit(kredyt);
        return wynik;
    }
}
