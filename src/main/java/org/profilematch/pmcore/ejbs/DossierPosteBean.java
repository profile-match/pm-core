package org.profilematch.pmcore.ejbs;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.profilematch.pmcore.entities.Avis;
import org.profilematch.pmcore.entities.Certification;
import org.profilematch.pmcore.entities.Dossier_poste;
import org.profilematch.pmcore.entities.Fonctionnelle;
import org.profilematch.pmcore.entities.Formation_Recruteur;
import org.profilematch.pmcore.entities.Langue;
import org.profilematch.pmcore.entities.Metier;
import org.profilematch.pmcore.entities.Recruteur;
import org.profilematch.pmcore.entities.RecruteurMDP;
import org.profilematch.pmcore.entities.Technique;
import org.profilematch.pmcore.entities.Utilisateur;

/**
 *
 * @author Steven Klinger && Pierre Leriche
 */
@Stateless
public class DossierPosteBean implements DossierPosteBeanLocal {

    @PersistenceContext(unitName = "IMP_PU")
    private EntityManager em;

    @Override
    public int createDossier(Dossier_poste dp) {
        Calendar c = new GregorianCalendar();
        Date date_publication = c.getTime();
        dp.setDate_publication(date_publication);
        em.persist(dp);

        return 0;

    }

    @Override
    public List<Dossier_poste> getIntituleAllDossier(int id) {

        Query query = em.createNamedQuery("Dossier.findByIntitule").setParameter("id_recruteur", id);

        return query.getResultList();

    }

    @Override
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
        Dossier_poste c = em.find(Dossier_poste.class, id);
        if (c != null) {
            em.remove(c);
        }
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

    @Override
    public Dossier_poste getDossier(Long id) {
        Query query = em.createNamedQuery("Dossier.getDossier").setParameter("poste_id", id);
        Dossier_poste p = (Dossier_poste) query.getSingleResult();
        return p;
    }

    @Override
    public List<Formation_Recruteur> completeFormation(String formation) {
        Query query = em.createNamedQuery("Formation.completeFormation").setParameter("nom_formation", formation + "%");
        return query.getResultList();
    }

    @Override
    public List<Certification> completeCertification(String certification) {
        Query query = em.createNamedQuery("certification.completeCertification").setParameter("nom_certification", certification + "%");
        return query.getResultList();
    }

    @Override
    public Recruteur getUser(Long id) {
        Recruteur c = em.find(Recruteur.class, id);
        if (c != null) {
            return c;
        } else {
            return new Recruteur(-1L, "", "", "");
        }
    }

    @Override
    public Object BanUser(Long id) {
        Recruteur c = em.find(Recruteur.class, id);
        if (c != null) {
            c.setBanned(true);
            return c;
        } else {
            return new Recruteur(-1L, "", "", "");
        }
    }

    @Override
    public Object UnbanUser(Long id) {
        Recruteur c = em.find(Recruteur.class, id);
        if (c != null) {
            c.setBanned(false);
            return c;
        } else {
            return new Recruteur(-1L, "", "", "");
        }
    }

    @Override
    public Object getAllUser() {
        return em.createNamedQuery("Recruteur.findAll").getResultList();
    }

    @Override
    public Recruteur registerUser(Recruteur r) {
        em.persist(r);
        return r;
    }

    @Override
    public Object getAllPostes() {
        return em.createNamedQuery("Dossier.findAll").getResultList();
    }

    @Override
    public Object getCandidatPostule(Long id) {
        return em.createNamedQuery("Dossier.findPostule").setParameter("poste_id", id).getResultList();
    }

    @Override
    public int createAvis(Avis avis) {
      em.persist(avis);
      return 0;

    }


    @Override
    public boolean updateRecruteur(Recruteur r) {
        Recruteur r2 = em.find(Recruteur.class, r.getId());
        if (r2 != null) {
            em.merge(r);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int updateMDPRecruteur(RecruteurMDP r) {

        Utilisateur utilisateur = em.find(Utilisateur.class, r.getEmail());
        if (utilisateur == null) {
            return -1;
        }

        if (!utilisateur.getMotdepasse().equals(r.getOldMDP())) {
            return -2;
        }

        utilisateur.setMotdepasse(r.getNewMDP());
        em.merge(utilisateur);
        return 0;

    }

}
