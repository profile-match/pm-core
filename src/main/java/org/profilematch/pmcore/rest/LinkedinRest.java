/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.profilematch.pmcore.rest;
import org.json.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import org.profilematch.pmcore.entities.Utilisateur;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import javax.ejb.EJB;
import org.profilematch.pmcore.ejbs.UtilisateurEJB;
import org.profilematch.pmcore.entities.Utilisateur;
/**
 * REST Web Service
 *
 * @author Franck
 */
@Path("generic")
public class LinkedinRest {

    @EJB
    private UtilisateurEJB ue;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of LinkedinRest
     */
    public LinkedinRest() {
    }

 @GET
    @Path("gettoken/{code}/{state}")
    @Consumes("text/plain")
    public String GetCandidat(@PathParam("code") String code, @PathParam("state") String state) {
        
        String rep = "false";
        String url = "https://www.linkedin.com/oauth/v2/accessToken?" +
            "grant_type=authorization_code&" +
            "code=" + code + "&" +
            "redirect_uri=http%3A%2F%2Flocalhost%3A4200%2Fhome%2Finscription-linkedin&" +
            "client_id=7868doeuipinun&" +
            "client_secret=2HdM9JPydxMUsGWH";
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(UriBuilder.fromUri(url).build());
        String response = service
                        .get(String.class);
        
        
        
        JSONObject obj = new JSONObject(response);
        String token = obj.getString("access_token");
        
        url = "https://api.linkedin.com/v1/people/~:(email-address)?format=json";
        WebResource service2 = client.resource(UriBuilder.fromUri(url).build());
        String response2 = service2
                        .header("Authorization", "Bearer "+token)
                        .get(String.class);
        
        
        JSONObject obj2 = new JSONObject(response2);
        String email = obj2.getString("emailAddress");
        Utilisateur user = new Utilisateur(email, token);
        //Utilisateur u = ue.getUtilisateur(email);
        Utilisateur u = ue.getUtilisateurByEmail(email);
        if(u.getId() == -1){
            ue.inscrireUtilisateur(user);
            rep = "true";
        }
        return rep;

//return Response.ok(ce.getUser(id)).build();
    }
}
