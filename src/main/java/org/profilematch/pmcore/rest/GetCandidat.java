package org.profilematch.pmcore.rest;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.profilematch.pmcore.ejbs.CandidatEJB;
import org.profilematch.pmcore.entities.Candidat;
import org.profilematch.pmcore.entities.Competence;

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
    public Response post(@PathParam("id") Long id) {
        return Response.ok(ce.getUser(id)).build();
    }

}
