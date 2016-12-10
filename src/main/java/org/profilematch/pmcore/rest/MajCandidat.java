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
@Path("candidat/update")
public class MajCandidat {

    @EJB
    private CandidatEJB ce;

    @POST
    @Consumes("application/json")
    public Response postCreer(Candidat c) {
        return Response.ok(ce.updateUser(c)).build();
    }
    
}
