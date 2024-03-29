/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.arti01.encja;

import abst.AbstEncja;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
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
import javax.validation.constraints.Size;

/**
 *
 * @author arti01
 */
@Entity
@Table(name = "kredyt")
@NamedQueries({
    @NamedQuery(name = "Kredyt.findAll", query = "SELECT d FROM Kredyt d"),
    @NamedQuery(name = "Kredyt.findById", query = "SELECT d FROM Kredyt d WHERE d.id = :id"),
    @NamedQuery(name = "Kredyt.findByNazwa", query = "SELECT d FROM Kredyt d WHERE d.nazwa = :nazwa")})
public class Kredyt extends AbstEncja implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQKredyt")
    @SequenceGenerator(name = "SEQKredyt", sequenceName = "SEQKredyt")
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(nullable = false, length = 256, unique = true)
    private String nazwa;

    @Temporal(TemporalType.DATE)
    private Date dataPierwSplaty;

    private int liczbaRat;
    private BigDecimal kwota;
    private boolean ratyRowne;

    @OneToMany(mappedBy = "kredyt", fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Wynik> wynikList;
    
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

    @Override
    public String getNazwa() {
        return nazwa;
    }

    @Override
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
    
    public Date getDataPierwSplaty() {
        return dataPierwSplaty;
    }

    public void setDataPierwSplaty(Date dataPierwSplaty) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataPierwSplaty);
        //rata zawsze w ostatni dzień miesiąca
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        this.dataPierwSplaty = cal.getTime();
    }

    public int getLiczbaRat() {
        return liczbaRat;
    }

    public void setLiczbaRat(int liczbaRat) {
        this.liczbaRat = liczbaRat;
    }

    public BigDecimal getKwota() {
        return kwota;
    }

    public void setKwota(BigDecimal kwota) {
        this.kwota = kwota;
    }

    public boolean isRatyRowne() {
        return ratyRowne;
    }

    public void setRatyRowne(boolean ratyRowne) {
        this.ratyRowne = ratyRowne;
    }

    public List<Wynik> getWynikList() {
        return wynikList;
    }

    public void setWynikList(List<Wynik> wynikList) {
        this.wynikList = wynikList;
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
        final Kredyt other = (Kredyt) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Kredyt{" + "id=" + id + ", nazwa=" + nazwa + '}';
    }
}
