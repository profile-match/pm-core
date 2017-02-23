package org.profilematch.pmcore.ejbs;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import org.apache.http.client.ClientProtocolException;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.json.JSONObject;
import org.profilematch.pmcore.entities.Utilisateur;

/**
 *
 * @author Lucas
 */
@Stateless
@LocalBean
public class LinkedInEJB {
    @EJB
    private UtilisateurEJB ue;
    public String inscriptionLinkedin(String code){
        String rep = "false";
        String url = "https://www.linkedin.com/oauth/v2/accessToken?" +
            "grant_type=authorization_code&" +
            "code=" + code + "&" +
            "redirect_uri=http%3A%2F%2Flocalhost%3A4200%2Finscription-linkedin&" +
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
                        ue.inscrireUtilisateur(user);
                        rep = "true";
                    }
                    return rep;
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rep;
    }
}
