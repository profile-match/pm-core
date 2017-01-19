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
@Table(name="FONCTIONNELLE")
@NamedQueries({
  @NamedQuery(name = "Fonctionnelle.completeFonctionnelle",
        query = "SELECT p FROM Fonctionnelle p where p.fonctionnelle LIKE :nom_fonctionnelle")
    })
public class Fonctionnelle implements Serializable {

    @Id
    private Long id;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="fonctionnelle_id")
    public Long getId() {
        return id;
    }

    
    @Column(name="nom_fonctionnelle")
    private String fonctionnelle;
    @Column(name="is_obligatoire_fonctionnelle")
    private int obligatoire;
    
    
    private Set<Dossier_poste> postes = new HashSet<>(0);
    
    @ManyToMany(cascade=CascadeType.ALL, mappedBy="fonctionnelles")
    public Set<Dossier_poste> getPostes() {
        return postes;
    }

    public String getFonctionnelles() {
        return fonctionnelle;
    }

    public void setFonctionnelles(String fonctionnelles) {
        this.fonctionnelle = fonctionnelles;
    }

    public int getObligatoire() {
        return obligatoire;
    }

    public void setObligatoire(int obligatoire) {
        this.obligatoire = obligatoire;
    }
    
    
    
    public Fonctionnelle(){}

    public String getFonctionnelle() {
        return fonctionnelle;
    }

    public void setFonctionnelle(String fonctionnelle) {
        this.fonctionnelle = fonctionnelle;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPostes(Set<Dossier_poste> postes) {
        this.postes = postes;
    }
    
    
    
    
}
