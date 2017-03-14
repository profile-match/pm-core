package org.profilematch.pmcore.rest;

import org.profilematch.pmcore.ejbs.Matcher;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
/**
 * Created by antoine on 3/3/17.
 */

@Path("match/")
public class MatchingRest {

    @EJB
    private Matcher M;

    @GET
    @Path("dossierCandidatCert/{idDossier}/{idCandidat}")
    @Produces("application/json")
    public Response getMatchingDossierCert(@PathParam("idDossier") Long idDossier, @PathParam("idCandidat") Long idCandidat) {
        return Response.ok(M.matchCandidatCert(idDossier, idCandidat)).build();
    }
    
    @GET
    @Path("dossierCandidatComp/{idDossier}/{idCandidat}")
    @Produces("application/json")
    public Response getMatchingDossierComp(@PathParam("idDossier") Long idDossier, @PathParam("idCandidat") Long idCandidat) {
        return Response.ok(M.matchCandidatComp(idDossier, idCandidat)).build();
    }
    
    @GET
    @Path("dossierCandidatForm/{idDossier}/{idCandidat}")
    @Produces("application/json")
    public Response getMatchingDossierForm(@PathParam("idDossier") Long idDossier, @PathParam("idCandidat") Long idCandidat) {
        return Response.ok(M.matchCandidatForm(idDossier, idCandidat)).build();
    }
    

    @GET
    @Path("dossier/{idDossier}/{borneInf}/{borneSup}")
    @Produces("application/json")
    public Response getMatching(@PathParam("idDossier") Long idDossier,@PathParam("borneInf") int borneInf,@PathParam("borneSup") int borneSup) {
        return Response.ok(M.match(idDossier, borneInf, borneSup)).build();
    }

}
