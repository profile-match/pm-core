/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.profilematch.pmcore.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Steven Klinger && Pierre Leriche
 */
@Entity
@Table(name = "DOSSIER")
@NamedQueries({
  @NamedQuery(name="Dossier.IntitulefindAll",
              query="SELECT intitule FROM Dossier e"),
  @NamedQuery(name="Dossier.findByPrimaryKey",
              query="SELECT e FROM Dossier e WHERE e.id = :id"),
  @NamedQuery(name="Dossier.findByName",
              query="SELECT e FROM Dossier e WHERE e.name = :name")
})
public class Dossier_poste implements Serializable {

   
    private int id_recruteur;
    private int id_entreprise;
    private Date date_publication;
    private String reference;
    private String intitule;
    private int indice_salaire;
    /*Clé étrangère sur Niveau_Salaire)*/
    private int salaire_min;
    private int salaire_max;
    private String type_contrat;
    private String resume;
    private String point_attention;
    /*Résumé des points importants de l'offre*/
    private String lieu_travail;
    private String organisation;
    /*Organisation demandeuse de l'offre*/
    private String service;
    /*service de l'organisation concernée*/
    private String departement;
    private String equipe;
    
    
    private Long id;

   
    public Dossier_poste(int id_recruteur, int id_entreprise, Date date_publication, String reference, String intitule, int indice_salaire, int salaire_min, int salaire_max, String type_contrat, String resume, String point_attention, String lieu_travail, String organisation, String service, String departement, String equipe, Set<Certification> certifications,Set<Metier> metiers, Set<Technique> techniques, Set<Langue> langues, Set<Formation> formations, Set<Fonctionnelle> fonctionnelles,Set<Savoir_etre> savoir_etres, Set<Savoir_faire> savoir_faires, Set<Savoir_specification> savoir_specifications) {
        this.id_recruteur = id_recruteur;
        this.id_entreprise = id_entreprise;
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
        this.service = service;
        this.departement = departement;
        this.equipe = equipe;
        this.certifications = certifications;
        this.metiers = metiers;
        this.fonctionnelles = fonctionnelles;
        this.langues = langues;
        this.techniques = techniques;
        this.formations = formations;
        this.savoir_etres = savoir_etres;
       this.savoir_faires = savoir_faires;
       this.savoir_specifications = savoir_specifications;
       
        
                
      
    }

    
    public Dossier_poste(){}
    /*
Afficher_moyenne : int{0,1}
*/
private Set<Certification> certifications;
private Set<Metier> metiers;
private Set<Fonctionnelle> fonctionnelles;
private Set<Technique> techniques;
private Set<Langue> langues;
private Set<Formation> formations;
private Set<Savoir_etre> savoir_etres;
private Set<Savoir_faire> savoir_faires;
private Set<Savoir_specification> savoir_specifications;





@ManyToMany(cascade=CascadeType.ALL)  
    @JoinTable(name="POSTE_CERTIFICATION", joinColumns=@JoinColumn(name="poste_id"), inverseJoinColumns=@JoinColumn(name="certification_id")) public Set<Certification> getCertifications()  
    {  
        return certifications;  
    }
    
     
   @ManyToMany(cascade=CascadeType.ALL)  
    @JoinTable(name="POSTE_METIER", joinColumns=@JoinColumn(name="poste_id"), inverseJoinColumns=@JoinColumn(name="metier_id")) public Set<Metier> getMetiers()  
    {  
        return metiers;  
    }
    
    @ManyToMany(cascade=CascadeType.ALL)  
    @JoinTable(name="POSTE_FONCTIONNELLE", joinColumns=@JoinColumn(name="poste_id"), inverseJoinColumns=@JoinColumn(name="fonctionnelle_id")) public Set<Fonctionnelle> getFonctionnelles()  
    {  
        return fonctionnelles;  
    }
    
    @ManyToMany(cascade=CascadeType.ALL)  
    @JoinTable(name="POSTE_TECHNIQUE", joinColumns=@JoinColumn(name="poste_id"), inverseJoinColumns=@JoinColumn(name="technique_id")) public Set<Technique> getTechniques()  
    {  
        return techniques;  
    }
    
    @ManyToMany(cascade=CascadeType.ALL)  
    @JoinTable(name="POSTE_LANGUE", joinColumns=@JoinColumn(name="poste_id"), inverseJoinColumns=@JoinColumn(name="langue_id")) public Set<Langue> getLangues()  
    {  
        return langues;  
    }
    
    @ManyToMany(cascade=CascadeType.ALL)  
    @JoinTable(name="POSTE_FORMATION", joinColumns=@JoinColumn(name="poste_id"), inverseJoinColumns=@JoinColumn(name="formation_id")) public Set<Formation> getFormations()  
    {  
        return formations;  
    }
    
    @ManyToMany(cascade=CascadeType.ALL)  
    @JoinTable(name="POSTE_SAVOIR_ETRE", joinColumns=@JoinColumn(name="poste_id"), inverseJoinColumns=@JoinColumn(name="savoir_etre_id")) public Set<Savoir_etre> getSavoir_etres()  
    {  
        return savoir_etres;      }
    
    @ManyToMany(cascade=CascadeType.ALL)  
    @JoinTable(name="POSTE_SAVOIR_FAIRE", joinColumns=@JoinColumn(name="poste_id"), inverseJoinColumns=@JoinColumn(name="savoir_faire_id")) public Set<Savoir_faire> getSavoir_faires()  
    {  
        return savoir_faires;  
    }
    
    @ManyToMany(cascade=CascadeType.ALL)  
    @JoinTable(name="POSTE_SAVOIR_SPECIFICATION", joinColumns=@JoinColumn(name="poste_id"), inverseJoinColumns=@JoinColumn(name="savoir_specification_id")) public Set<Savoir_specification> getSavoir_specifications()  
    {  
        return savoir_specifications;  
    }
    
    

    public void setMetiers(Set<Metier> metiers) {
        this.metiers = metiers;
    }
    
   
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="poste_id")
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

    public int getId_entreprise() {
        return id_entreprise;
    }

    public void setId_entreprise(int id_entreprise) {
        this.id_entreprise = id_entreprise;
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

    public int getIndice_salaire() {
        return indice_salaire;
    }

    public void setIndice_salaire(int indice_salaire) {
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

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getEquipe() {
        return equipe;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
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

    public void setFormations(Set<Formation> formations) {
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

    
 
}
