/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.eod2.encje;

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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import pl.eod.abstr.AbstEncja;

/**
 *
 * @author arti01
 */
@Entity
@Table(name = "dc_symb_mer_1")
@NamedQueries({
    @NamedQuery(name = "DcSymbMer1.findAll", query = "SELECT d FROM DcSymbMer1 d"),
    @NamedQuery(name = "DcSymbMer1.findById", query = "SELECT d FROM DcSymbMer1 d WHERE d.id = :id"),
    @NamedQuery(name = "DcSymbMer1.findByNazwa", query = "SELECT d FROM DcSymbMer1 d WHERE d.nazwa = :nazwa"),
    @NamedQuery(name = "DcSymbMer1.findByOpis", query = "SELECT d FROM DcSymbMer1 d WHERE d.opis = :opis")})
public class DcSymbMer1 extends AbstEncja implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQDCSYMBMER1")
    @SequenceGenerator(name = "SEQDCSYMBMER1", sequenceName = "SEQDCSYMBMER1")
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(nullable = false, length = 256, unique = true)
    private String nazwa;
    @Size(max = 10485760)
    @Lob
    private String opis;
    private int czas;
    @OneToMany(mappedBy = "symbMer1Id", fetch = FetchType.LAZY)
    private List<DcRodzaj> dcRodzajList;

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

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getCzas() {
        return czas;
    }

    public void setCzas(int czas) {
        this.czas = czas;
    }

    public List<DcRodzaj> getDcRodzajList() {
        return dcRodzajList;
    }

    public void setDcRodzajList(List<DcRodzaj> dcRodzajList) {
        this.dcRodzajList = dcRodzajList;
    }

    @Override
    public String toString() {
        return "DcSymbMer1{" + "id=" + id + ", nazwa=" + nazwa + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.id);
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
        final DcSymbMer1 other = (DcSymbMer1) obj;
        return Objects.equals(this.id, other.id);
    }
    
}
