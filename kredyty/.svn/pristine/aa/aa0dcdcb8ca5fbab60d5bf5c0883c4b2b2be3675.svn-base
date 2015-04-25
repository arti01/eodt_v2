package pl.arti01.mg.kredyt;


import abst.AbstMg;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import pl.arti01.encja.Kredyt;
import pl.arti01.encja.KredytKontr;

@ManagedBean(name = "Kredyty")
@SessionScoped
public class Kredyty extends AbstMg<Kredyt, KredytKontr>{
    private static final long serialVersionUID = 1L;
    @ManagedProperty(value = "#{Wyniki}")
    Wyniki wyniki;
    public Kredyty() throws InstantiationException, IllegalAccessException {
        super("/kredyty/kredyty", new KredytKontr(), new Kredyt());
    }
    
    public String oblicz(){
        wyniki.setObiekt(dcC.oblicz(obiekt));
        return "/kredyty/wynik";
    }

    public Wyniki getWyniki() {
        return wyniki;
    }

    public void setWyniki(Wyniki wyniki) {
        this.wyniki = wyniki;
    }
    
    
}