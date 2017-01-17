/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.profilematch.pmcore.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="METIER")
public class Metier {

    @Id
    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="metier_id")
    public Long getId() {
        return id;
    }

    
    
    @Column(name="nom_metier")
    private String metier;
    @Column(name="is_obligatoire_metier")
    private int obligatoire;
    
    
    private Set<Dossier_poste> postes = new HashSet<>(0);
    
    @ManyToMany(cascade=CascadeType.ALL, mappedBy="metiers")
    public Set<Dossier_poste> getPostes() {
        return postes;
    }

    public String getMetier() {
        return metier;
    }

    public void setMetier(String metiers) {
        this.metier = metiers;
    }

    public int getObligatoire() {
        return obligatoire;
    }

    public void setObligatoire(int obligatoire) {
        this.obligatoire = obligatoire;
    }
    
    
    
    public Metier(){}

    public void setId(Long id) {
        this.id = id;
    }

    public void setPostes(Set<Dossier_poste> postes) {
        this.postes = postes;
    }
    
    
}
