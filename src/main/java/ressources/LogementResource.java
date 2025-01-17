package ressources;


import entities.Logement;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/logements")
public class LogementResource {
    LogementBusiness logementBusiness ;

    public LogementResource() {
        this.logementBusiness = new LogementBusiness();
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addLogement(Logement logement){
        logementBusiness.addLogement(logement);
        return Response.ok(logementBusiness.getLogements()).build();
    }



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByDelegation(@QueryParam("delegation") String delegation,@QueryParam("referance") int ref) {
        if (ref != 0) {
            return Response.ok(logementBusiness.getLogementsByReference(ref)).build();
        }
        if (delegation == null) {
            return Response.ok(logementBusiness.getLogements()).build();
        }
        return Response.ok(logementBusiness.getLogementsByDeleguation(delegation)).build();
    }
        @DELETE
        @Path("/{reference}")
        @Produces(MediaType.APPLICATION_JSON)
    public Response deleteLogement(@PathParam("reference") int reference) {
        logementBusiness.deleteLogement(reference);
        return Response.ok(logementBusiness.getLogements()).build();
    }
    @PUT
    @Path("/{reference}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLogement(@PathParam("reference") int reference, Logement logement) {
        logementBusiness.updateLogement(reference,logement);
        return Response.ok(logementBusiness.getLogements()).build();
    }

}
