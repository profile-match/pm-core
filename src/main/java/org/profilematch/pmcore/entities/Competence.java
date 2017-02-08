package org.profilematch.pmcore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author antoine
 */
@Entity
@NamedQuery(name = "competence.startsby", query = "SELECT DISTINCT c.competence FROM Competence c WHERE c.competence LIKE :debut_comp")
public class Competence implements Serializable {

    @JsonIgnore
    private Long id_comp;

    private String competence;

    private List<Candidat> candidat = new ArrayList<>(0);

    public Competence() {

    }

    public Competence(String c) {
        this.competence = c;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "competence")
    @JsonIgnore
    public List<Candidat> getCandidat() {
        return candidat;
    }

    public void setCandidat(List<Candidat> candidat) {
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

    public String getCompetence() {
        return competence;
    }

    public void setCompetence(String competences) {
        this.competence = competences;
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
