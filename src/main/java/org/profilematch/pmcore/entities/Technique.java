/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.profilematch.pmcore.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Steven Klinger && Pierre Leriche
 */
@Entity
@Table(name="TECHNIQUE")
@NamedQueries({
  @NamedQuery(name = "Technique.completetechnique",
        query = "SELECT p FROM Technique p where p.technique LIKE :nom_technique")
    })
public class Technique implements Serializable {

    @Id
    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="technique_id")
    public Long getId() {
        return id;
    }

    
     
    @Column(name="nom_technique")
    private String technique;
    @Column(name="is_obligatoire_technique")
    private int obligatoire;
    
    
    private Set<Dossier_poste> postes = new HashSet<>(0);
    
    @ManyToMany(cascade=CascadeType.ALL, mappedBy="techniques")
    public Set<Dossier_poste> getPostes() {
        return postes;
    }

    public String getTechnique() {
        return technique;
    }

    public void setFonctionnelles(String faires) {
        this.technique = faires;
    }

    public int getObligatoire() {
        return obligatoire;
    }

    public void setObligatoire(int obligatoire) {
        this.obligatoire = obligatoire;
    }
    
    
    
    public Technique(){}

    public void setId(Long id) {
        this.id = id;
    }

    public void setTechnique(String technique) {
        this.technique = technique;
    }

    public void setPostes(Set<Dossier_poste> postes) {
        this.postes = postes;
    }
    
    
}
