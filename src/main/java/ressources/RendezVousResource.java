package ressources;


import entities.Logement;
import entities.RendezVous;
import metiers.RendezVousBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rendezvous")
public class RendezVousResource {
    RendezVousBusiness rendezVousBusiness;

    public RendezVousResource() {
        this.rendezVousBusiness = new RendezVousBusiness();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRendezVous(RendezVous rendezVous) {
        rendezVousBusiness.addRendezVous(rendezVous);
        return Response.ok(rendezVousBusiness.getListeRendezVous()).build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response ListRendezVous(@QueryParam("refLogement") int refLogement) {
        if (refLogement != 0) {
            return Response.ok(rendezVousBusiness.getListeRendezVousByLogementReference(refLogement)).build();
        }
        return Response.ok(rendezVousBusiness.getListeRendezVous()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{referance}")
    public Response rendezvousById(@PathParam("referance") int ref) {
        return Response.ok(rendezVousBusiness.getRendezVousById(ref)).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{referance}")
    public Response delete(@PathParam("referance") int ref) {
        rendezVousBusiness.deleteRendezVous(ref);
        return Response.ok(rendezVousBusiness.getListeRendezVous()).build();
    }

    @PUT
    @Path("/{reference}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLogement(@PathParam("reference") int reference, RendezVous rendezVous) {
        rendezVousBusiness.updateRendezVous(reference,rendezVous);
        return Response.ok(rendezVousBusiness.getRendezVousById(reference)).build();
    }
}
