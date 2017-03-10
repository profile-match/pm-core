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
    @Path("dossierCandidat/{idDossier}/{idCandidat}")
    @Produces("application/pdf")
    public Response getCV(@PathParam("idDossier") Long idDossier, @PathParam("idCandidat") Long idCandidat) {
        return Response.ok(M.matchCandidat(idDossier, idCandidat)).build();
    }

    @GET
    @Path("dossier/{idDossier}/{borneInf}/{borneSup}")
    @Produces("application/pdf")
    public Response getCV(@PathParam("idDossier") Long idDossier,@PathParam("borneInf") int borneInf,@PathParam("borneSup") int borneSup) {
        return Response.ok(M.match(idDossier, borneInf, borneSup)).build();
    }

}
