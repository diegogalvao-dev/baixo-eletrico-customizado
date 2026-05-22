package org.acme.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.acme.dto.ConfiguracaoEletronicaDTO;
import org.acme.service.ConfiguracaoEletronicaService;


@RolesAllowed("admin")
@Path("configuracao-eletronica")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConfiguracaoEletronicaResource {

    @Inject
    ConfiguracaoEletronicaService configuracaoEletronicaService;

    @GET
    public Response findAll(){
        return Response.ok().entity(configuracaoEletronicaService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") long id){
        return Response.ok().entity(configuracaoEletronicaService.findById(id)).build();
    }

    @POST
    public Response create(ConfiguracaoEletronicaDTO dto){
        return Response.status(Response.Status.CREATED).entity(configuracaoEletronicaService.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") long id, ConfiguracaoEletronicaDTO dto){
        return Response.ok().entity(configuracaoEletronicaService.update(id, dto)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long id){
        configuracaoEletronicaService.delete(id);
        return Response.noContent().build();
    }

}