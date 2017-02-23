/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.profilematch.pmcore.rest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import javax.ejb.EJB;
import org.profilematch.pmcore.ejbs.LinkedInEJB;
/**
 * REST Web Service
 *
 * @author Franck
 */
@Path("linkedin/")
public class LinkedinRest {

    @EJB
    private LinkedInEJB linkE;
    
    /**
     * Creates a new instance of LinkedinRest
     */
    public LinkedinRest() {
    }

    @GET
    @Path("gettoken/{code}/{state}")
    @Consumes("text/plain")
    public String GetCandidat(@PathParam("code") String code, @PathParam("state") String state) {
       return linkE.inscriptionLinkedin(code);
    }
}
