/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.arti01.encja;

import abst.AbstEncja;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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

/**
 *
 * @author arti01
 */
@Entity
@Table(name = "wynik")
@NamedQueries({
    @NamedQuery(name = "Wynik.findAll", query = "SELECT d FROM Wynik d"),
    @NamedQuery(name = "Wynik.findById", query = "SELECT d FROM Wynik d WHERE d.id = :id")})
public class Wynik extends AbstEncja implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQWynik")
    @SequenceGenerator(name = "SEQWynik", sequenceName = "SEQWynik")
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataWyliczenia;
    private int liczbaRat;
    private int kwota;
    private String nazwaBanku;
    
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "wynik", fetch = FetchType.LAZY)
    private List<WynikRata> wynikRataList;

    @ManyToOne()
    private Kredyt kredyt;

    public Wynik() {
    }

    public Wynik(Kredyt kredyt) {
        this.dataWyliczenia = new Date();
        this.kwota = kredyt.getKwota();
        this.nazwaBanku = kredyt.getBank().getNazwa();
        this.liczbaRat=kredyt.getLiczbaRat();
        this.wynikRataList=new ArrayList<>();
    }
    
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataWyliczenia() {
        return dataWyliczenia;
    }

    public void setDataWyliczenia(Date dataWyliczenia) {
        this.dataWyliczenia = dataWyliczenia;
    }

    public List<WynikRata> getWynikRataList() {
        return wynikRataList;
    }

    public void setWynikRataList(List<WynikRata> wynikRataList) {
        this.wynikRataList = wynikRataList;
    }

    public Kredyt getKredyt() {
        return kredyt;
    }

    public void setKredyt(Kredyt kredyt) {
        this.kredyt = kredyt;
    }

    public int getKwota() {
        return kwota;
    }

    public void setKwota(int kwota) {
        this.kwota = kwota;
    }

    public String getNazwaBanku() {
        return nazwaBanku;
    }

    public void setNazwaBanku(String nazwaBanku) {
        this.nazwaBanku = nazwaBanku;
    }

    public int getLiczbaRat() {
        return liczbaRat;
    }

    public void setLiczbaRat(int liczbaRat) {
        this.liczbaRat = liczbaRat;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.id);
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
        final Wynik other = (Wynik) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Wynik{" + "id=" + id + '}';
    }
}
