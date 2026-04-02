package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.acme.dto.BaixoDTO;
import org.acme.service.BaixoService;

@Path("baixo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BaixoResource {

    @Inject
    BaixoService baixoService;

    @GET
    public Response findAll(){
        return Response.ok().entity(baixoService.findAll()).build();
    }

    @POST
    public Response create(BaixoDTO dto){
        return Response.status(Response.Status.CREATED).entity(baixoService.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(long id, BaixoDTO dto){
        baixoService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(long id){
        baixoService.delete(id);
        return Response.noContent().build();
    }

}