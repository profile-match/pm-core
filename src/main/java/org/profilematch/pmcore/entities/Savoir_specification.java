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
import javax.persistence.Table;

/**
 *
 * @author Steven Klinger && Pierre Leriche
 */
@Entity
@Table(name = "SAVOIR_SPECIFICATION")
public class Savoir_specification implements Serializable {

    @Id
    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "savoir_specification_id")
    public Long getId() {
        return id;
    }

    @Column(name = "nom_savoir_specification")
    private String specification;
    @Column(name = "is_obligatoire_savoir_specification")
    private int obligatoire;

    private Set<Dossier_poste> postes = new HashSet<>(0);

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "savoir_specifications")
    public Set<Dossier_poste> getPostes() {
        return postes;
    }

    public String getSpecification() {
        return specification;
    }

    public void setFonctionnelles(String faires) {
        this.specification = faires;
    }

    public int getObligatoire() {
        return obligatoire;
    }

    public void setObligatoire(int obligatoire) {
        this.obligatoire = obligatoire;
    }

    public Savoir_specification() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public void setPostes(Set<Dossier_poste> postes) {
        this.postes = postes;
    }

}
