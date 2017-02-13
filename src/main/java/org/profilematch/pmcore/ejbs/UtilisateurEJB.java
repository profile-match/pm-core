package org.profilematch.pmcore.ejbs;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.profilematch.pmcore.entities.Utilisateur;

/**
 *
 * @author Lucas
 */
@Stateless
@LocalBean
public class UtilisateurEJB {

    @PersistenceContext(unitName = "IMP_PU")
    EntityManager em;

    public boolean inscrireUtilisateur(Utilisateur u) {
        em.persist(u);
        return true;
    }

    public boolean modifierUtilisateur(Utilisateur u) {
        Utilisateur nouveauU = em.find(Utilisateur.class, u.getEmail());
        if (nouveauU != null) {
            em.merge(u);
            return true;
        } else {
            return false;
        }
    }
    
    public Utilisateur getUtilisateur(String id){
        Utilisateur u = em.find(Utilisateur.class, id);
        if (u != null) {
            return u;
        } else {
            return new Utilisateur(-1, "", "", "");
        }
    }

    public Utilisateur getUtilisateurByEmail(String email){
        Utilisateur u;
        try{
            u = (Utilisateur) em.createQuery("Utilisateur.findByEmail")
                 .setParameter("email", email).getSingleResult();    
        }catch(Exception e){
            u = new Utilisateur(-1, "", "", "");
        }
        return u;
    }
    
    public List<Utilisateur> getUtilisateurs(){
        return em.createNamedQuery("Utilisateur.findAll").getResultList();
    }
}
