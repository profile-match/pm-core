/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.profilematch.pmcore.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author antoine
 */
@Entity
public class Formation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String intitule_de_formation;
    private String etablissement;
    private String description_formation;
    private Date date_debut_format;
    private Date date_fin_format;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIntitule_de_formation() {
        return intitule_de_formation;
    }

    public void setIntitule_de_formation(String intitule_de_formation) {
        this.intitule_de_formation = intitule_de_formation;
    }

    public String getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(String etablissement) {
        this.etablissement = etablissement;
    }

    public String getDescription_formation() {
        return description_formation;
    }

    public void setDescription_formation(String description_formation) {
        this.description_formation = description_formation;
    }

    public Date getDate_debut_format() {
        return date_debut_format;
    }

    public void setDate_debut_format(Date date_debut_format) {
        this.date_debut_format = date_debut_format;
    }

    public Date getDate_fin_format() {
        return date_fin_format;
    }

    public void setDate_fin_format(Date date_fin_format) {
        this.date_fin_format = date_fin_format;
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
        if (!(object instanceof Formation)) {
            return false;
        }
        Formation other = (Formation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.profilematch.pmcore.entities.Formation[ id=" + id + " ]";
    }

}
