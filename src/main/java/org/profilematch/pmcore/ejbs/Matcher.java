package org.profilematch.pmcore.ejbs;

import org.profilematch.pmcore.entities.*;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.*;

/**
 * Created by antoine on 3/3/17.
 */

@Stateless
@LocalBean
public class Matcher {

    private final int OBLIGATOIRE = 2;
    private final int PLUS = 1;
    private final int NA = 0;

    @EJB
    DossierPosteBeanLocal posteEJB;

    @EJB
    CandidatEJB candidatEJB;

    /**
     *  Returns an array containing 3 hashmap <String, Boolean> <br/>
     *  [0] : Competences   <br/>
     *  [1] : Formations    <br/>
     *  [2] : Certifications<br/>
     *
     * @param idPost
     * @param idCandidat
     * @return
     */
    public HashMap<String, Boolean>[] matchCandidat(Long idPost, Long idCandidat){

        Dossier_poste d = posteEJB.getDossier(idPost);
        Candidat c = candidatEJB.getUser(idCandidat);

        return matchCandidat(d,c);
    }

    /**
     *  Returns an array containing 3 hashmap <String, Boolean> <br/>
     *  [0] : Competences   <br/>
     *  [1] : Formations    <br/>
     *  [2] : Certifications<br/>
     *
     * @param d
     * @param c
     * @return
     */
    public HashMap<String, Boolean>[] matchCandidat(Dossier_poste d, Candidat c){
        HashMap<String, Boolean> competencesH = new HashMap<>();
        HashMap<String, Boolean> formationsH = new HashMap<>();
        HashMap<String, Boolean> CertificationH = new HashMap<>();

        HashMap<String, Boolean>[] result = new HashMap[3];
        result[0] = competencesH;
        result[1] = formationsH;
        result[2] = CertificationH;

        for(Metier m : d.getMetiers()){
            competencesH.put(m.getIntitule(), false);
            for(Competence comp : c.getCompetence()){
                if(comp.getType() == 0 && m.getIntitule().toLowerCase().equals(comp.getCompetence().toLowerCase()))
                    competencesH.put(m.getIntitule(), true);
            }
        }

        for(Fonctionnelle f : d.getFonctionnelles()){
            competencesH.put(f.getIntitule(), false);
            for(Competence comp : c.getCompetence()){
                if(comp.getType() == 1 && f.getIntitule().toLowerCase().equals(comp.getCompetence().toLowerCase()))
                    competencesH.put(f.getIntitule(), true);
            }
        }

        for(Technique t : d.getTechniques()){
            competencesH.put(t.getIntitule(), false);
            for(Competence comp : c.getCompetence()){
                if(comp.getType() == 2 && t.getIntitule().toLowerCase().equals(comp.getCompetence().toLowerCase()))
                    competencesH.put(t.getIntitule(), true);
            }
        }

        for(Langue l : d.getLangues()){
            competencesH.put(l.getIntitule(), false);
            for(Competence comp : c.getCompetence()){
                if(comp.getType() == 3 && l.getIntitule().toLowerCase().equals(comp.getCompetence().toLowerCase()))
                    competencesH.put(l.getIntitule(), true);
            }
        }

        for(Formation_Recruteur f : d.getFormations()){
            formationsH.put(f.getIntitule(), false);
            for(Formation form : c.getFormation()){
                if(f.getIntitule().toLowerCase().equals(form.getIntitule_de_formation().toLowerCase()))
                    formationsH.put(f.getIntitule(), true);
            }
        }

        for(Certification cert: d.getCertifications()){
            CertificationH.put(cert.getIntitule(), false);
            for(CertificationCandidat certC : c.getCertifications()){
                if(cert.getIntitule().equals(certC.getCertification()))
                    CertificationH.put(cert.getIntitule(), true);
            }
        }

        return result;
    }

    /**
     * Return a sorted list of the borneInfth to the borneSupth bests candidats
     *
     * @param dossierPoste
     * @param borneInf
     * @param borneSup
     * @return
     */
    public List<MatchedCandidat> match(Long dossierPoste, int borneInf, int borneSup) {

        return match(posteEJB.getDossier(dossierPoste), candidatEJB.getAllUser(),borneInf, borneSup);
    }

    /**
     * Return a sorted list of the borneInfth to the borneSupth bests candidats
     *
     * @param d
     * @param ListCandidats
     * @param borneInf
     * @param borneSup
     * @return
     */
    public List<MatchedCandidat> match(Dossier_poste d, List<Candidat> ListCandidats, int borneInf, int borneSup) {
        List<MatchedCandidat> result = new ArrayList<>();

        /*
        * 0 : metier
        * 1 : fonctionnelle
        * 2 : technique
        * 3 : linguistique
        */

        for (Candidat c : ListCandidats) {
            float plus = 0f, obligatoire = 0f;
            float diviseurObligatoire = 0f, diviseurPlus = 0f;


            for (Metier t : d.getMetiers()) {
                for (Competence comp : c.getCompetence()) {
                    if (comp.getCompetence().equals(t.getIntitule()) && comp.getType() == 0) {
                        if (t.getObligatoire() == OBLIGATOIRE)
                            obligatoire++;
                        else
                            plus++;
                    }
                }

                if(t.getObligatoire() == OBLIGATOIRE)
                    diviseurObligatoire ++;
                else
                    diviseurPlus++;

            }

            for (Fonctionnelle t : d.getFonctionnelles()) {
                for (Competence comp : c.getCompetence()) {
                    if (comp.getCompetence().equals(t.getIntitule()) && comp.getType() == 1) {
                        if (t.getObligatoire() == OBLIGATOIRE)
                            obligatoire++;
                        else
                            plus++;
                    }
                }
                if(t.getObligatoire() == OBLIGATOIRE)
                    diviseurObligatoire ++;
                else
                    diviseurPlus++;

            }

            for (Technique t : d.getTechniques()) {
                for (Competence comp : c.getCompetence()) {
                    if (comp.getCompetence().equals(t.getIntitule()) && comp.getType() == 2) {
                        if (t.getObligatoire() == OBLIGATOIRE)
                            obligatoire++;
                        else
                            plus++;
                    }
                }
                if(t.getObligatoire() == OBLIGATOIRE)
                    diviseurObligatoire ++;
                else
                    diviseurPlus++;
            }

            for (Langue t : d.getLangues()) {
                for (Competence comp : c.getCompetence()) {
                    if (comp.getCompetence().equals(t.getIntitule()) && comp.getType() == 3) {
                        if (t.getObligatoire() == OBLIGATOIRE)
                            obligatoire++;
                        else
                            plus++;
                    }
                }
                if(t.getObligatoire() == OBLIGATOIRE)
                    diviseurObligatoire ++;
                else
                    diviseurPlus++;
            }

            result.add(new MatchedCandidat(c,  (obligatoire/diviseurObligatoire), (plus/diviseurPlus)));

        }

        List<MatchedCandidat> listFinal = new LinkedList<>();

        Collections.sort(result);
        Collections.reverse(result);
        for(int i = borneSup; i >= borneInf; i--){
            listFinal.add(result.get(i));
        }

        Collections.reverse(listFinal);

        return listFinal;
    }
}
