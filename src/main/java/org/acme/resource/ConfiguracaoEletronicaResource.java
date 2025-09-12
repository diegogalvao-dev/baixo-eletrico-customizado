package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.ConfiguracaoEletronicaDTO;
import org.acme.service.ConfiguracaoEletronicaService;


@Path("ConfiguracaoEletronica")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConfiguracaoEletronicaResource {

    @Inject
    ConfiguracaoEletronicaService configuracaoEletronicasService;

    @GET
    public Response findAll(){
        return Response.ok().entity(configuracaoEletronicasService.findAll()).build();
    }

    @POST
    public Response create(ConfiguracaoEletronicaDTO dto){
        return Response.status(Response.Status.CREATED).entity(configuracaoEletronicasService.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(long id, ConfiguracaoEletronicaDTO dto){
        configuracaoEletronicasService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(long id){
        configuracaoEletronicasService.delete(id);
        return Response.noContent().build();
    }

}
