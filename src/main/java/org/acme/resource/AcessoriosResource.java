package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.AcessoriosDTO;
import org.acme.service.AcessoriosService;

@Path("Acessorio")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AcessoriosResource {

    @Inject
    AcessoriosService acessoriosService;

    @GET
    public Response findAll(){
        return Response.ok().entity(acessoriosService.findAll()).build();
    }

    @POST
    public Response create(AcessoriosDTO dto){
        return Response.status(Response.Status.CREATED).entity(acessoriosService.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(long id, AcessoriosDTO dto){
        acessoriosService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(long id){
        acessoriosService.delete(id);
        return Response.noContent().build();
    }

}
