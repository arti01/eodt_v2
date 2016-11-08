/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ek.encje;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import pl.eod.encje.Uzytkownik;

/**
 *
 * @author arti01
 */
@Entity
@Table(name = "user_profil")
@NamedQueries({
    @NamedQuery(name = "UserProfil.findAll", query = "SELECT u FROM UserProfil u"),
    @NamedQuery(name = "UserProfil.findById", query = "SELECT u FROM UserProfil u WHERE u.id = :id"),
    @NamedQuery(name = "UserProfil.findByEcpId", query = "SELECT u FROM UserProfil u WHERE u.ecpId = :ecpId"),
    @NamedQuery(name = "UserProfil.findByCardno", query = "SELECT u FROM UserProfil u WHERE u.cardno = :cardno"),
    @NamedQuery(name = "UserProfil.findByEmail", query = "SELECT u FROM UserProfil u WHERE u.email = :email"),
    @NamedQuery(name = "UserProfil.findByEodId", query = "SELECT u FROM UserProfil u WHERE u.eodId = :eodId"),
    @NamedQuery(name = "UserProfil.findByFullname", query = "SELECT u FROM UserProfil u WHERE u.fullname = :fullname"),
    @NamedQuery(name = "UserProfil.findByPin", query = "SELECT u FROM UserProfil u WHERE u.pin = :pin")})
public class UserProfil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "ecp_id")
    private BigInteger ecpId;
    @Size(max = 30)
    @Column(name = "cardno")
    private String cardno;
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 150)
    @Column(name = "email")
    private String email;
    @Column(name = "eod_id")
    private BigInteger eodId;
    @Size(max = 255)
    @Column(name = "fullname")
    private String fullname;
    @Column(name = "pin")
    private Integer pin;
    @ManyToMany()
    @JoinTable(name = "view_user_roles",
            joinColumns = @JoinColumn(name = "cardno", referencedColumnName = "cardno"),
            inverseJoinColumns = @JoinColumn(name = "role_name", referencedColumnName = "role_name"))
    List<Roles> roleList;

    public UserProfil() {
    }

    public UserProfil(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigInteger getEcpId() {
        return ecpId;
    }

    public void setEcpId(BigInteger ecpId) {
        this.ecpId = ecpId;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigInteger getEodId() {
        return eodId;
    }

    public void setEodId(BigInteger eodId) {
        this.eodId = eodId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    public List<Roles> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Roles> roleList) {
        this.roleList = roleList;
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
        if (!(object instanceof UserProfil)) {
            return false;
        }
        UserProfil other = (UserProfil) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ek.encje.UserProfil[ id=" + id + " ]";
    }

}