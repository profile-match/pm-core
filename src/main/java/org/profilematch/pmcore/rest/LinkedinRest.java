/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.profilematch.pmcore.rest;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.apache.http.client.ClientProtocolException;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.json.JSONObject;
import org.profilematch.pmcore.config.IO;
import org.profilematch.pmcore.ejbs.CandidatEJB;
import org.profilematch.pmcore.ejbs.DossierPosteBeanLocal;
import org.profilematch.pmcore.ejbs.UtilisateurEJB;
import org.profilematch.pmcore.entities.Candidat;
import org.profilematch.pmcore.entities.Recruteur;
import org.profilematch.pmcore.entities.Utilisateur;
/**
 * REST Web Service
 *
 * @author Franck
 */
@Path("linkedin/")
public class LinkedinRest {

    @EJB
    private UtilisateurEJB ue;
    @EJB
    private CandidatEJB ce;
    @Inject
    private DossierPosteBeanLocal re;
    
    
    /**
     * Creates a new instance of LinkedinRest
     */
    public LinkedinRest() {
    }

    @GET
    @Path("gettoken/{code}/{state}/{genre}")
    @Consumes("text/plain")
    @Produces("text/plain")
    public Response GetCandidat(@PathParam("code") String code, @PathParam("state") String state, @PathParam("genre") String genre){
        String rep = "-1";
        String front = "http%3A%2F%2Flocalhost%3A4200%2F";
        if(IO.getProd()){
            front = "https%3A%2F%2Fimp.bober.ovh%2F";
        }
        String url = "https://www.linkedin.com/oauth/v2/accessToken?" +
            "grant_type=authorization_code&" +
            "code=" + code + "&" +
            "redirect_uri="+front+"inscription-linkedin?genre="+genre+"&" +
            "client_id=7868doeuipinun&" +
            "client_secret=2HdM9JPydxMUsGWH";
        try {
            ClientRequest request = new ClientRequest(url);
            request.accept("application/json");
            ClientResponse<String> response = request.get(String.class);
            if (response.getStatus() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                    new ByteArrayInputStream(response.getEntity().getBytes())));
                String output;
                StringBuilder sbRep = new StringBuilder();
                while ((output = br.readLine()) != null) {
                    sbRep.append(output);
                }
                
                JSONObject obj = new JSONObject(sbRep.toString());
                String token = obj.getString("access_token");
                url = "https://api.linkedin.com/v1/people/~:(email-address)?format=json";
                
                request = new ClientRequest(url);
                request.accept("application/json");
                response = request.header("Authorization", "Bearer "+token).get(String.class);
                
                if (response.getStatus() == 200) {
                    br = new BufferedReader(new InputStreamReader(
                        new ByteArrayInputStream(response.getEntity().getBytes())));
                    sbRep = new StringBuilder();
                    while ((output = br.readLine()) != null) {
                        sbRep.append(output);
                    }
                    JSONObject obj2 = new JSONObject(sbRep.toString());
                    String email = obj2.getString("emailAddress");
                    Utilisateur user = new Utilisateur(email, token);
                    //Utilisateur u = ue.getUtilisateur(email);
                    Utilisateur u = ue.getUtilisateurByEmail(email);
                    if(u.getId() == -1){
                        if(genre.equals("C")){
                            Candidat cand = new Candidat();
                            cand.setEmail(email);
                            ce.registerUser(cand);
                            user.setId(cand.getId());
                        }else{
                            Recruteur rec = new Recruteur();
                            rec.setEmail(email);
                            re.registerUser(rec);
                            user.setId(rec.getId());
                        }
                        ue.inscrireUtilisateur(user);
                        rep = ""+user.getId();
                    }else{
                        rep = ""+u.getId();
                    }
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.ok(rep).build();
    }
}
