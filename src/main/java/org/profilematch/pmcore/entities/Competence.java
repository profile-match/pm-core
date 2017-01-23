/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.profilematch.pmcore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author antoine
 */
@Entity
public class Competence implements Serializable {

    @JsonIgnore
    private Long id_comp;

    private String domaine_de_competence;
    private String competences;

    private Set<Candidat> candidat = new HashSet<>(0);

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "competence")
    @JsonIgnore
    public Set<Candidat> getCandidat() {
        return candidat;
    }

    public void setCandidat(Set<Candidat> candidat) {
        this.candidat = candidat;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id_comp;
    }

    public void setId(Long id) {
        this.id_comp = id;
    }

    public String getDomaine_de_competence() {
        return domaine_de_competence;
    }

    public void setDomaine_de_competence(String domaine_de_competence) {
        this.domaine_de_competence = domaine_de_competence;
    }

    public String getCompetences() {
        return competences;
    }

    public void setCompetences(String competences) {
        this.competences = competences;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_comp != null ? id_comp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Competence)) {
            return false;
        }
        Competence other = (Competence) object;
        if ((this.id_comp == null && other.id_comp != null) || (this.id_comp != null && !this.id_comp.equals(other.id_comp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.profilematch.pmcore.entities.Competence[ id=" + id_comp + " ]";
    }

}
