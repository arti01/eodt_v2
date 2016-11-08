/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ek.encje;

import ek.abstr.AbstKontroler;
import ek.encje.exceptions.NonexistentEntityException;
import ek.encje.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author arti01
 */
public class EkLogKontr extends AbstKontroler<EkLog>  implements Serializable {

    private static final long serialVersionUID = 1L;

    public EkLogKontr() {
        super(new EkLog());
    }

    @SuppressWarnings("unchecked")
    public List<EkLog> findEntities(int limit) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("EkLog.findOstatnie");
            return (List<EkLog>) q.setMaxResults(limit).getResultList();
        } catch (NoResultException | ArrayIndexOutOfBoundsException ex) {
            //ex.printStackTrace();
            //logger.log(Level.SEVERE, "blad", ex);
            return null;
        } finally {
            em.close();
        }
    }
    
}