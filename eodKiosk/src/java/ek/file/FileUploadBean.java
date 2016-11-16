package ek.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.primefaces.model.UploadedFile;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.InputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;


@ManagedBean(name = "fileUploadBean")
@RequestScoped
public class FileUploadBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private StreamedContent file;
    private String plikDownl;
    private String plikDownlName;
    UploadedFile upFile;

    
    public String getPlikDownl() {
        return plikDownl;
    }

    public void setPlikDownl(String plikDownl) {
        this.plikDownl = plikDownl;
    }

    public String getPlikDownlName() {
        return plikDownlName;
    }

    public void setPlikDownlName(String plikDownlName) {
        this.plikDownlName = plikDownlName;
    }

    public StreamedContent getFile() {
        InputStream stream;
        try {
            stream = new FileInputStream(this.getPlikDownl());
            file = new DefaultStreamedContent(stream, null, this.getPlikDownlName());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUploadBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return file;
    }

    public UploadedFile getUpFile() {
        return upFile;
    }

    public void setUpFile(UploadedFile upFile) {
        this.upFile = upFile;
    }

}
