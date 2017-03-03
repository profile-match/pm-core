package org.profilematch.pmcore.ejbs;

import org.profilematch.pmcore.entities.*;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.HashMap;

/**
 * Created by antoine on 3/3/17.
 */

@Stateless
@LocalBean
public class Matcher {

    @EJB
    DossierPosteBeanLocal posteEJB;

    @EJB
    CandidatEJB candidatEJB;

    public HashMap<Candidat, Float> match(Long dossierPoste) {
        HashMap<Candidat, Float> result = new HashMap<>();

        for (Candidat c : candidatEJB.getAllUser()) {
            result.put(c, 0f);
        }

        Dossier_poste d = posteEJB.getDossier(dossierPoste);
        /*
        * 0 : metier
        * 1 : fonctionnelle
        * 2 : technique
        * 3 : linguistique
        */
        for (Candidat c : result.keySet()) {
            float metier = 0, fonctionnelle = 0, technique = 0, langue = 0;
            float diviseur = 0f;

            System.out.println("Candidat : " + c.getNom());

            for (Competence comp : c.getCompetence()) {

                for (Metier t : d.getMetiers()) {
                    if (comp.getCompetence().equals(t.getIntitule()) && comp.getType() == 0) {
                        metier += 1;
                        System.out.println(t + " / " + metier);
                    }
                }

                for (Fonctionnelle t : d.getFonctionnelles()) {
                    if (comp.getCompetence().equals(t.getIntitule()) && comp.getType() == 1) {
                        fonctionnelle += 1;
                        System.out.println(t + " / " + fonctionnelle);
                    }
                }

                for (Technique t : d.getTechniques()) {
                    if (comp.getCompetence().equals(t.getIntitule()) && comp.getType() == 2) {
                        technique += 1;
                        System.out.println(t + " / " + technique);
                    }
                }

                for (Langue t : d.getLangues()) {
                    if (comp.getCompetence().equals(t.getIntitule()) && comp.getType() == 3) {
                        langue += 1;
                        System.out.println(t + " / " + langue);
                    }
                }
            }

            if (d.getMetiers().size() > 0) {
                metier /= d.getMetiers().size();
                for (Metier l : d.getMetiers())
                    System.out.println("Metier : " + l.getIntitule());
                diviseur++;
            }

            if (d.getFonctionnelles().size() > 0) {
                fonctionnelle /= d.getFonctionnelles().size();
                for (Fonctionnelle l : d.getFonctionnelles())
                    System.out.println("Fonctionelle " + l.getIntitule());
                diviseur++;
            }

            if (d.getTechniques().size() > 0) {
                technique /= d.getTechniques().size();
                for (Technique l : d.getTechniques())
                    System.out.println("Technique " + l.getIntitule());
                diviseur++;
            }

            if (d.getLangues().size() > 0) {
                langue /= d.getLangues().size();
                for (Langue l : d.getLangues())
                    System.out.println("Langue : " + l.getIntitule());
                diviseur++;
            }

            System.out.println(diviseur);
            result.put(c, (metier + fonctionnelle + technique + langue) / diviseur);
        }

        return result;
    }
}
