package ek.abstr;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

public abstract class AbstMg<X extends AbstEncja, Y extends AbstKontroler<X>> {

    public List<X> lista=new ArrayList<>();
    public final Y dcC;
    public X obiekt;
    public String error;
    private final String link;

    @SuppressWarnings({"unchecked", "unchecked"})
    public AbstMg(String s, AbstKontroler<X> ak, X obiekt) throws InstantiationException, IllegalAccessException {
        link = s;
        this.dcC = (Y) ak.getClass().newInstance();
        this.obiekt = obiekt;
        lista=dcC.findEntities();
    }

    @SuppressWarnings("unchecked")
    public void newObiekt() throws InstantiationException, IllegalAccessException {
        obiekt = (X) obiekt.getClass().newInstance();
    }

    @SuppressWarnings("unchecked")
    public void refresh() throws InstantiationException, IllegalAccessException {
        lista=dcC.findEntities();
        obiekt = (X) obiekt.getClass().newInstance();
        error = null;
    }

    public Map<String, String> dodaj() throws InstantiationException, IllegalAccessException {
        @SuppressWarnings("unchecked")
        Map<String, String> errorMap = dcC.create(obiekt);
        if (!errorMap.isEmpty()) {
            FacesContext context = FacesContext.getCurrentInstance();
            //przycisk zapisz/dodaj
            UIComponent zapisz = UIComponent.getCurrentComponent(context);
            for (Map.Entry<String, String> entry : errorMap.entrySet()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, entry.getValue(), entry.getValue());
                UIComponent input = zapisz.getParent().findComponent(entry.getKey());
                try {
                    context.addMessage(input.getClientId(context), message);
                } catch (NullPointerException e) {
                    context.addMessage(null, message);
                    System.err.println("po migracji na PF wywalic");
                }
            }
        } else {
            refresh();
        }
        return errorMap;
    }

    public Map<String, String> edytuj() throws Exception {
        @SuppressWarnings("unchecked")
        Map<String, String> errorMap = dcC.edit(obiekt);
        if (!errorMap.isEmpty()) {
            FacesContext context = FacesContext.getCurrentInstance();
            //przycisk zapisz/dodaj
            UIComponent zapisz = UIComponent.getCurrentComponent(context);
            for (Map.Entry<String, String> entry : errorMap.entrySet()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, entry.getValue(), entry.getValue());
                UIComponent input = zapisz.getParent().findComponent(entry.getKey());    
                try {
                    context.addMessage(input.getClientId(context), message);
                } catch (NullPointerException e) {
                    context.addMessage(null, message);
                }
                lista=dcC.findEntities();
            }
        } else {
            refresh();
        }
        return errorMap;
    }

    @SuppressWarnings("unchecked")
    public void usun() throws InstantiationException, IllegalAccessException {
        //rodzajGrupa=lista.getRowData();
        dcC.destroy(obiekt);
        refresh();
    }

    public String list() throws InstantiationException, IllegalAccessException {
        refresh();
        return link;
    }

    public List<X> getLista() {
        return lista;
    }

    public void setLista(List<X> lista) {
        this.lista = lista;
    }

    public X getObiekt() {
        return obiekt;
    }

    public void setObiekt(X obiekt) {
        this.obiekt = obiekt;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Y getDcC() {
        return dcC;
    }

    public void pfMess(Severity sev, String mess, String messDet) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(sev, mess, messDet);
        context.addMessage(null, message);
    }

}
