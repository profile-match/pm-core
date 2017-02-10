package org.profilematch.pmcore.rest;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.profilematch.pmcore.ejbs.Mail;
        
        
@Path("serviceMail/")
public class MailRest {
    
    @EJB
    private Mail mail;
    
    @POST
    @Path("envoyer")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response envoyerMail(org.profilematch.pmcore.entities.Mail mails) {
        String res ;
        try {
            res = mail.envoyer(mails); 
        } catch (Exception ex) {
            Logger.getLogger(MailRest.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(201).build();
        }
        return Response.ok(res).build();
    }
}
