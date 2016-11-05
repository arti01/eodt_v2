/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ek.encje;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author arti01
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "EkLog.findAll", query = "SELECT c FROM EkLog c"),
    @NamedQuery(name = "EkLog.findById", query = "SELECT c FROM EkLog c WHERE c.id = :id"),
    @NamedQuery(name = "EkLog.findByNazwa", query = "SELECT c FROM EkLog c WHERE c.nazwa = :nazwa"),
    @NamedQuery(name = "EkLog.findByOpis", query = "SELECT c FROM EkLog c WHERE c.opis = :opis")})
public class EkLog extends ek.abstr.AbstEncja implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQEkLog")
    @SequenceGenerator(name = "SEQEkLog", sequenceName = "SEQEkLog")
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
    @Column(name = "cardno")
    private String opis;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataZdarzenia=new Date();
    public EkLog() {
    }

    public EkLog(Long id) {
        this.id = id;
    }

    public EkLog(Long id, String nazwa, String opis) {
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

    public Date getDataZdarzenia() {
        return dataZdarzenia;
    }

    public void setDataZdarzenia(Date dataZdarzenia) {
        this.dataZdarzenia = dataZdarzenia;
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
        if (!(object instanceof EkLog)) {
            return false;
        }
        EkLog other = (EkLog) object;
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
