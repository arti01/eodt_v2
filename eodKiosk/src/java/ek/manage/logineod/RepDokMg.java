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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean(name = "RepDokMg")
@SessionScoped
public class RepDokMg extends AbstMg<EkObceLinki, EKObceLinkiKontr> {

    private final int listaSize = 0;
    private String dir_repo = "/home/arti01/tmp";
    private TreeNode root;
    DrzPF drR;

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

}
