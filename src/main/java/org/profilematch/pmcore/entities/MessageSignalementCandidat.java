package org.profilematch.pmcore.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by antoine on 1/19/17.
 */
@Entity
@NamedQuery(name="MessageSignalementCandidat.findAll", query="SELECT m FROM MessageSignalementCandidat m")
public class MessageSignalementCandidat implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String titre, description;
    private int idCandidat;
    private int idReported;

    public MessageSignalementCandidat(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdCandidat() {
        return idCandidat;
    }

    public void setIdCandidat(int idCandidat) {
        this.idCandidat = idCandidat;
    }

    public int getIdReported() {
        return idReported;
    }

    public void setIdReported(int idReported) {
        this.idReported = idReported;
    }
}