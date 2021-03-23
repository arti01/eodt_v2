package pl.arti01.mg.kredyt;


import abst.AbstMg;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import pl.arti01.encja.Wynik;
import pl.arti01.encja.WynikKontr;

@ManagedBean(name = "Wyniki")
@SessionScoped
public class Wyniki extends AbstMg<Wynik, WynikKontr>{
    private static final long serialVersionUID = 1L;
    public Wyniki() throws InstantiationException, IllegalAccessException {
        super("/kredyty/wynikList", new WynikKontr(), new Wynik());
    }
}