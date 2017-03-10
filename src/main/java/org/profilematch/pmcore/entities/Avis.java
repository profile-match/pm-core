package org.profilematch.pmcore.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Leriche Pierre
 */
@Entity
@Table(name = "AVIS")
public class Avis implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;

    public Avis(){
        
    }
    
    private String description;
    
    private int note;
    
    @ManyToOne
    private Candidat candidat;
    
    @ManyToOne
    private Recruteur recruteur;
    
    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }
    
    
    
}
