/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.profilematch.pmcore.entities;

/**
 *
 * @author geoffrey
 */
public class RecruteurMDP {

    private String email;
    private String oldMDP;
    private String newMDP;

    public RecruteurMDP() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOldMDP() {
        return oldMDP;
    }

    public void setOldMDP(String oldMDP) {
        this.oldMDP = oldMDP;
    }

    public String getNewMDP() {
        return newMDP;
    }

    public void setNewMDP(String newMDP) {
        this.newMDP = newMDP;
    }

}
