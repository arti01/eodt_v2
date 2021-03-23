/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.arti01.encja;

import abst.AbstEncja;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author arti01
 */
@Entity
@Table(name = "bank")
@NamedQueries({
    @NamedQuery(name = "Bank.findAll", query = "SELECT d FROM Bank d"),
    @NamedQuery(name = "Bank.findById", query = "SELECT d FROM Bank d WHERE d.id = :id"),
    @NamedQuery(name = "Bank.findByNazwa", query = "SELECT d FROM Bank d WHERE d.nazwa = :nazwa")})
public class Bank extends AbstEncja implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQBank")
    @SequenceGenerator(name = "SEQBank", sequenceName = "SEQBank")
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(nullable = false, length = 256, unique = true)
    private String nazwa;
    
    @OneToMany(mappedBy = "bank", fetch = FetchType.LAZY)
    private List<OkresOds> okredOdsList;

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

    public List<OkresOds> getOkredOdsList() {
        return okredOdsList;
    }

    public void setOkredOdsList(List<OkresOds> okredOdsList) {
        this.okredOdsList = okredOdsList;
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
        final Bank other = (Bank) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Bank{" + "id=" + id + ", nazwa=" + nazwa + '}';
    }


}
