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


public class EKObceLinkiKontr extends AbstKontroler<EkObceLinki> {

    public EKObceLinkiKontr() {
        super(new EkObceLinki());
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<EkObceLinki> findEntities() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("EkObceLinki.findAll");
            return (List<EkObceLinki>) q.getResultList();
        } catch (NoResultException | ArrayIndexOutOfBoundsException ex) {
            //ex.printStackTrace();
            //logger.log(Level.SEVERE, "blad", ex);
            return null;
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("unchecked")
    public List<EkObceLinki> findEntitiesLogin() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("EkObceLinki.findPoZalogowaniu");
            return (List<EkObceLinki>) q.getResultList();
        } catch (NoResultException | ArrayIndexOutOfBoundsException ex) {
            //ex.printStackTrace();
            //logger.log(Level.SEVERE, "blad", ex);
            return null;
        } finally {
            em.close();
        }
    }
}