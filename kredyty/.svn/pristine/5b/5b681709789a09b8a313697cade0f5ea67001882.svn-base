package pl.arti01.mg.kredyt;


import abst.AbstMg;
import encje.exceptions.BrakOprocentowaniaEx;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import pl.arti01.encja.Kredyt;
import pl.arti01.encja.KredytKontr;

@ManagedBean(name = "Kredyty")
@SessionScoped
public class Kredyty extends AbstMg<Kredyt, KredytKontr>{
    private static final long serialVersionUID = 1L;
    @ManagedProperty(value = "#{Wyniki}")
    Wyniki wyniki;
    public Kredyty() throws InstantiationException, IllegalAccessException {
        super("/kredyty/kredyty?faces-redirect=true", new KredytKontr(), new Kredyt());
    }
    
    public String oblicz() throws InstantiationException, IllegalAccessException{
        try{
            wyniki.setObiekt(dcC.oblicz(obiekt));
        }catch (BrakOprocentowaniaEx ex){
            FacesContext context = FacesContext.getCurrentInstance();
            UIComponent zapisz = UIComponent.getCurrentComponent(context);
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ex.getMessage());
             context.addMessage(zapisz.getClientId(context), message);
             return null;
        }
        this.refresh();
        return "/kredyty/wynik?faces-redirect=true";
    }

    public Wyniki getWyniki() {
        return wyniki;
    }

    public void setWyniki(Wyniki wyniki) {
        this.wyniki = wyniki;
    }
    
    
}