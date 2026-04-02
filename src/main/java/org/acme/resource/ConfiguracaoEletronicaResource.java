package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.acme.dto.ConfiguracaoEletronicaDTO;
import org.acme.service.ConfiguracaoEletronicaService;

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

    @POST
    public Response create(ConfiguracaoEletronicaDTO dto){
        return Response.status(Response.Status.CREATED).entity(configuracaoEletronicaService.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(long id, ConfiguracaoEletronicaDTO dto){
        configuracaoEletronicaService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(long id){
        configuracaoEletronicaService.delete(id);
        return Response.noContent().build();
    }

}