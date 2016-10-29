/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ek.encje;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author arti01
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "EkConfig.findAll", query = "SELECT c FROM EkConfig c"),
    @NamedQuery(name = "EkConfig.findById", query = "SELECT c FROM EkConfig c WHERE c.id = :id"),
    @NamedQuery(name = "EkConfig.findByNazwa", query = "SELECT c FROM EkConfig c WHERE c.nazwa = :nazwa"),
    @NamedQuery(name = "EkConfig.findByOpis", query = "SELECT c FROM EkConfig c WHERE c.opis = :opis"),
    @NamedQuery(name = "EkConfig.findByWartosc", query = "SELECT c FROM EkConfig c WHERE c.wartosc = :wartosc")})
public class EkConfig extends ek.abstr.AbstEncja implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nazwa", unique = false)
    private String nazwa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "opis")
    private String opis;
    @Size(max = 255)
    @Column(name = "wartosc")
    private String wartosc;

    public EkConfig() {
    }

    public EkConfig(Long id) {
        this.id = id;
    }

    public EkConfig(Long id, String nazwa, String opis) {
        this.id = id;
        this.nazwa = nazwa;
        this.opis = opis;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getNazwa() {
        return nazwa;
    }

    @Override
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getWartosc() {
        return wartosc;
    }

    public void setWartosc(String wartosc) {
        this.wartosc = wartosc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EkConfig)) {
            return false;
        }
        EkConfig other = (EkConfig) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.eod.encje.Config[ id=" + id + " ]";
    }
    
}
