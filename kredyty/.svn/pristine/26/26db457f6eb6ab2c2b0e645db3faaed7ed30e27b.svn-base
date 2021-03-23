/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.arti01.encja;

import abst.AbstEncja;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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
    private Date data;
    
    @OneToMany(mappedBy = "Wynik", fetch = FetchType.LAZY)
    private List<Obliczenie> ObliczenieList;

    @ManyToOne()
    private Kredyt kredyt;
    
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<Obliczenie> getObliczenieList() {
        return ObliczenieList;
    }

    public void setObliczenieList(List<Obliczenie> ObliczenieList) {
        this.ObliczenieList = ObliczenieList;
    }

    public Kredyt getKredyt() {
        return kredyt;
    }

    public void setKredyt(Kredyt kredyt) {
        this.kredyt = kredyt;
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
