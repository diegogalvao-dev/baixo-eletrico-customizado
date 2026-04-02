package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.acme.dto.CaptadorPassivoDTO;
import org.acme.service.CaptadorPassivoService;

@Path("captador-passivo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CaptadorPassivoResource {

    @Inject
    CaptadorPassivoService captadorPassivoService;

    @GET
    public Response findAll(){
        return Response.ok().entity(captadorPassivoService.findAll()).build();
    }

    @POST
    public Response create(CaptadorPassivoDTO dto){
        return Response.status(Response.Status.CREATED).entity(captadorPassivoService.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(long id, CaptadorPassivoDTO dto){
        captadorPassivoService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(long id){
        captadorPassivoService.delete(id);
        return Response.noContent().build();
    }

}