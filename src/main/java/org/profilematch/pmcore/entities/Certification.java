/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.profilematch.pmcore.entities;

import java.io.Serializable;
import java.util.Date;
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
@Table(name="CERTIFICATION")
public class Certification implements Serializable {

    @Id
    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="certification_id")
    public Long getId() {
        return id;
    }

  
    @Column(name="nom_certification")
    private String certification;
    
    @Column(name="annee_certification")
    private Date annee;
    
    @Column(name="is_obligatoire_certification")
    private int obligatoire;
    
    private Set<Dossier_poste> postes = new HashSet<>(0);
    
    @ManyToMany(cascade=CascadeType.ALL, mappedBy="certifications")
    public Set<Dossier_poste> getPostes() {
        return postes;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public Date getAnnee() {
        return annee;
    }

    public void setAnnee(Date annee) {
        this.annee = annee;
    }

    public int getObligatoire() {
        return obligatoire;
    }

    public void setObligatoire(int obligatoire) {
        this.obligatoire = obligatoire;
    }

    public Certification() {
    }

    public Certification(String certification, Date annee, int obligatoire) {
        this.certification = certification;
        this.annee = annee;
        this.obligatoire = obligatoire;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPostes(Set<Dossier_poste> postes) {
        this.postes = postes;
    }
    
    
    
    
}
