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
@Table(name="LANGUE")
@NamedQueries({
  @NamedQuery(name = "Langue.completeLangue",
        query = "SELECT p FROM Langue p where p.langue LIKE :nom_langue")
    })
public class Langue implements Serializable {

    @Id
    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="langue_id")
    public Long getId() {
        return id;
    }

    
    @Column(name="nom_langue")
    private String langue;
    @Column(name="is_obligatoire_langue")
    private int obligatoire;
    
    
    private Set<Dossier_poste> postes = new HashSet<>(0);
    
    @ManyToMany(cascade=CascadeType.ALL, mappedBy="langues")
    public Set<Dossier_poste> getPostes() {
        return postes;
    }

    public String getLangue() {
        return langue;
    }

    public void setFonctionnelles(String langues) {
        this.langue = langues;
    }

    public int getObligatoire() {
        return obligatoire;
    }

    public void setObligatoire(int obligatoire) {
        this.obligatoire = obligatoire;
    }
    
    
    
    public Langue(){}

    public void setId(Long id) {
        this.id = id;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public void setPostes(Set<Dossier_poste> postes) {
        this.postes = postes;
    }
    
    
}
