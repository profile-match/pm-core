package org.profilematch.pmcore.ejbs;

import java.io.Serializable;
import org.profilematch.pmcore.entities.Candidat;

/**
 * Created by antoine on 3/9/17.
 */
public class MatchedCandidat implements Comparable<MatchedCandidat>,Serializable {

    private Candidat candidat;

    private float obligatoire;
    private float plus;

    public MatchedCandidat(Candidat candidat, float obligatoire, float plus){
        this.candidat = candidat;
        this.obligatoire = obligatoire;
        this.plus = plus;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public float getObligatoire() {
        return obligatoire;
    }

    public void setObligatoire(float obligatoire) {
        this.obligatoire = obligatoire;
    }

    public float getPlus() {
        return plus;
    }

    public void setPlus(float plus) {
        this.plus = plus;
    }

    @Override
    public int compareTo(MatchedCandidat matchedCandidat) {
        if(this.getObligatoire() < matchedCandidat.getObligatoire())
            return -1;
        else if(this.getObligatoire() == matchedCandidat.getObligatoire())
            if(this.getPlus() < matchedCandidat.getPlus())
                return -1;
            else if (this.getPlus() == matchedCandidat.getPlus())
                return 0;
            else
                return 1;
        else
            return 1;
    }
}
