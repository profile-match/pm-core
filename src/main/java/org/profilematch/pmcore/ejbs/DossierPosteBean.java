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
import org.profilematch.pmcore.entities.Fonctionnelle;
import org.profilematch.pmcore.entities.Langue;
import org.profilematch.pmcore.entities.Metier;
import org.profilematch.pmcore.entities.Technique;

/**
 *
 * @author Steven Klinger && Pierre Leriche
 */
@Stateless
public class DossierPosteBean implements DossierPosteBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "Candidat_PU")
    private EntityManager em;

    @Override
    public int createDossier(Dossier_poste dp) {
        em.persist(dp);

        return 0;

    }

    @Override
    public List<Dossier_poste> getIntituleAllDossier(int id) {

        Query query = em.createNamedQuery("Dossier.findByIntitule").setParameter("id_recruteur", id);

        return query.getResultList();

    }
    
    public List<Dossier_poste> getAllDossier(int id) {
        Query query = em.createNamedQuery("Dossier.getAllDossier").setParameter("id_recruteur", id);
        return query.getResultList();
    }

    @Override
    public List<Dossier_poste> getElementAllDossier(int id) {

        Query createQuery = em.createNamedQuery("Dossier.findByElement").setParameter("id_recruteur", id);
        return createQuery.getResultList();
    }

    @Override
    public int deleteDossier(Long id) {
        em.createNamedQuery("Dossier.deleteDossier").setParameter("poste_id", id).executeUpdate();

        return 0;
    }

    @Override
    public int updateDossier(Dossier_poste dp) {
        Dossier_poste d = em.find(Dossier_poste.class, dp.getId());
        if (d != null) {
            em.merge(dp);
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public List<Metier> completeMetier(String metier) {
        Query query = em.createNamedQuery("Metier.completeMetier").setParameter("nom_metier", metier + "%");
        return query.getResultList();
    }

    @Override
    public List<Technique> completeTechnique(String technique) {
        Query query = em.createNamedQuery("Technique.completeTechnique").setParameter("nom_technique", technique + "%");
        return query.getResultList();
    }

    @Override
    public List<Fonctionnelle> completeFonctionnelle(String fonc) {
        Query query = em.createNamedQuery("Fonctionnelle.completeFonctionnelle").setParameter("nom_fonctionnelle", fonc + "%");
        return query.getResultList();
    }

    @Override
    public List<Langue> completeLinguistique(String langue) {
        Query query = em.createNamedQuery("Langue.completeLangue").setParameter("nom_langue", langue + "%");
        return query.getResultList();
    }

}
