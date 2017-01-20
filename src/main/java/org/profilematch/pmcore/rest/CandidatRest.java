package org.profilematch.pmcore.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.profilematch.pmcore.ejbs.CandidatEJB;
import org.profilematch.pmcore.entities.Candidat;
import org.profilematch.pmcore.entities.MessageSignalementCandidat;

/**
 * @author antoine
 */
@Path("candidat/")
public class CandidatRest {

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
        ce.registerUser(c);
        return Response.ok().build();
    }

    @PUT
    @Consumes("application/json")
    @Path("update/")
    public Response UpdateCandidat(Candidat c) {
        ce.updateUser(c);
        return Response.ok().build();
    }

    @PUT
    @Consumes("application/json")
    @Path("ban/{id}")
    public Response UpdateBanCandidat(@PathParam("id") Long id) {
        ce.BanUser(id);
        return Response.ok().build();
    }

    @PUT
    @Consumes("application/json")
    @Path("unban/{id}")
    public Response UpdateUnbanCandidat(@PathParam("id") Long id) {
        ce.UnbanUser(id);
        return Response.ok().build();
    }


    @GET
    @Path("get")
    @Produces("application/json")
    public Response GetAllClient() {
        return Response.ok(ce.getAllUser()).build();
    }
   
    @POST
    @Consumes("application/json")
    @Path("messagesignalementcandidat/")
    public Response postMessageSignalementCandidat(MessageSignalementCandidat m) {
        ce.postMessageSignalementCandidat(m);
        return Response.ok().build();
}

}
