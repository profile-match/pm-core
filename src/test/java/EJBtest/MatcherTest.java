package EJBtest;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import org.junit.Before;
import org.junit.Test;
import org.profilematch.pmcore.ejbs.MatchedCandidat;
import org.profilematch.pmcore.ejbs.Matcher;
import org.profilematch.pmcore.entities.*;

import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by antoine on 3/7/17.
 */
public class MatcherTest {

    private final int OBLIGATOIRE = 2;
    private final int PLUS = 1;
    private final int NA = 0;

    private List<Candidat> listeC;
    private Dossier_poste dossierNominal;
    private Dossier_poste dossierVide;

    Matcher M;

    @Before
    public void prepare(){
        this.M = new Matcher();

        Candidat c0 = new Candidat();
        Candidat c1 = new Candidat();
        Candidat c2 = new Candidat();

        c0.setNom("c0");
        c0.setId(0L);
        c1.setNom("c1");
        c1.setId(1L);
        c2.setNom("c2");
        c2.setId(3L);

        /*
        * 0 : metier
        * 1 : fonctionnelle
        * 2 : technique
        * 3 : linguistique
        */

        Competence comp1 = new Competence("Comp1", 2);
        Competence comp2 = new Competence("Comp2", 3);
        Competence comp3 = new Competence("Comp3", 1);
        Competence compF1 = new Competence("F1", 1);
        Competence compF2 = new Competence("F2", 1);
        Competence compM1 = new Competence("M1", 0);
        Competence compM2 = new Competence("M2", 0);
        Competence compT1 = new Competence("T1", 2);
        Competence compT2 = new Competence("T2", 2);
        Competence compL1 = new Competence("L1", 3);
        Competence compL2 = new Competence("L2", 3);

        List list1 = new LinkedList<Competence>();
        List list2 = new LinkedList<Competence>();

        CertificationCandidat cf1 = new CertificationCandidat();
        CertificationCandidat cf2 = new CertificationCandidat();
        cf1.setCertification("cf1");
        cf2.setCertification("cert2");

        List<CertificationCandidat> listCf1 = new LinkedList<>();
        List<CertificationCandidat> listCf2 = new LinkedList<>();
        listCf1.add(cf1);
        listCf1.add(cf2);

        list1.add(comp1);
        list1.add(comp2);
        list1.add(compF1);
        list1.add(compF2);
        list1.add(compT2);
        list1.add(compL1);
        list2.add(comp3);
        list2.add(compF1);
        list2.add(compF2);
        list2.add(compM1);
        list2.add(compM2);
        list2.add(compT1);
        list2.add(compT2);
        list2.add(compL1);
        list2.add(compL2);

        Formation formC1 = new Formation();
        formC1.setIntitule_de_formation("FORMC1");
        Formation formEQform1 = new Formation();
        formEQform1.setIntitule_de_formation("FORM1");
        Formation formEQform1Lower = new Formation();
        formEQform1Lower.setIntitule_de_formation("form1");

        List<Formation> listF = new LinkedList<>();
        List<Formation> listF2 = new LinkedList<>();

        listF.add(formC1);
        listF.add(formEQform1);
        listF2.add(formEQform1Lower);

        Metier m1 = new Metier();
        m1.setIntitule("M1");
        m1.setObligatoire(OBLIGATOIRE);
        Metier m2 = new Metier();
        m2.setObligatoire(OBLIGATOIRE);
        m2.setIntitule("M2");
        Set<Metier> metiers = new HashSet<Metier>();
        metiers.add(m1);
        metiers.add(m2);

        Fonctionnelle f1 = new Fonctionnelle();
        f1.setIntitule("F1");
        f1.setObligatoire(OBLIGATOIRE);
        Fonctionnelle f2 = new Fonctionnelle();
        f2.setIntitule("F2");
        f2.setObligatoire(PLUS);
        Set<Fonctionnelle> fonctionnelles = new HashSet<Fonctionnelle>();
        fonctionnelles.add(f1);
        fonctionnelles.add(f2);

        Technique t1 = new Technique();
        t1.setIntitule("T1");
        t1.setObligatoire(OBLIGATOIRE);
        Technique t2 = new Technique();
        t2.setIntitule("T2");
        t2.setObligatoire(PLUS);
        Set<Technique> techniques = new HashSet<Technique>();
        techniques.add(t1);
        techniques.add(t2);

        Langue l1 = new Langue();
        l1.setIntitule("L1");
        l1.setObligatoire(OBLIGATOIRE);
        Langue l2 = new Langue();
        l2.setIntitule("L2");
        l2.setObligatoire(OBLIGATOIRE);
        Set<Langue> langues = new HashSet<Langue>();
        langues.add(l1);
        langues.add(l2);

        Formation_Recruteur form1 = new Formation_Recruteur();
        form1.setIntitule("FORM1");

        Set<Formation_Recruteur> formation_recruteurs = new HashSet<>();
        formation_recruteurs.add(form1);

        Certification cert1 = new Certification();
        Certification cert2 = new Certification();
        cert1.setIntitule("cert1");
        cert2.setIntitule("cert2");

        Set<Certification> listCert1 = new HashSet<>();
        listCert1.add(cert1);
        listCert1.add(cert2);
        Set<Certification> listCert2 = new HashSet<>();

        this.dossierNominal = new Dossier_poste();
        this.dossierNominal.setMetiers(metiers);
        this.dossierNominal.setFonctionnelles(fonctionnelles);
        this.dossierNominal.setTechniques(techniques);
        this.dossierNominal.setLangues(langues);
        this.dossierNominal.setFormations(new HashSet<>());
        this.dossierNominal.setFormations(formation_recruteurs);
        this.dossierNominal.setCertifications(listCert1);

        this.dossierVide = new Dossier_poste();
        this.dossierVide.setMetiers(new HashSet<>());
        this.dossierVide.setFonctionnelles(new HashSet<>());
        this.dossierVide.setTechniques(new HashSet<>());
        this.dossierVide.setLangues(new HashSet<>());
        this.dossierVide.setFormations(new HashSet<>());
        this.dossierVide.setCertifications(listCert2);

        c0.setCompetence(list1);
        c1.setCompetence(list2);
        c2.setCompetence(new LinkedList<>());
        c0.setFormation(listF);
        c1.setFormation(listF2);
        c2.setFormation(new LinkedList<>());
        c0.setCertifications(listCf1);
        c1.setCertifications(listCf2);
        c2.setCertifications(listCf2);

        this.listeC = new LinkedList<>();

        this.listeC.add(c0);
        this.listeC.add(c1);
        this.listeC.add(c2);

    }

    @Test
    public void MatchingDossierNominalTest(){
        List<MatchedCandidat> result = M.match(dossierNominal, this.listeC, 0, 2);

        assertTrue(result.get(0).getCandidat().equals(this.listeC.get(1)));
        assertTrue(result.get(1).getCandidat().equals(this.listeC.get(0)));
        assertTrue(result.get(2).getCandidat().equals(this.listeC.get(2)));
    }

    @Test
    public void MatchingDossierNominal2Test(){
        List<MatchedCandidat> result = M.match(dossierNominal, this.listeC, 0, 1);

        assertTrue(result.get(0).getCandidat().equals(this.listeC.get(1)));
        assertTrue(result.get(1).getCandidat().equals(this.listeC.get(0)));
    }

    @Test
    public void MatchingDossierNominalOnlyBestTest(){
        List<MatchedCandidat> result = M.match(dossierNominal, this.listeC, 0, 0);

        assertTrue(result.get(0).getCandidat().equals(this.listeC.get(1)));
    }

    @Test
    public void MatchingDossierNominalWrongTest(){
        List<MatchedCandidat> result = M.match(dossierNominal, this.listeC, 0, 0);

        assertTrue(result.get(0).getCandidat().equals(this.listeC.get(1)));
    }

    @Test
    public void MatchingUnCandidatNominalTest() {
        HashMap<String, Boolean>[] result = this.M.matchCandidat(this.dossierNominal, this.listeC.get(0));

        // Test des competences

        HashMap<String, Boolean> competences = result[0];

        assertFalse(competences.get("M1"));
        assertFalse(competences.get("M2"));
        assertTrue(competences.get("F1"));
        assertTrue(competences.get("F2"));
        assertFalse(competences.get("T1"));
        assertTrue(competences.get("T2"));
        assertTrue(competences.get("L1"));
        assertFalse(competences.get("L2"));

        // Test des Formations

        HashMap<String, Boolean> formations = result[1];

        assertTrue(formations.get("FORM1"));

        // Test des Formations

        HashMap<String, Boolean> certifications = result[2];

        assertFalse(certifications.get("cert1"));
        assertTrue(certifications.get("cert2"));

    }

    @Test
    public void MatchingUnCandidatNominalTailleTest(){
        HashMap<String, Boolean>[] result = this.M.matchCandidat(this.dossierNominal, this.listeC.get(0));

        // Test des competences

        HashMap<String, Boolean> competences = result[0];

        assertTrue(competences.size() == 8);

        // Test des Formations

        HashMap<String, Boolean> formations = result[1];

        assertTrue(formations.size() == 1);
    }

    @Test
    public void MatchingUnCandidatFullTrueTest(){
        HashMap<String, Boolean>[] result = this.M.matchCandidat(this.dossierNominal, this.listeC.get(1));

        // Test des competences

        HashMap<String, Boolean> competences = result[0];

        assertTrue(competences.get("M1"));
        assertTrue(competences.get("M2"));
        assertTrue(competences.get("F1"));
        assertTrue(competences.get("F2"));
        assertTrue(competences.get("T1"));
        assertTrue(competences.get("T2"));
        assertTrue(competences.get("L1"));
        assertTrue(competences.get("L2"));

        // Test des Formations

        HashMap<String, Boolean> formations = result[1];

        assertTrue(formations.get("FORM1"));

    }

    @Test
    public void MatchingUnCandidatFullTrueTailleTest(){
        HashMap<String, Boolean>[] result = this.M.matchCandidat(this.dossierNominal, this.listeC.get(1));

        // Test des competences

        HashMap<String, Boolean> competences = result[0];

        assertTrue(competences.size() == 8);

        // Test des Formations

        HashMap<String, Boolean> formations = result[1];

        assertTrue(formations.size() == 1);
    }

    @Test
    public void MatchingUnCandidatFullFalseTest(){
        HashMap<String, Boolean>[] result = this.M.matchCandidat(this.dossierNominal, this.listeC.get(2));

        // Test des competences

        HashMap<String, Boolean> competences = result[0];

        assertFalse(competences.get("M1"));
        assertFalse(competences.get("M2"));
        assertFalse(competences.get("F1"));
        assertFalse(competences.get("F2"));
        assertFalse(competences.get("T1"));
        assertFalse(competences.get("T2"));
        assertFalse(competences.get("L1"));
        assertFalse(competences.get("L2"));

        // Test des Formations

        HashMap<String, Boolean> formations = result[1];

        assertFalse(formations.get("FORM1"));

    }

    @Test
    public void MatchingUnCandidatFullFalseTailleTest(){
        HashMap<String, Boolean>[] result = this.M.matchCandidat(this.dossierNominal, this.listeC.get(2));

        // Test des competences

        HashMap<String, Boolean> competences = result[0];

        assertTrue(competences.size() == 8);

        // Test des Formations

        HashMap<String, Boolean> formations = result[1];

        assertTrue(formations.size() == 1);
    }

    @Test
    public void MatchingUnCandidatEmptyTest(){
        HashMap<String, Boolean>[] result = this.M.matchCandidat(this.dossierVide, this.listeC.get(0));

        assertTrue(result[0].size() == 0);
        assertTrue(result[1].size() == 0);
        assertTrue(result[2].size() == 0);

    }

}
