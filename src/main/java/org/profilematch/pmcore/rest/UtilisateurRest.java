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

import org.profilematch.pmcore.ejbs.UtilisateurEJB;
import org.profilematch.pmcore.entities.Utilisateur;

/**
 * @author Lucas
 */
@Path("utilisateur/")
public class UtilisateurRest {

    @EJB
    private UtilisateurEJB ue;

    @GET
    @Path("get/{email}")
    @Produces("application/json")
    public Response GetUtilisateur(@PathParam("email") String email) {
        return Response.ok(ue.getUtilisateur(email)).build();
    }

    @POST
    @Consumes("application/json")
    @Path("inscrire/")
    public Response InscrireUtilisateur(Utilisateur u) {
        ue.inscrireUtilisateur(u);
        return Response.ok().build();
    }

    @PUT
    @Consumes("application/json")
    @Path("modifier/")
    public Response ModifierUtilisateur(Utilisateur u) {
        ue.modifierUtilisateur(u);
        return Response.ok().build();
    }

    @GET
    @Path("get")
    @Produces("application/json")
    public Response GetUtilisateurs() {
        return Response.ok(ue.getUtilisateurs()).build();
    }

}
