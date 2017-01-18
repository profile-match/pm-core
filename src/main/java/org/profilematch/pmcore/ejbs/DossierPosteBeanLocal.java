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

/**
 *
 * @author Steven Klinger && Pierre Leriche
 */
@Local
public interface DossierPosteBeanLocal {
    
    public int createDossier(Dossier_poste dp);
     public List<Dossier_poste> getIntituleAllDossier();
    public List<Dossier_poste> getElementAllDossier();
}
