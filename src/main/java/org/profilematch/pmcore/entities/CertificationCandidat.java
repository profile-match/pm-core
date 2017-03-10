package org.profilematch.pmcore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by antoine on 3/7/17.
 */

@Entity
public class CertificationCandidat  implements Serializable {

    @JsonIgnore
    private Long id_certif;

    private String certification;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id_certif;
    }

    public void setId(Long id){
        this.id_certif = id;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }
}
