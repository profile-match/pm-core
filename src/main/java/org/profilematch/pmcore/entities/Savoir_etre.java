/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.profilematch.pmcore.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Steven Klinger && Pierre Leriche
 */
@Entity
@Table(name = "SAVOIR_ETRE")
public class Savoir_etre implements Serializable {

    @JsonIgnore
    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "savoir_etre_id")
    public Long getId() {
        return id;
    }

    @Column(name = "nom_savoir_etre")
    private String intitule;
    @Column(name = "is_obligatoire_savoir_etre")
    private int obligatoire;

    private Set<Dossier_poste> postes = new HashSet<>(0);

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "savoir_etres", fetch = FetchType.LAZY)
    @JsonIgnore
    public Set<Dossier_poste> getPostes() {
        return postes;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    

    public int getObligatoire() {
        return obligatoire;
    }

    public void setObligatoire(int obligatoire) {
        this.obligatoire = obligatoire;
    }

    public Savoir_etre() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPostes(Set<Dossier_poste> postes) {
        this.postes = postes;
    }

}
