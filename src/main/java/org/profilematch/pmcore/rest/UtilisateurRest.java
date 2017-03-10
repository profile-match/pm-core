package org.profilematch.pmcore.rest;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.profilematch.pmcore.ejbs.CandidatEJB;
import org.profilematch.pmcore.ejbs.DossierPosteBeanLocal;

import org.profilematch.pmcore.ejbs.UtilisateurEJB;
import org.profilematch.pmcore.entities.Candidat;
import org.profilematch.pmcore.entities.Recruteur;
import org.profilematch.pmcore.entities.Utilisateur;

/**
 * @author Lucas
 */
@Path("utilisateur/")
public class UtilisateurRest {

    @EJB
    private UtilisateurEJB ue;
    
    @EJB
    private CandidatEJB cand;
    
    @Inject
    DossierPosteBeanLocal rec;

    @GET
    @Path("get/{email}")
    @Produces("application/json")
    public Response GetUtilisateur(@PathParam("email") String email) {
        return Response.ok(ue.getUtilisateur(email)).build();
    }

    @POST
    @Consumes("application/json")
    @Path("inscrireCand/")
    public Response InscrireUtilisateurCand(Utilisateur u) {
        Candidat c = new Candidat();
        c.setEmail(u.getEmail());
        cand.registerUser(c);
        System.out.println("id Cand :"+c.getId());
        u.setId(c.getId());
     
        ue.inscrireUtilisateur(u);
        return Response.ok().build();
    }
    
    @POST
    @Consumes("application/json")
    @Path("inscrireRec/")
    public Response InscrireUtilisateurRec(Utilisateur u) {
        Recruteur r = new Recruteur();        
        r.setEmail(u.getEmail());
        rec.registerUser(r);
        
        u.setId(r.getId());
        
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
