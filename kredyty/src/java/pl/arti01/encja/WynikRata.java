/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.arti01.encja;

import abst.AbstEncja;
import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "wynik_rata")
@NamedQueries({
    @NamedQuery(name = "WynikRata.findAll", query = "SELECT d FROM WynikRata d"),
    @NamedQuery(name = "WynikRata.findById", query = "SELECT d FROM WynikRata d WHERE d.id = :id")})
public class WynikRata extends AbstEncja implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQWynikRata")
    @SequenceGenerator(name = "SEQWynikRata", sequenceName = "SEQWynikRata")
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer id;
    
    private int rataNumer;
    
    @Temporal(TemporalType.DATE)
    private Date rataData;
    
    private BigDecimal rataKapitalowa;
    private BigDecimal rataOdsetkowa;
    private BigDecimal doSplaty;
    private Double oprocentowanie;
    
    @ManyToOne()
    private Wynik wynik;
    
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public int getRataNumer() {
        return rataNumer;
    }

    public void setRataNumer(int rataNumer) {
        this.rataNumer = rataNumer;
    }

    public Date getRataData() {
        return rataData;
    }

    public void setRataData(Date rataData) {
        this.rataData = rataData;
    }

    public BigDecimal getRataKapitalowa() {
        return rataKapitalowa;
    }

    public void setRataKapitalowa(BigDecimal rataKapitalowa) {
        this.rataKapitalowa = rataKapitalowa;
    }

    public BigDecimal getRataOdsetkowa() {
        return rataOdsetkowa;
    }

    public void setRataOdsetkowa(BigDecimal rataOdsetkowa) {
        this.rataOdsetkowa = rataOdsetkowa;
    }

    public BigDecimal getDoSplaty() {
        return doSplaty;
    }

    public void setDoSplaty(BigDecimal doSplaty) {
        this.doSplaty = doSplaty;
    }

    public Wynik getWynik() {
        return wynik;
    }

    public void setWynik(Wynik wynik) {
        this.wynik = wynik;
    }

    public Double getOprocentowanie() {
        return oprocentowanie;
    }

    public void setOprocentowanie(Double oprocentowanie) {
        this.oprocentowanie = oprocentowanie;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WynikRata other = (WynikRata) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public String toString() {
        return "WynikRata{" + "id=" + id +  '}';
    }
}
