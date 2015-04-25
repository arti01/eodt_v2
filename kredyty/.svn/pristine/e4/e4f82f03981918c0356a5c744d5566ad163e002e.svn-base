package mg.cfg;

import abst.AbstMg;
import encja.OkresOds;
import encja.OkresOdsKontr;
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