/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ek.encje;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author arti01
 */
@Entity
@Table(name = "view_user_roles")
@NamedQueries({
    @NamedQuery(name = "ViewUserRoles.findAll", query = "SELECT v FROM ViewUserRoles v"),
    @NamedQuery(name = "ViewUserRoles.findByRoleName", query = "SELECT v FROM ViewUserRoles v WHERE v.roleName = :roleName"),
    @NamedQuery(name = "ViewUserRoles.findByCardno", query = "SELECT v FROM ViewUserRoles v WHERE v.cardno = :cardno")})
public class ViewUserRoles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Size(max = 20)
    @Column(name = "role_name")
    private String roleName;
    @Id
    @Size(max = 30)
    @Column(name = "cardno")
    private String cardno;

    public ViewUserRoles() {
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }
    
}
