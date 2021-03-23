/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.arti01.encja;

import abst.AbstKontroler;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import sun.jdbc.odbc.OdbcDef;

public class OkresOdsKontr extends AbstKontroler<OkresOds> {

    static final Logger logger = Logger.getAnonymousLogger();

    public OkresOdsKontr() {
        super(new OkresOds());
    }

    @Override
    public Map<String, String> create(OkresOds obiekt) {
        Map<String, String> bledy = new HashMap<>();

        //walidacja
        if (obiekt.getDataDo().compareTo(obiekt.getDataOd()) <= 0) {
            bledy.put("dataDoD", "data DO młodsza/równa niż data OD");
        }
        if (this.findWalidDataOd(obiekt) != 0) {
            bledy.put("dataOdD", "data od pokrywa inny zakres");
        }
        if (this.findWalidDataDo(obiekt) != 0) {
            bledy.put("dataDoD", "data do pokrywa inny zakres");
        }
        if (!bledy.isEmpty()) {
            return bledy;
        }

        EntityManager em = null;
        if (obiekt.getNazwa() != null) {
            if (findEntities(obiekt.getNazwa()) != null) {
                bledy.put("nazwaD", "nazwa już istnieje");
            }
        }
        //if(!bledy.isEmpty()) return bledy;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(obiekt);
            em.getTransaction().commit();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "blad", ex);
            obiekt.setId(null);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return bledy;
    }

    @Override
     public Map<String, String> edit(OkresOds obiekt) {
        OkresOds oldObiekt = findObiekt(obiekt);
        Map<String, String> bledy = new HashMap<>();
        
        //walidacja
        if (obiekt.getDataDo().compareTo(obiekt.getDataOd()) <= 0) {
            bledy.put("dataDoD", "data DO młodsza/równa niż data OD");
        }
        if (this.findWalidDataOd(obiekt) != 0) {
            bledy.put("dataOdD", "data od pokrywa inny zakres");
        }
        if (this.findWalidDataDo(obiekt) != 0) {
            bledy.put("dataDoD", "data do pokrywa inny zakres");
        }
        if (!bledy.isEmpty()) {
            return bledy;
        }
        
        EntityManager em = null;
        if (obiekt.getNazwa() != null) {
            if ((findEntities(obiekt.getNazwa()) != null) && (!obiekt.getNazwa().equals(oldObiekt.getNazwa()))) {
                bledy.put("nazwaD", "nazwa już istnieje");
            }
        }
        if (!bledy.isEmpty()) {
            return bledy;
        }
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(obiekt);
            em.getTransaction().commit();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "blad", ex);
            obiekt.setId(null);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return bledy;
    }

    
    public int findWalidDataOd(OkresOds oo) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("OkresOds.findWaliddataOd");
            q.setParameter("dataOd", oo.getDataOd());
            q.setParameter("bank", oo.getBank());
            return q.getResultList().size();
        } catch (NoResultException | ArrayIndexOutOfBoundsException ex) {
            //ex.printStackTrace();
            logger.log(Level.SEVERE, "blad", ex);
            return 0;
        } finally {
            em.close();
        }
    }
    
    public int findWalidDataDo(OkresOds oo) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("OkresOds.findWaliddataDo");
            q.setParameter("dataDo", oo.getDataOd());
            q.setParameter("bank", oo.getBank());
            return q.getResultList().size();
        } catch (NoResultException | ArrayIndexOutOfBoundsException ex) {
            //ex.printStackTrace();
            logger.log(Level.SEVERE, "blad", ex);
            return 0;
        } finally {
            em.close();
        }
    }
}
