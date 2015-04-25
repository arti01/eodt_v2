package pl.arti01.mg.kredyt;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;


@ManagedBean
@SessionScoped
public class FileDownload implements Serializable {

    private static final long serialVersionUID = 1L;
    @ManagedProperty(value = "#{Wyniki}")
    private Wyniki wyniki;


    
    public void download() {
        final HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setHeader("Content-Disposition", "attachment;filename=\"" + wyniki.getObiekt().getPlik().getNazwa() + ".csv\""); // or whatever type you're sending back
        try {
            response.getOutputStream().write(wyniki.getObiekt().getPlik().getPlik()); // from the UploadDetails bean
            response.setContentLength(wyniki.getObiekt().getPlik().getPlik().length);
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FacesContext.getCurrentInstance().responseComplete();
    }

    
    public long getTimeStamp() {
        return System.currentTimeMillis();
    }

    public Wyniki getWyniki() {
        return wyniki;
    }

    public void setWyniki(Wyniki wyniki) {
        this.wyniki = wyniki;
    }

   
}