package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.acme.dto.CaptadorAtivoDTO;
import org.acme.service.CaptadorAtivoService;

@Path("captador-ativo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CaptadorAtivoResource {

    @Inject
    CaptadorAtivoService captadorAtivoService;

    @GET
    public Response findAll(){
        return Response.ok().entity(captadorAtivoService.findAll()).build();
    }

    @POST
    public Response create(CaptadorAtivoDTO dto){
        return Response.status(Response.Status.CREATED).entity(captadorAtivoService.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(long id, CaptadorAtivoDTO dto){
        captadorAtivoService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(long id){
        captadorAtivoService.delete(id);
        return Response.noContent().build();
    }

}