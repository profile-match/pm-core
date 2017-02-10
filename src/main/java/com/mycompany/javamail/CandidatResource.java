/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.javamail;

import javax.mail.MessagingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author ILYASS
 */
@Path("candidat/")
public class CandidatResource {
    
    private SendMail sm;

    @POST
    @Path("sendinvitationmail/")
    @Consumes("application/json")
    public String SendInvitation(String email) throws MessagingException {
        sm.generateAndSendEmail(email);
        return "Invitation Envoyé";
    }
    

    
}
