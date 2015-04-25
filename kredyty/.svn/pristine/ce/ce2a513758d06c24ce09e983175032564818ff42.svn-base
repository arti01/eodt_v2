/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package encja;

import abst.AbstEncja;
import java.io.Serializable;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author arti01
 */
@Entity
@Table(name = "okres_ods")
@NamedQueries({
    @NamedQuery(name = "Bank.findAll", query = "SELECT d FROM Bank d"),
    @NamedQuery(name = "Bank.findById", query = "SELECT d FROM Bank d WHERE d.id = :id"),
    @NamedQuery(name = "Bank.findByNazwa", query = "SELECT d FROM Bank d WHERE d.nazwa = :nazwa")})
public class OkresOds extends AbstEncja implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQBank")
    @SequenceGenerator(name = "SEQBank", sequenceName = "SEQBank")
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer id;

    @Temporal(TemporalType.DATE)
    private Date dataOd;
    @Temporal(TemporalType.DATE)
    private Date dataDo;
    
    private Currency oprocentowanie;
    
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

    public Currency getOprocentowanie() {
        return oprocentowanie;
    }

    public void setOprocentowanie(Currency oprocentowanie) {
        this.oprocentowanie = oprocentowanie;
    }

    
}
