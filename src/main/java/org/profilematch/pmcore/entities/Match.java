package org.profilematch.pmcore.entities;

import java.io.Serializable;
/**
 *
 * @author SoPretty
 */

public class Match implements Serializable {

    private String Intitule;
    private boolean valide;
    private int obligatoire;
    
    public Match(String i, boolean v, int o){
        this.Intitule = i;
        this.valide = v;
        this.obligatoire = o;
    }

    public int getObligatoire() {
        return obligatoire;
    }

    public void setObligatoire(int obligatoire) {
        this.obligatoire = obligatoire;
    }

    public String getIntitule() {
        return Intitule;
    }

    public void setIntitule(String Intitule) {
        this.Intitule = Intitule;
    }

    public boolean isValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }

  



}
