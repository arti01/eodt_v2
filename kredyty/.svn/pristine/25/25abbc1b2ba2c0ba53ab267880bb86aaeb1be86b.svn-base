package abst;

import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import encje.exceptions.IllegalOrphanException;
import encje.exceptions.NonexistentEntityException;
import java.io.Serializable;

public abstract class AbstMg<X extends AbstEncja, Y extends AbstKontroler<X>> implements Serializable {
    private static final long serialVersionUID = 1L;

    public DataModel<X> lista;
    public final Y dcC;
    public X obiekt;
    public String error;
    private final String link;

    @SuppressWarnings({"unchecked", "unchecked"})
    public AbstMg(String s, AbstKontroler<X> ak, X obiekt) throws InstantiationException, IllegalAccessException {
        link = s;
        this.lista = new ListDataModel<>();
        this.dcC = (Y) ak.getClass().newInstance();
        this.obiekt = obiekt;
        lista.setWrappedData(dcC.findEntities());
    }

    @SuppressWarnings("unchecked")
     public void newObiekt() throws InstantiationException, IllegalAccessException {
        obiekt = (X) obiekt.getClass().newInstance();
    }
    
    @SuppressWarnings("unchecked")
    public void refresh() throws InstantiationException, IllegalAccessException {
        lista.setWrappedData(dcC.findEntities());
        obiekt = (X) obiekt.getClass().newInstance();
        error = null;
    }

    public void dodaj() throws InstantiationException, IllegalAccessException {
        @SuppressWarnings("unchecked")
        Map<String, String> errorMap = dcC.create(obiekt);
        if (!errorMap.isEmpty()) {
            FacesContext context = FacesContext.getCurrentInstance();
            //przycisk zapisz/dodaj
            UIComponent zapisz = UIComponent.getCurrentComponent(context);
            for (Map.Entry<String, String> entry : errorMap.entrySet()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, entry.getValue(), entry.getValue());
                UIComponent input = zapisz.getParent().findComponent(entry.getKey());
                context.addMessage(input.getClientId(context), message);
            }
        } else {
            refresh();
        }
    }

    public void edytuj() throws IllegalOrphanException, NonexistentEntityException, Exception {
        @SuppressWarnings("unchecked")
        Map<String, String> errorMap = dcC.edit(obiekt);
        if (!errorMap.isEmpty()) {
            FacesContext context = FacesContext.getCurrentInstance();
            //przycisk zapisz/dodaj
            UIComponent zapisz = UIComponent.getCurrentComponent(context);
            for (Map.Entry<String, String> entry : errorMap.entrySet()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, entry.getValue(), entry.getValue());
                UIComponent input = zapisz.getParent().findComponent(entry.getKey());
                context.addMessage(input.getClientId(context), message);
                lista.setWrappedData(dcC.findEntities());
            }
        } else {
            refresh();
        }
    }
    
    @SuppressWarnings("unchecked")
    public void usun() throws IllegalOrphanException, NonexistentEntityException, InstantiationException, IllegalAccessException {
        //rodzajGrupa=lista.getRowData();
        dcC.destroy(obiekt);
        refresh();
    }

    public String list() throws InstantiationException, IllegalAccessException {
        refresh();
        return link;
    }

    public DataModel<X> getLista() {
        return lista;
    }

    public void setLista(DataModel<X> lista) {
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

}
