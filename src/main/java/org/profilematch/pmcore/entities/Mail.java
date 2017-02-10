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
public class Mail {
    
    private String email;
    
    public Mail(){
       
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String toString(){
       
       return "{mail = " + email + " }"; 
    }
    
    
}
