/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ek.encje;

import ek.abstr.AbstKontroler;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import pl.eod.encje.Uzytkownik;
import pl.eod.encje.UzytkownikJpaController;

public class UserProfilKontr extends AbstKontroler<UserProfil> implements Serializable {

    private static final long serialVersionUID = 1L;
    static final Logger LOGGER = Logger.getAnonymousLogger();

    public UserProfilKontr() {
        super(new UserProfil());
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

    @Override
    public UserProfil findEntities(String nazwa) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("UserProfil.findByFullname");
            q.setParameter("fullname", nazwa);
            @SuppressWarnings("unchecked")
            UserProfil u = (UserProfil) q.getResultList().get(0);
            return u;
        } catch (NoResultException | ArrayIndexOutOfBoundsException ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public UserProfil save(UserProfil up) {
        try {
            EntityManager em = getEntityManager();
            em.getTransaction().begin();
            up = em.merge(up);
            em.getTransaction().commit();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "blad", ex);
        } finally {
            if (getEntityManager() != null) {
                getEntityManager().close();
            }
        }
        return up;
    }

    @Override
    public void destroy(UserProfil up) {
        try {
            EntityManager em = getEntityManager();
            em.getTransaction().begin();
            Query query = em.createNativeQuery("delete from user_profil where id = "+up.getId());
            query.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "blad", ex);
        } finally {
            if (getEntityManager() != null) {
                getEntityManager().close();
            }
        }
    }

    public Uzytkownik findUzytkownik(UserProfil up) {
        UzytkownikJpaController userC = new UzytkownikJpaController();
        if (up.getEodId() != null) {
            return userC.findUzytkownik(up.getEodId().longValue());
        } else {
            return null;
        }
    }

    public List<UserProfil> findAll() {
        List<UserProfil> wynik = new ArrayList<>();
        for (UserProfil up : getFindEntities()) {
            up.setUzytkownik(findUzytkownik(up));
            wynik.add(up);
        }
        return wynik;
    }
}
