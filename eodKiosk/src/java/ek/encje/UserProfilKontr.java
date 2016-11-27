/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ek.encje;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class UserProfilKontr implements Serializable {

    private static final long serialVersionUID = 1L;

    public UserProfilKontr() {
        if (this.emf == null) {
            this.emf = Persistence.createEntityManagerFactory("eodEkiosk");
        }
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public UserProfil findByCardno(String nazwa) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("UserProfil.findByCardno");
            q.setParameter("cardno", nazwa);
            @SuppressWarnings("unchecked")
            UserProfil u = (UserProfil) q.getResultList().get(0);
            return u;
        } catch (NoResultException | ArrayIndexOutOfBoundsException ex) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public String save(UserProfil up){
        getEntityManager().merge(up);
        return "zmiana wykonana";
    }
}
