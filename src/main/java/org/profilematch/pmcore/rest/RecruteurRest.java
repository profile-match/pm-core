package org.profilematch.pmcore.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;
import org.profilematch.pmcore.ejbs.DossierPosteBeanLocal;
import org.profilematch.pmcore.entities.Avis;
import org.profilematch.pmcore.entities.Dossier_poste;
import org.profilematch.pmcore.entities.Recruteur;
import org.profilematch.pmcore.entities.RecruteurMDP;

@Path("/recruteur")
public class RecruteurRest {

    @Inject
    DossierPosteBeanLocal ce;

    @GET
    @Produces("text/plain")
    public Response doGet() throws InterruptedException {

        return Response.ok(" gazejalzjelazkjealzkejfrom WildFrzerly Swarm!").build();
    }

    //Methode put modification dossier
    @PUT
    @Path("updateDossier")
    @Consumes("application/json")
    public Response updateDossier(Dossier_poste c) {

        return Response.ok(ce.updateDossier(c)).build();
    }

    //Methode get metier technique
    @GET
    @Path("completeMetier/{complete}")
    @Produces("application/json")
    public Response completeMetier(@PathParam("complete") String c) {
        return Response.ok(ce.completeMetier(c)).build();
    }

    @GET
    @Path("completeTechnique/{complete}")
    @Produces("application/json")
    public Response completeTechnique(@PathParam("complete") String c) {
        return Response.ok(ce.completeTechnique(c)).build();
    }

    @GET
    @Path("completeLangue/{complete}")
    @Produces("application/json")
    public Response completeLangue(@PathParam("complete") String c) {
        return Response.ok(ce.completeLinguistique(c)).build();
    }

    @GET
    @Path("completeFormation/{complete}")
    @Produces("application/json")
    public Response completeFormation(@PathParam("complete") String c) {
        return Response.ok(ce.completeFormation(c)).build();
    }

    @GET
    @Path("completeCertification/{complete}")
    @Produces("application/json")
    public Response completeCertification(@PathParam("complete") String c) {
        return Response.ok(ce.completeCertification(c)).build();
    }

    @GET
    @Path("completeFonctionnelle/{complete}")
    @Produces("application/json")
    public Response completeFonctionnelle(@PathParam("complete") String c) {
        return Response.ok(ce.completeFonctionnelle(c)).build();
    }

    //Methode get Info all dossier
    @GET
    @Path("ElementAllDossier/{id}")
    @Produces("application/json")
    public Response ElementAllDossier(@PathParam("id") int id) {
        return Response.ok(ce.getElementAllDossier(id)).build();

    }

    //Methode get ALL intitule de dossier
    @GET
    @Path("intituleAllDossier/{id}")
    @Produces("application/json")
    public Response intituleAllDossier(@PathParam("id") int id) {
        return Response.ok(ce.getIntituleAllDossier(id)).build();

    }

    @GET
    @Path("allDossier")
    @Produces("application/json")
    public Response allDossier() {
        return Response.ok(ce.getAllPostes()).build();
    }

    @GET
    @Path("dossierRecruteur/{id}")
    @Produces("application/json")
    public Response allDossier(@PathParam("id") int id) {
        return Response.ok(ce.getAllDossier(id)).build();
    }

    @GET
    @Path("dossier/{id}")
    @Produces("application/json")
    public Response Dossier(@PathParam("id") Long id) {
        return Response.ok(ce.getDossier(id)).build();

    }

    //Methode Delete dossier_poste
    @DELETE
    @Path("deleteDossier/{id}")
    @Consumes("application/json")
    public Response deleteDossier(@PathParam("id") Long id) {

        return Response.ok(ce.deleteDossier(id)).build();

    }

    @POST
    @Consumes("application/json")
    @Path("create")
    public Response createDossier(Dossier_poste c) {
        return Response.ok(ce.createDossier(c)).build();
    }

    @GET
    @Path("get/{id}")
    @Produces("application/json")
    public Response GetRecruteur(@PathParam("id") Long id) {
        return Response.ok(ce.getUser(id)).build();
    }

    @GET
    @Path("get")
    @Produces("application/json")
    public Response GetAllClient() {
        return Response.ok(ce.getAllUser()).build();
    }

    @PUT
    @Produces("application/json")
    @Path("ban/{id}")
    public Response UpdateBanRecruteur(@PathParam("id") Long id) {

        return Response.ok(ce.BanUser(id)).build();
    }

    @PUT
    @Produces("application/json")
    @Path("unban/{id}")
    public Response UpdateUnbanRecruteur(@PathParam("id") Long id) {
        return Response.ok(ce.UnbanUser(id)).build();
    }

    @POST
    @Produces("application/json")
    @Path("avis")
    public Response createAvis(Avis avis) {
        return Response.ok(ce.createAvis(avis)).build();
    }

    @PUT
    @Path("update")
    @Consumes("application/json")
    public Response updateRecruteur(Recruteur r) {

        boolean res = ce.updateRecruteur(r);
        if (res) {
            return Response.ok(res + " ").build();
        }
        return Response.status(201).build();

    }

    @Path("updateMdp")
    @Consumes("application/json")

    public Response updateMdpRecruteur(RecruteurMDP r) {

        int res = ce.updateMDPRecruteur(r);

        if (res == 0) {
            return Response.ok("le mot de passe a été modifié").build();
        } else if (res == -1) {
            return Response.status(201).entity("Une erreur c'est produite").build();
        } else {
            return Response.status(201).entity("ancien mot de passe incorrect").build();
        }

    }
}
