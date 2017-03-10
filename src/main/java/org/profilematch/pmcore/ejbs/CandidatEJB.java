package org.profilematch.pmcore.ejbs;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.profilematch.pmcore.entities.Candidat;
import org.profilematch.pmcore.entities.Competence;
import org.profilematch.pmcore.entities.MessageSignalementCandidat;

/**
 *
 * @author antoine
 */
@Stateless
@LocalBean
public class CandidatEJB {

    @PersistenceContext(unitName = "IMP_PU")
    EntityManager em;

    /**
     * Persists a new MessageSignalementCandidat
     *
     * @param m
     */
    public void postMessageSignalementCandidat(MessageSignalementCandidat m) {
        em.persist(m);
    }

    /**
     *
     * @param s
     * @return a list of all the competences that starts by the given parameter
     */
    public List<Competence> getCompetencesStartBy(String s) {
        return em.createNamedQuery("competence.startsby").setParameter("debut_comp", s + "%").getResultList();
    }

    /**
     * Persists a new Candidat
     *
     * @param c
     * @return the persisted Candidat
     */
    public Candidat registerUser(Candidat c) {
        em.persist(c);
        return c;
    }

    /**
     * Removes the candidat corresponding to the given id
     *
     * @param id
     */
    public void deleteCandidat(Long id){
        Candidat c = em.find(Candidat.class, id);
        if (c != null) {
            em.remove(c);
        }
    }

    /**
     * Updates the given User
     *
     * @param c
     * @return the updated candidat
     */
    public Candidat updateUser(Candidat c) {
        Candidat c1 = em.find(Candidat.class, c.getId());
        if (c1 != null) {
            
            em.merge(c);
            
            return c;
            
        } else {
            return c;
        }
    }

    /**
     *
     * @param id
     * @return the user corresponding to the given id
     */
    public Candidat getUser(Long id) {
        Candidat c = em.find(Candidat.class, id);
        if (c != null) {
            return c;
        } else {
            return new Candidat(-1L, "", "", "");
        }
    }

    /**
     *
     * @return the total number of males
     */
    public String getNbMale() {
        return em.createNamedQuery("Candidat.countMale").getSingleResult().toString();
    }

    /**
     *
     * @return the total number of females
     */
    public String getNbFemelle() {
        return em.createNamedQuery("Candidat.countFemelle").getSingleResult().toString();
    }

    /**
     *
     * Ban the candidat corresponding to the given id
     *
     * @param id
     * @return the banned candidat
     */
    public Candidat BanUser(Long id) {
        Candidat c = em.find(Candidat.class, id);
        if (c != null) {
            c.setBanned(true);
            return c;
        } else {
            return new Candidat(-1L, "", "", "");
        }
    }

    /**
     * Unban the candidat corresponding to the given id
     *
     * @param id
     * @return the unbanned candidat
     */
    public Candidat UnbanUser(Long id) {
        Candidat c = em.find(Candidat.class, id);
        if (c != null) {
            c.setBanned(false);
            return c;
        } else {
            return new Candidat(-1L, "", "", "");
        }
    }

    /**
     *
     * Suspend the candidat corresponding to the given id
     *
     * @param id
     * @return the suspended candidat
     */
    public Candidat SuspendUser(Long id) {
        Candidat c = em.find(Candidat.class, id);
        if (c != null) {
            c.setSuspended(true);
            return c;
        } else {
            return new Candidat(-1L, "", "", "");
        }
    }

    /**
     *
     * Unsuspend the candidat corresponding to the given id
     *
     * @param id
     * @return the unsuspended id
     */
    public Candidat UnSuspendUser(Long id) {
        Candidat c = em.find(Candidat.class, id);
        if (c != null) {
            c.setSuspended(false);
            return c;
        } else {
            return new Candidat(-1L, "", "", "");
        }
    }

    /**
     *
     * @return all the candidats
     */
    public List<Candidat> getAllUser() {
        return em.createNamedQuery("Candidat.findAll").getResultList();
    }
}
