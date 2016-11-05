/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ek.manage.logineod;

import ek.abstr.AbstMg;
import ek.encje.EkConfigKontr;
import ek.encje.EkObceLinki;
import ek.encje.EKObceLinkiKontr;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import pl.eod.email.MailWyslij;
import pl.eod2.encje.Repozytoria;

@ManagedBean(name = "RepDokMg")
@SessionScoped
public class RepDokMg extends AbstMg<EkObceLinki, EKObceLinkiKontr> {

    private final int listaSize = 0;
    private String dir_repo;
    private TreeNode root;
    DrzPF drR;
    private String shortName;
    private String fullPath;
    private List<TreeNode> inneRoot;

    @ManagedProperty(value = "#{LoginMg}")
    LoginMg loginMg;

    public RepDokMg() throws InstantiationException, IllegalAccessException {
        super("/login/rep_dok", new EKObceLinkiKontr(), new EkObceLinki());
    }

    @PostConstruct
    public void init() {
        try {
            EkConfigKontr confC = new EkConfigKontr();
            this.dir_repo = confC.findEntities("dir_repo").getWartosc();
            if (this.dir_repo != null && this.dir_repo.length() > 0) {
                this.drzewko();
                this.inneDrzewka();
            }
            super.refresh();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(RepDokMg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void drzewko() {
        root = new DefaultTreeNode(this.dir_repo, null);
        drR = new DrzPF(dir_repo, "repozytorium", null);
        this.createTree(root, drR);
    }

    private TreeNode createTree(TreeNode node, DrzPF dp) {
        TreeNode tr = new DefaultTreeNode(dp.getType(), dp, node);
        for (DrzPF dpN : dp.getChidren()) {
            TreeNode tr1 = this.createTree(tr, dpN);
        }
        return tr;
    }

    private void inneDrzewka() {
        inneRoot = new ArrayList<>();
        List<Repozytoria> repoAll = new ArrayList<>();
        repoAll.addAll(loginMg.getUser().getStruktura().getRepozytoriaList());
        for (Repozytoria rep : loginMg.getUser().getStruktura().getSzefId().getRepozytoriaList()) {
            if (!repoAll.contains(rep)) {
                repoAll.add(rep);
            }
        }
        for (Repozytoria rep : repoAll) {
            DefaultTreeNode rootT = new DefaultTreeNode(rep.getNazwa(), null);
            DrzPF drRT = new DrzPF(rep.getSciezka(), rep.getNazwa(), null);
            this.createTree(rootT, drRT);
            inneRoot.add(rootT);
        }
    }

    public void onNodeSelect(NodeSelectEvent event) {
        event.getTreeNode().setExpanded(!event.getTreeNode().isExpanded());
    }

    public void zZalaczZserwera() {
        MailWyslij mailWyslij = new MailWyslij("Dokument: " + shortName, "Przesyłamy dokument: " + shortName, loginMg.getUserProfil().getEmail());
        mailWyslij.setZalacznikZserwera(fullPath);
        FacesContext context = FacesContext.getCurrentInstance();
        UIComponent zapisz = UIComponent.getCurrentComponent(context);
        FacesMessage message = new FacesMessage();
        String mess = mailWyslij.wyslij();
        System.err.println(mess);
        message.setSummary(mess);
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        context.addMessage(zapisz.getClientId(context), message);
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public DrzPF getDrR() {
        return drR;
    }

    public void setDrR(DrzPF drR) {
        this.drR = drR;
    }

    public LoginMg getLoginMg() {
        return loginMg;
    }

    public void setLoginMg(LoginMg loginMg) {
        this.loginMg = loginMg;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public List<TreeNode> getInneRoot() {
        return inneRoot;
    }

    public void setInneRoot(List<TreeNode> inneRoot) {
        this.inneRoot = inneRoot;
    }

}
