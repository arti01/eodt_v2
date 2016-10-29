/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ek.encje;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author arti01
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "EkObceLinki.findAll", query = "SELECT d FROM EkObceLinki d"),
    @NamedQuery(name = "EkObceLinki.findById", query = "SELECT d FROM EkObceLinki d WHERE d.id = :id"),
    @NamedQuery(name = "EkObceLinki.findByNazwa", query = "SELECT d FROM EkObceLinki d WHERE d.nazwa = :nazwa")})
public class EkObceLinki extends ek.abstr.AbstEncja implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQEkObceLinki")
    @SequenceGenerator(name = "SEQEkObceLinki", sequenceName = "SEQEkObceLinki")
    private Long id;
    @Size(min = 1, max = 256)
    @Column(name = "nazwa", nullable = false, length = 256, unique = true)
    private String nazwa;
    @Size(max = 10485760)
    @Lob
    private String opis;

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

    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final EkObceLinki other = (EkObceLinki) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
