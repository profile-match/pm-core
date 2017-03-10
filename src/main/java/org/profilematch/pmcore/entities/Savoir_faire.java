package org.profilematch.pmcore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "SAVOIR_FAIRE")
public class Savoir_faire implements Serializable {

    @JsonIgnore
    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "savoir_faire_id")
    public Long getId() {
        return id;
    }

    @Column(name = "nom_savoir_faire")
    private String intitule;
    @Column(name = "is_obligatoire_savoir_faire")
    private int obligatoire;

    private Set<Dossier_poste> postes = new HashSet<>(0);

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "savoir_faires", fetch = FetchType.LAZY)
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

    public Savoir_faire() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    

    public void setPostes(Set<Dossier_poste> postes) {
        this.postes = postes;
    }

}
