package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.BaixoCustomizadoDTO;
import org.acme.service.BaixoCustomizadoService;

@Path("Baixocustomizado")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BaixoCustomizadoResource {

    @Inject
    BaixoCustomizadoService baixocustomizadosService;

    @GET
    public Response findAll(){
        return Response.ok().entity(baixocustomizadosService.findAll()).build();
    }

    @POST
    public Response create(BaixoCustomizadoDTO dto){
        return Response.status(Response.Status.CREATED).entity(baixocustomizadosService.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(long id, BaixoCustomizadoDTO dto){
        baixocustomizadosService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(long id){
        baixocustomizadosService.delete(id);
        return Response.noContent().build();
    }

}
