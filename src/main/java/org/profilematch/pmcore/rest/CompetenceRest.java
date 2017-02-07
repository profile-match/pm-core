package org.profilematch.pmcore.rest;

import org.profilematch.pmcore.ejbs.CandidatEJB;

import javax.ws.rs.Path;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Created by antoine on 07/02/17.
 */
@Path("competence/")
public class CompetenceRest {

    @EJB
    private CandidatEJB ce;

    @GET
    @Produces("application/json")
    @Path("get/{s}")
    public Response GetAllClient(@PathParam("s") String s) {
        System.out.println(ce.getCompetencesStartBy(s));
        return Response.ok(ce.getCompetencesStartBy(s)).build();
    }

}
