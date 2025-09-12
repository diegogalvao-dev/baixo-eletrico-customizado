package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.CaptadoresDTO;
import org.acme.service.CaptadoresService;

@Path("Captadores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CaptadoresResource {

    @Inject
    CaptadoresService captadoresService;

    @GET
    public Response findAll(){
        return Response.ok().entity(captadoresService.findAll()).build();
    }

    @POST
    public Response create(CaptadoresDTO dto){
        return Response.status(Response.Status.CREATED).entity(captadoresService.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(long id, CaptadoresDTO dto){
        captadoresService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(long id){
        captadoresService.delete(id);
        return Response.noContent().build();
    }

}
