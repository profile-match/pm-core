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
