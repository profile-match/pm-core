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

    /**
     * Persists a the given Utilisateur
     *
     * @param u
     * @return the persisted Utilisateur
     */
    public boolean inscrireUtilisateur(Utilisateur u) {
        em.persist(u);
        return true;
    }

    /**
     * Updated the given Utilisateur
     *
     * @param u
     * @return the updated Utilisateur
     */
    public boolean modifierUtilisateur(Utilisateur u) {
        Utilisateur nouveauU = em.find(Utilisateur.class, u.getEmail());
        if (nouveauU != null) {
            em.merge(u);
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param id
     * @return the Utilisateur corresponding to the given id
     */
    public Utilisateur getUtilisateur(String id){
        Utilisateur u = em.find(Utilisateur.class, id);
        if (u != null) {
            return u;
        } else {
            return new Utilisateur((long)-1, "", "", "");
        }
    }

    /**
     *
     * @param email
     * @return the Utilisateur corresponding to the given email
     */
    public Utilisateur getUtilisateurByEmail(String email){
        Utilisateur u;
        
          u = em.find(Utilisateur.class, email);
      //  u = (Utilisateur) em.createQuery("Utilisateur.findByEmail")
       //      .setParameter("email", email).getSingleResult();    
        if (u != null) {
             return u;
         } else {
             return new Utilisateur((long)-1, "", "", "");
         }
       
    }

    /**
     *
     * @return all the Utilisateurs
     */
    public List<Utilisateur> getUtilisateurs(){
        return em.createNamedQuery("Utilisateur.findAll").getResultList();
    }
    
    public Utilisateur connexion(String email, String hache){
        Utilisateur user = getUtilisateurByEmail(email);
        if(user.getId() != -1){
            if(!user.getMotdepasse().equals(hache)){
                user = new Utilisateur((long)-1, "", "", "");
            }
        }
        return user; 
    }
}
