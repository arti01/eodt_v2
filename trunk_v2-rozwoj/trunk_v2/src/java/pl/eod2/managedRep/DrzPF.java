/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod2.managedRep;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public final class DrzPF implements Comparable<DrzPF>, Serializable {

    private static final long serialVersionUID = 1L;
    @XmlElement
    String fullPath;
    @XmlElement
    String shortName;
    
    @XmlElement
    List<DrzPF> chidren;
    
    //@XmlTransient
    DrzPF parent;
    @XmlElement
    String type;

    public DrzPF() {
    }

    public DrzPF(String fullPath, String shortName, DrzPF parent) {
        this.fullPath = fullPath;
        this.shortName = shortName;
        this.parent = parent;
        File file = new File(fullPath);
        if (file.isDirectory()) {
            this.type = "dir";
        } else {
            this.type = "file";
        }
        //System.err.println(this.fullPath);
        this.szukaj();
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public List<DrzPF> getChidren() {
        Collections.sort(chidren);
        return chidren;
    }

    public void setChidren(List<DrzPF> chidren) {
        this.chidren = chidren;
    }

    public DrzPF getParent() {
        return parent;
    }

    public void setParent(DrzPF parent) {
        this.parent = parent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void szukaj() {
        this.chidren = new ArrayList<>();
        File plikF = new File(this.fullPath);
        if (plikF.isDirectory()) {
            for (File plik : plikF.listFiles()) {
                DrzPF dp = new DrzPF(plik.getAbsolutePath(), plik.getName(), this);
                this.getChidren().add(dp);
            }
        }
    }

    @Override
    public int compareTo(DrzPF o) {
        return this.getType().compareTo(o.getType());
    }
}
