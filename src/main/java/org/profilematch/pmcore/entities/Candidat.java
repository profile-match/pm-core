package org.profilematch.pmcore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author antoine
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Candidat.findAll", query = "SELECT c FROM Candidat c"),
    @NamedQuery(name = "Candidat.countMale", query = "SELECT count(c) FROM Candidat c WHERE c.isMale = TRUE"),
    @NamedQuery(name = "Candidat.countFemelle", query = "SELECT count(c) FROM Candidat c WHERE c.isMale = FALSE")
    //@NamedQuery(name = "Candidat.findPostule", 
   //         query = "SELECT cand FROM Candidat cand where cand.id = some (select pos.candidat_id from POSTE_CANDIDAT_POSTULE pos where pos.poste_id =:poste_id)")
})
public class Candidat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nom, prenom, email, loisirs, photo, adresse, telfix, telperso;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    private boolean isBanned;
    private boolean isSuspended;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "listeCandidat", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Dossier_poste> listDossier = new HashSet<>(0);

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<ExperiencePro> experiencePro;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Formation> formation;

    private boolean isMale;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "Candidat_Competence", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "id_comp"))
    private List<Competence> competence;

    public Set<Dossier_poste> getDossierPoste() {
        return listDossier;
    }

    public Candidat() {

    }

    public Candidat(Long id, String nom, String prenom, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.competence = new ArrayList<>();
    }

    public Candidat(Long id, String nom, String prenom, String email, String loisirs, List<ExperiencePro> ep, List<Formation> f, List<Competence> c) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.loisirs = loisirs;
        this.experiencePro = ep;
        this.formation = f;
        this.competence = c;
    }

    public Candidat(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;

    }

    public boolean isIsMale() {
        return isMale;
    }

    public void setIsMale(boolean isMale) {
        this.isMale = isMale;
    }

    public String getLoisirs() {
        return loisirs;
    }

    public void setLoisirs(String loisirs) {
        this.loisirs = loisirs;
    }

    public List<Formation> getFormation() {
        return formation;
    }

    public void setFormation(List<Formation> formation) {
        this.formation = formation;
    }

    public List<Competence> getCompetence() {
        return competence;
    }

    public void setCompetence(List<Competence> competence) {
        this.competence = competence;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelfix() {
        return telfix;
    }

    public void setTelfix(String telfix) {
        this.telfix = telfix;
    }

    public String getTelperso() {
        return telperso;
    }

    public void setTelperso(String telperso) {
        this.telperso = telperso;
    }


    public List<ExperiencePro> getExperiencePro() {
        return experiencePro;
    }

    public void setExperiencePro(List<ExperiencePro> experiencePro) {
        this.experiencePro = experiencePro;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public void setIsBanned(boolean isBanned) {
        this.isBanned = isBanned;
    }


    public boolean isSuspended() {
        return isSuspended;
    }
    
    public void setSuspended(boolean b) {
        this.isSuspended = b;
    }
    

    public void setIsSuspended(boolean isSuspended) {
        this.isSuspended = isSuspended;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Candidat)) {
            return false;
        }
        Candidat other = (Candidat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.imp.entities.Candidat[ id=" + id + " ]";
    }

}
