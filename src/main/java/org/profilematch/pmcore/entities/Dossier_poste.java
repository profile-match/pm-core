package org.profilematch.pmcore.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Steven Klinger && Pierre Leriche && Mathieu Jeanmougin && Antoine Baran
 */
@Entity
@Table(name = "DOSSIER")
@NamedQueries({
    @NamedQuery(name = "Dossier.findAll", query = "SELECT d FROM Dossier_poste d"),
    @NamedQuery(name = "Dossier.getDossier",
            query = "SELECT p FROM Dossier_poste p where p.id = :poste_id"),
    @NamedQuery(name = "Dossier.getAllDossier",
            query = "SELECT p FROM Dossier_poste p where p.id_recruteur = :id_recruteur"),
    @NamedQuery(name = "Dossier.findByIntitule",
            query = "SELECT p.intitule FROM Dossier_poste p where p.id_recruteur = :id_recruteur"),
    @NamedQuery(name = "Dossier.deleteDossier",
            query = "DELETE FROM Dossier_poste p WHERE p.id = :poste_id"),
    @NamedQuery(name = "Dossier.findByElement",
            query = "SELECT p.intitule, p.resume, p.lieu_travail, p.type_contrat, p.date_publication from Dossier_poste p where p.id_recruteur = :id_recruteur")
})
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class Dossier_poste implements Serializable {

    private int id_recruteur;
    private Date date_publication;
    private String reference;
    private String intitule;
    private String indice_salaire;
    /*Clé étrangère sur Niveau_Salaire)*/
    private int salaire_min;
    private int salaire_max;
    private String type_contrat;
    private String resume;
    private String point_attention;
    /*Résumé des points importants de l'offre*/
    private String lieu_travail;
    private int afficher_moyenne;
    private String organisation;
    /*Organisation demandeuse de l'offre*/
    private String equipe_concernee;
    private Long id;

    // initialisation des sets
    
    private Set<Metier> metiers;

    private Set<Certification> certifications;

    private Set<Fonctionnelle> fonctionnelles;

    private Set<Technique> techniques;

    private Set<Langue> langues;

    private Set<Formation_Recruteur> formations;

    private Set<Savoir_etre> savoir_etres;

    private Set<Savoir_faire> savoir_faires;

    private Set<Savoir_specification> savoir_specifications;
    
    private Set<Candidat> listeCandidat;
    
    public Dossier_poste(int id_recruteur, Date date_publication, String reference, String intitule, String indice_salaire, int salaire_min, int salaire_max, String type_contrat, String resume, String point_attention, String lieu_travail, String organisation, String equipe, String service, Set<Certification> certifications, Set<Metier> metiers, Set<Technique> techniques, Set<Langue> langues, Set<Formation_Recruteur> formations, Set<Fonctionnelle> fonctionnelles, Set<Savoir_etre> savoir_etres, Set<Savoir_faire> savoir_faires, Set<Savoir_specification> savoir_specifications, Set<Candidat> listeCandidat) {

        this.id_recruteur = id_recruteur;

        this.reference = reference;
        this.intitule = intitule;
        this.indice_salaire = indice_salaire;
        this.salaire_min = salaire_min;
        this.salaire_max = salaire_max;
        this.type_contrat = type_contrat;
        this.resume = resume;
        this.point_attention = point_attention;
        this.lieu_travail = lieu_travail;
        this.organisation = organisation;
        this.equipe_concernee = equipe;
        this.certifications = certifications;
        this.metiers = metiers;
        this.fonctionnelles = fonctionnelles;
        this.langues = langues;
        this.techniques = techniques;
        this.formations = formations;
        this.savoir_etres = savoir_etres;
        this.savoir_faires = savoir_faires;
        this.savoir_specifications = savoir_specifications;
        this.listeCandidat = listeCandidat;
    }

    public Dossier_poste(int id_recruteur, Date date_publication, String reference, String intitule, String indice_salaire, int salaire_min, int salaire_max, String type_contrat, String resume, String point_attention, String lieu_travail, String organisation, String equipe) {

        this.id_recruteur = id_recruteur;

        this.date_publication = date_publication;
        this.reference = reference;
        this.intitule = intitule;
        this.indice_salaire = indice_salaire;
        this.salaire_min = salaire_min;
        this.salaire_max = salaire_max;
        this.type_contrat = type_contrat;
        this.resume = resume;
        this.point_attention = point_attention;
        this.lieu_travail = lieu_travail;
        this.organisation = organisation;
        this.equipe_concernee = equipe;
    }

    public Dossier_poste() {
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "POSTE_METIER", joinColumns = @JoinColumn(name = "poste_id"), inverseJoinColumns = @JoinColumn(name = "metier_id"))
    public Set<Metier> getMetiers() {
        return metiers;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "POSTE_CERTIFICATION", joinColumns = @JoinColumn(name = "poste_id"), inverseJoinColumns = @JoinColumn(name = "certification_id"))
    public Set<Certification> getCertifications() {
        return certifications;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "POSTE_FONCTIONNELLE", joinColumns = @JoinColumn(name = "poste_id"), inverseJoinColumns = @JoinColumn(name = "fonctionnelle_id"))
    public Set<Fonctionnelle> getFonctionnelles() {
        return fonctionnelles;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "POSTE_TECHNIQUE", joinColumns = @JoinColumn(name = "poste_id"), inverseJoinColumns = @JoinColumn(name = "technique_id"))
    public Set<Technique> getTechniques() {
        return techniques;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "POSTE_LANGUE", joinColumns = @JoinColumn(name = "poste_id"), inverseJoinColumns = @JoinColumn(name = "langue_id"))
    public Set<Langue> getLangues() {
        return langues;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "POSTE_FORMATION", joinColumns = @JoinColumn(name = "poste_id"), inverseJoinColumns = @JoinColumn(name = "formation_id"))
    public Set<Formation_Recruteur> getFormations() {
        return formations;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "POSTE_SAVOIR_ETRE", joinColumns = @JoinColumn(name = "poste_id"), inverseJoinColumns = @JoinColumn(name = "savoir_etre_id"))
    public Set<Savoir_etre> getSavoir_etres() {
        return savoir_etres;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "POSTE_SAVOIR_FAIRE", joinColumns = @JoinColumn(name = "poste_id"), inverseJoinColumns = @JoinColumn(name = "savoir_faire_id"))
    public Set<Savoir_faire> getSavoir_faires() {
        return savoir_faires;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "POSTE_SAVOIR_SPECIFICATION", joinColumns = @JoinColumn(name = "poste_id"), inverseJoinColumns = @JoinColumn(name = "savoir_specification_id"))
    public Set<Savoir_specification> getSavoir_specifications() {
        return savoir_specifications;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "POSTE_CANDIDAT_POSTULE", joinColumns = @JoinColumn(name = "poste_id"), inverseJoinColumns = @JoinColumn(name = "candidat_id"))
    public Set<Candidat> getListeCandidat() {
        return listeCandidat;
    }
    
    public void setMetiers(Set<Metier> metiers) {
        this.metiers = metiers;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "poste_id")
    public Long getId() {
        return id;
    }

    public void setId(Long ID) {
        this.id = ID;
    }

    public int getId_recruteur() {
        return id_recruteur;
    }

    public void setId_recruteur(int id_recruteur) {
        this.id_recruteur = id_recruteur;
    }

    public Date getDate_publication() {
        return date_publication;
    }

    public void setDate_publication(Date date_publication) {
        this.date_publication = date_publication;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getIndice_salaire() {
        return indice_salaire;
    }

    public void setIndice_salaire(String indice_salaire) {
        this.indice_salaire = indice_salaire;
    }

    public int getSalaire_min() {
        return salaire_min;
    }

    public void setSalaire_min(int salaire_min) {
        this.salaire_min = salaire_min;
    }

    public int getSalaire_max() {
        return salaire_max;
    }

    public void setSalaire_max(int salaire_max) {
        this.salaire_max = salaire_max;
    }

    public String getType_contrat() {
        return type_contrat;
    }

    public void setType_contrat(String type_contrat) {
        this.type_contrat = type_contrat;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getPoint_attention() {
        return point_attention;
    }

    public void setPoint_attention(String point_attention) {
        this.point_attention = point_attention;
    }

    public String getLieu_travail() {
        return lieu_travail;
    }

    public void setLieu_travail(String lieu_travail) {
        this.lieu_travail = lieu_travail;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public void setCertifications(Set<Certification> certifications) {
        this.certifications = certifications;
    }

    public void setFonctionnelles(Set<Fonctionnelle> fonctionnelles) {
        this.fonctionnelles = fonctionnelles;
    }

    public void setTechniques(Set<Technique> techniques) {
        this.techniques = techniques;
    }

    public void setLangues(Set<Langue> langues) {
        this.langues = langues;
    }

    public void setFormations(Set<Formation_Recruteur> formations) {
        this.formations = formations;
    }

    public void setSavoir_etres(Set<Savoir_etre> savoir_etres) {
        this.savoir_etres = savoir_etres;
    }

    public void setSavoir_faires(Set<Savoir_faire> savoir_faires) {
        this.savoir_faires = savoir_faires;
    }

    public void setSavoir_specifications(Set<Savoir_specification> savoir_specifications) {
        this.savoir_specifications = savoir_specifications;
    }

    public int getAfficher_moyenne() {
        return afficher_moyenne;
    }

    public void setAfficher_moyenne(int afficher_moyenne) {
        this.afficher_moyenne = afficher_moyenne;
    }

    public String getEquipe_concernee() {
        return equipe_concernee;
    }

    public void setEquipe_concernee(String equipe_concernee) {
        this.equipe_concernee = equipe_concernee;
    }

    public void setListeCandidat(Set<Candidat> listeCandidat) {
        this.listeCandidat = listeCandidat;
    }

    
    
}
