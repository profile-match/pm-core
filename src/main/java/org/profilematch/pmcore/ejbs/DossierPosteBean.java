/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.profilematch.pmcore.ejbs;

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
    //TO DO 
    @Override
    public Set<String> getIntituleAllDossier() {
        
        Query createQuery = em.createNamedQuery("IntitulefindAll");
       
        return  (Set<String>)createQuery.getResultList();
    
    }

    @Override
    public Set<String> getElementAllDossier() {
        Query createQuery = em.createQuery("SELECT intitule, resume, lieu_travail, type_contrat, date_publication from DOSSIER");
        return (Set<String>)createQuery.getResultList();
    }

    
    
    
    
}
