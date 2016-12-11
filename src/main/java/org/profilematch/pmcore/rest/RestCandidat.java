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
@Path("candidat/")
public class RestCandidat {

    @EJB
    private CandidatEJB ce;

    @GET
    @Path("get/{id}")
    @Produces("application/json")
    public Response GetCandidat(@PathParam("id") Long id) {
        return Response.ok(ce.getUser(id)).build();
    }

    @POST
    @Consumes("application/json")
    @Path("create/")
    public Response RegisterCandidat(Candidat c) {
        return Response.ok(ce.registerUser(c)).build();
    }

    @POST
    @Consumes("application/json")
    @Path("update/")
    public Response UpdateCandidat(Candidat c) {
        return Response.ok(ce.updateUser(c)).build();
    }

}
