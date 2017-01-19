package org.profilematch.pmcore.ejbs;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;
import java.util.Set;
import javax.ejb.Local;
import org.profilematch.pmcore.entities.Dossier_poste;
import org.profilematch.pmcore.entities.Fonctionnelle;
import org.profilematch.pmcore.entities.Langue;
import org.profilematch.pmcore.entities.Metier;
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
    public List<Fonctionnelle> completeFonctionnelle(String metier);
    public List<Langue> completeLinguistique(String metier);
}
