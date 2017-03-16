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
import org.profilematch.pmcore.config.AutoConfig;
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
 * 
 * Classe web service permettant d'accedez à l'API Linkedin
 * dans le but de récupérer le mail de l'utilisateur, il permet
 * aussi à l'utilisateur de se connecter à IMatchProfile
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
     * Constructeur de la classe
     */
    public LinkedinRest() {
    }

    /**
     * Méthode permettant de récuperer le mail de l'utilisateur via l'API Linkedin et de l'inscrire
     * @param code code pour l'authentification OAuth2.
     * @param state state pour vérifier que la requete correspond bien à celle que l'on à faite coté front. 
     * @param genre permet de savoir si l'utilisateur est une candidat ou un recruteur.
     * @return retourne l'ID de l'utilisateur ou -1 si il n'a été possible d'ajouter le nouvelle utilisateur.
     */
    @GET
    @Path("gettoken/{code}/{state}/{genre}")
    @Consumes("text/plain")
    @Produces("text/plain")
    public Response inscriptionLinkedin(@PathParam("code") String code, @PathParam("state") String state, @PathParam("genre") String genre){
        String rep = "-1";
        String front = "http%3A%2F%2Flocalhost%3A4200%2F";
        if(AutoConfig.getProd()){
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
                            user.setType("candidat");
                            user.setId(cand.getId());
                        }else{
                            Recruteur rec = new Recruteur();
                            rec.setEmail(email);
                            re.registerUser(rec);
                            user.setType("recruteur");
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
    
     /**
     * Méthode permettant de récupérer l'email de l'utilisateur et de le connecter.
     * @param code code pour l'authentification OAuth2.
     * @param state state pour vérifier que la requete correspond bien à celle que l'on à faite coté front. 
     * @return retourne un objet JSON contenant l'ID de l'utilisateur et son type (candidat/recruteur).
     */
    @GET
    @Path("connexion/{code}/{state}")
    @Consumes("text/plain")
    @Produces("application/json")
    public Response connexionLinkedin(@PathParam("code") String code, @PathParam("state") String state){
        String rep = "{\"id\":\"-1\", \"genre\":\"undefined\"}";
        String front = "http%3A%2F%2Flocalhost%3A4200%2F";
        if(AutoConfig.getProd()){
            front = "https%3A%2F%2Fimp.bober.ovh%2F";
        }
        String url = "https://www.linkedin.com/oauth/v2/accessToken?" +
            "grant_type=authorization_code&" +
            "code=" + code + "&" +
            "redirect_uri="+front+"connexion-linkedin&" +
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
                    Utilisateur u = ue.getUtilisateurByEmail(email);
                    System.out.println(u.getId());
                    if(u.getId() != -1){
                        rep = "{"
                            + "\"id\":\""+u.getId()+"\","
                            + "\"genre\":\""+u.getType()+"\""
                            + "}";
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
