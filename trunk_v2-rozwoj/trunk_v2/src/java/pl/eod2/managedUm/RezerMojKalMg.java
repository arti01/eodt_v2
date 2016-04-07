package pl.eod2.managedUm;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.SlideEndEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;
import pl.eod.abstr.AbstMg;
import pl.eod.encje.Uzytkownik;
import pl.eod.managed.Login;
import pl.eod2.encje.UmRezerwacje;
import pl.eod2.encje.UmRezerwacjeKontr;

@ManagedBean(name = "RezerMojKalMg")
@SessionScoped
public class RezerMojKalMg extends AbstMg<UmRezerwacje, UmRezerwacjeKontr> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{login}")
    private Login login;

    private ScheduleModel eventModel;
    private DefaultScheduleEvent event = new DefaultScheduleEvent();
    private int number2=3;
    private Uzytkownik uzyt;

    public RezerMojKalMg() throws InstantiationException, IllegalAccessException {
        super("/um/rez_moj_kal", new UmRezerwacjeKontr(), new UmRezerwacje());
    }

    @PostConstruct
    private void init() {
        eventModel = new DefaultScheduleModel();
        uzyt=login.getZalogowany().getUserId();
        try {
            refresh();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(RezerMojKalMg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void refresh() throws InstantiationException, IllegalAccessException {
        super.refresh();
        login.refresh();
        ustawSched();
    }

    @Override
    public String list() throws InstantiationException, IllegalAccessException{
        uzyt=login.getZalogowany().getUserId();
        return super.list();
    }
    
    public String listObcy() throws InstantiationException, IllegalAccessException{
        return super.list();
    }
    
    public void ustawSched() {
        eventModel.clear();
        for (UmRezerwacje rez : uzyt.getRezUczestnikList()) {
            DefaultScheduleEvent ev = new DefaultScheduleEvent(rez.getNazwa() + "\ndla: " + rez.getUrzadzenie().getNazwa(), rez.getDataOd(), rez.getDataDo(), rez);
            ev.setEditable(false);
            eventModel.addEvent(ev);
        }
    }

    public void onEventSelect(SelectEvent selectEvent) {
        event = (DefaultScheduleEvent) selectEvent.getObject();
        obiekt = dcC.findObiekt(((UmRezerwacje) event.getData()).getId());
    }

    public void onSlideEnd(SlideEndEvent event) {
        number2 = event.getValue();
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public DefaultScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(DefaultScheduleEvent event) {
        this.event = event;
    }

    public int getNumber2() {
        return number2;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }

    public Uzytkownik getUzyt() {
        return uzyt;
    }

    public void setUzyt(Uzytkownik uzyt) {
        this.uzyt = uzyt;
    }

}