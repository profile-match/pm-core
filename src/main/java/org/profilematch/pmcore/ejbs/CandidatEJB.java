package org.profilematch.pmcore.ejbs;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.profilematch.pmcore.entities.Candidat;
import org.profilematch.pmcore.entities.MessageSignalementCandidat;

/**
 *
 * @author antoine
 */
@Stateless
@LocalBean
public class CandidatEJB {

    @PersistenceContext(unitName = "Candidat_PU")
    EntityManager em;

    public void postMessageSignalementCandidat(MessageSignalementCandidat m) {
        em.persist(m);
    }

    public int registerUser(Candidat c) {

        em.persist(c);

        return 0;
    }

    public int updateUser(Candidat c) {
        Candidat c1 = em.find(Candidat.class, c.getId());
        if (c1 != null) {
            em.merge(c);
            return 0;
        } else {
            return -1;
        }
    }

    public Candidat getUser(Long id) {
        Candidat c = em.find(Candidat.class, id);
        if (c != null) {
            return c;
        } else {
            return new Candidat(-1L, "", "", "");
        }
    }

    public Candidat BanUser(Long id) {
        Candidat c = em.find(Candidat.class, id);
        if (c != null) {
            c.setBanned(true);
            return c;
        } else {
            return new Candidat(-1L, "", "", "");
        }
    }

    public Candidat UnbanUser(Long id) {
        Candidat c = em.find(Candidat.class, id);
        if (c != null) {
            c.setBanned(false);
            return c;
        } else {
            return new Candidat(-1L, "", "", "");
        }
    }

    public List<Candidat> getAllUser() {
        return em.createNamedQuery("Candidat.findAll").getResultList();
    }
}
