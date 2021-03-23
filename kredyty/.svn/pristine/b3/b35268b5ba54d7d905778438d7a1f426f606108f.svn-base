package mg.cfg;

import abst.AbstMg;
import encja.Bank;
import encja.BankKontr;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "Banki")
@SessionScoped
public class Banki extends AbstMg<Bank, BankKontr>{
    private static final long serialVersionUID = 1L;
    public Banki() throws InstantiationException, IllegalAccessException {
        super("/cfg/banki", new BankKontr(), new Bank());
    }
}