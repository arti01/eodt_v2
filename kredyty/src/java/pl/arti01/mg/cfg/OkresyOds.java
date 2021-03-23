package pl.arti01.mg.cfg;

import abst.AbstMg;
import pl.arti01.encja.OkresOds;
import pl.arti01.encja.OkresOdsKontr;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "OkresyOds")
@SessionScoped
public class OkresyOds extends AbstMg<OkresOds, OkresOdsKontr>{
    private static final long serialVersionUID = 1L;
    public OkresyOds() throws InstantiationException, IllegalAccessException {
        super("/cfg/okresOds", new OkresOdsKontr(), new OkresOds());
    }
}