/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.profilematch.pmcore.ejbs;

import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.profilematch.pmcore.entities.Dossier_poste;

/**
 *
 * @author Steven Klinger && Pierre Leriche
 */
@Stateless
public class DossierPosteBean implements DossierPosteBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext(unitName =  "GPU")
    private EntityManager em;
    
    
    
    @Override
    public int createDossier(Dossier_poste dp){
        em.persist(dp);
        
        return 0;
       
    }
     
    @Override
    public List<Dossier_poste> getIntituleAllDossier() {
        
        Query query = em.createNamedQuery("Dossier.findByIntitule");
       
        return query.getResultList();
    
    }

    @Override
    public List<Dossier_poste> getElementAllDossier() {
        
        Query createQuery = em.createNamedQuery("Dossier.findByElement");
        return createQuery.getResultList();
    }

    @Override
    public int deleteDossier(Long id) {
        em.createNamedQuery("Dossier.deleteDossier").setParameter("poste_id", id).executeUpdate();
       
       return 0;
    }

    
    
    
    
}
