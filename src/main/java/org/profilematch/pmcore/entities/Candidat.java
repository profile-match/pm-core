package org.profilematch.pmcore.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author antoine
 */
@Entity
public class Candidat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nom, prenom, email, loisirs;
    @OneToOne(cascade = CascadeType.PERSIST)
    private ExperiencePro experiencePro;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Formation formation;
    @OneToMany(cascade = CascadeType.PERSIST)
    private Collection<Competence> competence;

    public Candidat() {

    }

    public Candidat(Long id, String nom, String prenom, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public Candidat(Long id, String nom, String prenom, String email, String loisirs, ExperiencePro ep, Formation f, Collection<Competence> c) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.loisirs = loisirs;
        this.experiencePro = ep;
        this.formation = f;
        this.competence = c;
    }

    public String getLoisirs() {
        return loisirs;
    }

    public void setLoisirs(String loisirs) {
        this.loisirs = loisirs;
    }

    public ExperiencePro getExperiencePro() {
        return experiencePro;
    }

    public void setExperiencePro(ExperiencePro experiencePro) {
        this.experiencePro = experiencePro;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public Collection<Competence> getCompetence() {
        return competence;
    }

    public void setCompetence(Collection<Competence> competence) {
        this.competence = competence;
    }    
    
    public Candidat(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
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
