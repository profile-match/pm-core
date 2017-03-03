package org.profilematch.pmcore.rest;

import org.profilematch.pmcore.ejbs.Matcher;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Created by antoine on 3/3/17.
 */

@Path("match/")
public class MatchingRest {

    @EJB
    private Matcher M;

    @GET
    @Path("get/")
    @Produces("application/json")
    public Response GetCandidat() {

        return Response.ok(M.match(1L)).build();
    }
}
