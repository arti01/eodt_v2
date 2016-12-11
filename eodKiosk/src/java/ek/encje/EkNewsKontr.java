/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ek.encje;

import ek.abstr.AbstKontroler;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class EkNewsKontr extends AbstKontroler<EkNews> {

    public EkNewsKontr() {
        super(new EkNews());
    }

    @SuppressWarnings({"unchecked", "unchecked"})
    public List<EkNews> findEntities(int limit) {
        EntityManager em = getEntityManager();
        try {
            Query qp = em.createNamedQuery("EkNews.findPriorytet");
            if (qp.getResultList().size() > 0) {
                return (List<EkNews>) qp.getResultList();
            } else {
                Query q = em.createNamedQuery("EkNews.findOstatnie");
                return (List<EkNews>) q.setMaxResults(limit).getResultList();
            }
        } catch (NoResultException | ArrayIndexOutOfBoundsException ex) {
            //ex.printStackTrace();
            //logger.log(Level.SEVERE, "blad", ex);
            return null;
        } finally {
            em.close();
        }
    }

}
