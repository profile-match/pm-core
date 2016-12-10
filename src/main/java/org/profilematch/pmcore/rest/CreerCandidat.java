package org.profilematch.pmcore.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import org.profilematch.pmcore.ejbs.CandidatEJB;
import org.profilematch.pmcore.entities.Candidat;

/**
 *
 * @author antoine
 */
@Path("candidat/create")
public class CreerCandidat {

    @EJB
    private CandidatEJB ce;

    /**
     *
     * @param c
     * @return
     */
    @POST
    @Consumes("application/json")
    public Response post(Candidat c) {
        return Response.ok(ce.registerUser(c)).build();
    }
    
}
