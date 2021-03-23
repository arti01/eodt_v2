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
import java.util.Objects;

public class KredytKontr extends AbstKontroler<Kredyt> {
    
    public KredytKontr() {
        super(new Kredyt());
    }
    
    
    public Wynik oblicz(Kredyt kredyt) throws BrakOprocentowaniaEx{
        if(kredyt.isRatyRowne()) {
            return this.obliczRowneRaty(kredyt);
        }
        else return this.obliczMalejaceRaty(kredyt);
    }
    
    public Wynik obliczMalejaceRaty(Kredyt kredyt) throws BrakOprocentowaniaEx {
        //stworzenie wyniku
        Wynik wynik = new Wynik(kredyt);
        //pętla dla rat
        Date dataRaty = kredyt.getDataPierwSplaty();
        BigDecimal rataKapitalowa = kredyt.getKwota().divide(new BigDecimal(kredyt.getLiczbaRat()), 2, RoundingMode.HALF_EVEN);
        BigDecimal doSplaty = kredyt.getKwota().setScale(2);
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
            odsetki = doSplaty.multiply(new BigDecimal(oproc)).divide(new BigDecimal(12), 10, RoundingMode.HALF_EVEN);
            doSplaty = doSplaty.subtract(rataKapitalowa);
            WynikRata rata = new WynikRata();
            rata.setRataData(cal.getTime());
            rata.setDoSplaty(doSplaty);
            rata.setRataKapitalowa(rataKapitalowa);
            rata.setRataNumer(i);
            rata.setRataOdsetkowa(odsetki.setScale(2, RoundingMode.HALF_EVEN));
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
    
    public Wynik obliczRowneRaty(Kredyt kredyt) throws BrakOprocentowaniaEx {
        //stworzenie wyniku
        Wynik wynik = new Wynik(kredyt);
        //pętla dla rat
        Date dataRaty = kredyt.getDataPierwSplaty();
        BigDecimal doSplaty = kredyt.getKwota().setScale(2);
        BigDecimal kwota=kredyt.getKwota().setScale(2);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataRaty);
        int liczbaRat=kredyt.getLiczbaRat();
        Double oproc=new Double("0");
        Double oprocOld=new Double("0");
        for (int i = 1; i <= kredyt.getLiczbaRat(); i++) {
            
            try {
                oproc = new OkresOdsKontr().findDlaDaty(cal.getTime(), kredyt.getBank()).getOprocentowanie();
            } catch (NullPointerException|ArrayIndexOutOfBoundsException ex) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
                throw new BrakOprocentowaniaEx("brak oprocentowania dla" + sdf.format(cal.getTime()));
            }
            //jesli oprocentowanie jest nowe, to zmien kwote (przy pierwszej tez będzie ok, bo do splaty to calosc
            if(!Objects.equals(oproc, oprocOld)){
                kwota=doSplaty;
                liczbaRat=kredyt.getLiczbaRat()-i+1;
                oprocOld=oproc;
            }
            
            BigDecimal odsetki=this.obliczOdsetkiDlaOkresu(oproc, doSplaty);
            BigDecimal rataKapitalowa = this.obliczRateDlaOkresu(kwota, oproc, liczbaRat).subtract(odsetki);
            

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
        kredyt.getWynikList().add(wynik);
        this.edit(kredyt);
        return wynik;
    }
    
    public BigDecimal obliczOdsetkiDlaOkresu(Double procent, BigDecimal kwotaDoSplaty){
        BigDecimal procPerOkr=new BigDecimal(procent).divide(new BigDecimal(12),10,RoundingMode.HALF_EVEN);
        return kwotaDoSplaty.multiply(procPerOkr).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }
    
    public BigDecimal obliczRateDlaOkresu(BigDecimal kwota, Double procent, int liczbaRat){
        BigDecimal procPerOkr=new BigDecimal(procent).divide(new BigDecimal(12),10,RoundingMode.HALF_EVEN);
        //stala (1+p)^n
        BigDecimal stala=new BigDecimal(1).add(procPerOkr);
        stala=stala.pow(liczbaRat);
        BigDecimal gora=procPerOkr.multiply(stala);
        BigDecimal mianownik=stala.subtract(new BigDecimal(1)).setScale(10, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal mnoz=kwota.multiply(gora).setScale(10, BigDecimal.ROUND_HALF_EVEN);
        return mnoz.divide(mianownik, RoundingMode.HALF_EVEN).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }
}
