/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.profilematch.pmcore.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.profilematch.pmcore.ejbs.CandidatEJB;
import org.profilematch.pmcore.entities.Candidat;

/**
 *
 * @author antoine
 */
@Path("candidat/get")
public class GetCandidat {

    @EJB
    private CandidatEJB ce;

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Candidat post(@PathParam("id") Long id) {
        return ce.getUser(id);
    }

    @GET
    @Produces("text/plain")
    public Response get() {
        return Response.ok("Bonjour").build();
    }
}
