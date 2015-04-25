/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.arti01.encja;

import abst.AbstEncja;
import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.OneToOne;
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

    private String nazwa;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataWyliczenia;
    private int liczbaRat;
    private BigDecimal kwota;
    private String nazwaBanku;
    @Temporal(TemporalType.DATE)
    private Date dataPierwSplaty;
    private boolean ratyRowne;
    
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "wynik", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<WynikRata> wynikRataList;
    
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "wynik", fetch = FetchType.LAZY, orphanRemoval = true)
    private PlikWynik plik;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    private Kredyt kredyt;

    public Wynik() {
    }

    public Wynik(Kredyt kredyt) {
        this.dataWyliczenia = new Date();
        this.kwota = kredyt.getKwota();
        this.nazwaBanku = kredyt.getBank().getNazwa();
        this.liczbaRat=kredyt.getLiczbaRat();
        this.wynikRataList=new ArrayList<>();
        this.kredyt=kredyt;
        this.dataPierwSplaty=kredyt.getDataPierwSplaty();
        this.ratyRowne=kredyt.isRatyRowne();
        this.nazwa=kredyt.getNazwa();
    }
    
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public void setNazwa(String nazwa) {
        this.nazwa=nazwa;
    }

    @Override
    public String getNazwa() {
        return nazwa;
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

    public BigDecimal getKwota() {
        return kwota;
    }

    public void setKwota(BigDecimal kwota) {
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

    public Date getDataPierwSplaty() {
        return dataPierwSplaty;
    }

    public void setDataPierwSplaty(Date dataPierwSplaty) {
        this.dataPierwSplaty = dataPierwSplaty;
    }

    public boolean isRatyRowne() {
        return ratyRowne;
    }

    public void setRatyRowne(boolean ratyRowne) {
        this.ratyRowne = ratyRowne;
    }

    public PlikWynik getPlik() {
        return plik;
    }

    public void setPlik(PlikWynik plik) {
        this.plik = plik;
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
