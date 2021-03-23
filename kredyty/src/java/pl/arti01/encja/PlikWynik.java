/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.arti01.encja;

import abst.AbstPlik;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author arti01
 */
@Entity
@Table(name = "plik_wynik")
public class PlikWynik extends AbstPlik implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQDCPLIK")
    @SequenceGenerator(name = "SEQDCPLIK", sequenceName = "SEQDCPLIK")
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(nullable = false, length = 256)
    private String nazwa;
    @Lob
    private byte[] plik;
    
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Wynik wynik;
    

    public PlikWynik() {
    }

    public PlikWynik(Integer id) {
        this.id = id;
    }

    public PlikWynik(Integer id, String nazwa) {
        this.id = id;
        this.nazwa = nazwa;
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
    public String getNazwa() {
        return nazwa;
    }

    @Override
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @Override
    public byte[] getPlik() {
        return plik;
    }

    @Override
    public void setPlik(byte[] plik) {
        this.plik = plik;
    }

    public Wynik getWynik() {
        return wynik;
    }

    public void setWynik(Wynik wynik) {
        this.wynik = wynik;
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
        if (!(object instanceof PlikWynik)) {
            return false;
        }
        PlikWynik other = (PlikWynik) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.eod2.encje.DcPlik[ id=" + id + " ]";
    }
    
}
