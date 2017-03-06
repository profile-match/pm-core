package org.profilematch.pmcore.rest;

import org.profilematch.pmcore.config.FileUploadForm;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimetypesFileTypeMap;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import org.profilematch.pmcore.ejbs.CandidatEJB;
import org.profilematch.pmcore.ejbs.CvEJB;
import org.profilematch.pmcore.entities.Candidat;
import org.profilematch.pmcore.entities.MessageSignalementCandidat;

/**
 * @author antoine
 */
@Path("candidat/")
public class CandidatRest {

    @EJB
    private CandidatEJB ce;

    @EJB
    private CvEJB cv;

    @GET
    @Path("cv/{id}")
    @Produces("application/pdf")
    public Response getCV(@PathParam("id") Long id) {
        File f = cv.getCV(id);
        String mt = new MimetypesFileTypeMap().getContentType(f);
        return Response.ok(f, mt).build();
    }

    @GET
    @Path("get/{id}")
    @Produces("application/json")
    public Response GetCandidat(@PathParam("id") Long id) {
        return Response.ok(ce.getUser(id)).build();
    }

    @GET
    @Path("getnbmale/")
    @Produces("application/json")
    public Response GetNbMale() {
        return Response.ok(ce.getNbMale()).build();
    }

    @GET
    @Path("getnbfemelle/")
    @Produces("application/json")
    public Response GetNbFemelle() {
        return Response.ok(ce.getNbFemelle()).build();
    }

    @DELETE
    @Path("delete/{id}")
    public Response DeleteCandidat(@PathParam("id") Long id) {
        ce.deleteCandidat(id);
        return Response.ok().build();
    }

    @POST
    @Consumes("application/json")
    @Path("create/")
    public Response RegisterCandidat(Candidat c) {
        return Response.ok(ce.registerUser(c)).build();
    }

    @PUT
    @Consumes("application/json")
    @Path("update/")
    public Response UpdateCandidat(Candidat c) {
        return Response.ok(ce.updateUser(c)).build();
    }

    @PUT
    @Produces("application/json")
    @Path("ban/{id}")
    public Response UpdateBanCandidat(@PathParam("id") Long id) {
        return Response.ok(ce.BanUser(id)).build();
    }

    @PUT
    @Produces("application/json")
    @Path("unban/{id}")
    public Response UpdateUnbanCandidat(@PathParam("id") Long id) {
        return Response.ok(ce.UnbanUser(id)).build();
    }

    @GET
    @Produces("application/json")
    @Path("suspend/{id}")
    public Response SuspsendCandidat(@PathParam("id") Long id) {
        return Response.ok(ce.SuspendUser(id)).build();
    }

    @GET
    @Produces("application/json")
    @Path("unsuspend/{id}")
    public Response UnSuspsendCandidat(@PathParam("id") Long id) {
        return Response.ok(ce.UnSuspendUser(id)).build();
    }

    @GET
    @Path("get")
    @Produces("application/json")
    public Response GetAllClient() {
        return Response.ok(ce.getAllUser()).build();
    }

    @POST
    @Consumes("application/json")
    @Path("messagesignalementcandidat/")
    public Response postMessageSignalementCandidat(MessageSignalementCandidat m) {
        ce.postMessageSignalementCandidat(m);
        return Response.ok().build();
    }

    @GET
    @Produces("image/*")
    @Path("photo/{id}")
    public Response getPhoto(@PathParam("id") Long id) {
        File f = new File("images/" + id);
        String mt = new MimetypesFileTypeMap().getContentType(f);
        return Response.ok(f,mt).build();
    }

    @POST
    @Path("photo")
    @Consumes("multipart/form-data")
    public Response uploadFile(@MultipartForm FileUploadForm form) {

        int file = Arrays.hashCode(form.getFileData());

        try {
            FileOutputStream fos = new FileOutputStream("images/" + file);
            fos.write(form.getFileData());
            fos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CandidatRest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CandidatRest.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Response.ok("" + file).build();
    }
}
