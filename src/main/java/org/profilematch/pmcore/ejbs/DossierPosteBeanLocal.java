package org.profilematch.pmcore.ejbs;

import java.util.List;
import javax.ejb.Local;
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

/**
 *
 * @author Steven Klinger && Pierre Leriche
 */
@Local
public interface DossierPosteBeanLocal {

    public int createDossier(Dossier_poste dp);

    public List<Dossier_poste> getIntituleAllDossier(int id);

    public List<Dossier_poste> getElementAllDossier(int id);

    public int deleteDossier(Long id);

    public int updateDossier(Dossier_poste dp);

    public List<Metier> completeMetier(String metier);

    public List<Technique> completeTechnique(String technique);

    public List<Fonctionnelle> completeFonctionnelle(String fonctionnelle);

    public List<Langue> completeLinguistique(String langue);
    public List<Formation_Recruteur> completeFormation(String formation);
    public List<Certification> completeCertification(String certification);
    
    public List<Dossier_poste> getAllDossier(int id);
    
    public Dossier_poste getDossier(Long id);

    public Recruteur getUser(Long id);

    public Object BanUser(Long id);

    public Object UnbanUser(Long id);

    public Object getAllUser();
    
    public Recruteur registerUser(Recruteur r);

    public Object getAllPostes();
    
    public Object getCandidatPostule(Long id);
    
    public int createAvis(Avis avis);


    public boolean updateRecruteur(Recruteur r);


    public int updateMDPRecruteur(RecruteurMDP r);

}
