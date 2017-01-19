package org.profilematch.pmcore.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.profilematch.pmcore.ejbs.DossierPosteBeanLocal;
import org.profilematch.pmcore.entities.Dossier_poste;


@Path("/recruteur")
public class HelloWorldEndpoint {

    @Inject
    DossierPosteBeanLocal ce;
    
	@GET
	@Produces("text/plain")
	public Response doGet() throws InterruptedException {
          
		return Response.ok(" gazejalzjelazkjealzkejfrom WildFrzerly Swarm!").build();
	}
        
    //Methode put modification dossier
        
        
    //Methode get savoir être faire specifique
    
    //Methode get Info all dossier
    @GET
    @Path("ElementAllDossier")
    @Produces("application/json")
    public Response ElementAllDossier(){
        return Response.ok(ce.getElementAllDossier()).build();
        
    }
    //Methode get ALL intitule de dossier
    @GET
    @Path("intituleAllDossier")
    @Produces("application/json")
    public Response intituleAllDossier(){
        return Response.ok(ce.getIntituleAllDossier()).build();
        
    }
    //Methode Delete dossier_poste
    @DELETE
    @Path("deleteDossier/{id}")
    @Consumes("application/json")
    public Response deleteDossier(@PathParam("id") Long id){
        
        return Response.ok(ce.deleteDossier(id)).build();
    }
    
    @POST
    @Consumes("application/json")
    @Path("create")
    public Response RegisterCandidat(Dossier_poste c) {
        return Response.ok(ce.createDossier(c)).build();
    }
    
        
}