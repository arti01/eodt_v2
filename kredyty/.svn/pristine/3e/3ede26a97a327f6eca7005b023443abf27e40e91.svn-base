/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.arti01.encja;

import abst.AbstEncja;
import java.io.Serializable;
import java.util.Currency;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author arti01
 */
@Entity
@Table(name = "okres_ods")
@NamedQueries({
    @NamedQuery(name = "OkresOds.findAll", query = "SELECT d FROM OkresOds d"),
    @NamedQuery(name = "OkresOds.findById", query = "SELECT d FROM OkresOds d WHERE d.id = :id")})
public class OkresOds extends AbstEncja implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQOkresOds")
    @SequenceGenerator(name = "SEQOkresOds", sequenceName = "SEQOkresOds")
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer id;

    @Temporal(TemporalType.DATE)
    private Date dataOd;
    @Temporal(TemporalType.DATE)
    private Date dataDo;
    
    private Double oprocentowanie;
    
    @ManyToOne()
     private Bank bank;
    
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataOd() {
        return dataOd;
    }

    public void setDataOd(Date dataOd) {
        this.dataOd = dataOd;
    }

    public Date getDataDo() {
        return dataDo;
    }

    public void setDataDo(Date dataDo) {
        this.dataDo = dataDo;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Double getOprocentowanie() {
        return oprocentowanie;
    }

    public void setOprocentowanie(Double oprocentowanie) {
        this.oprocentowanie = oprocentowanie;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OkresOds other = (OkresOds) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "OkresOds{" + "id=" + id + ", dataOd=" + dataOd + ", dataDo=" + dataDo + ", oprocentowanie=" + oprocentowanie + ", bank=" + bank + '}';
    }

    
}
