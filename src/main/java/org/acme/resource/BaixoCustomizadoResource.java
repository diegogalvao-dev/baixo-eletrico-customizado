package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

import org.acme.dto.BaixoCustomizadoDTO;
import org.acme.dto.BaixoCustomizadoResponseDTO;
import org.acme.service.BaixoCustomizadoService;

@Path("baixo-customizado")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BaixoCustomizadoResource {

    @Inject
    BaixoCustomizadoService baixoCustomizadoService;

    @GET
    @Path("/buscarTodos")
    public List<BaixoCustomizadoResponseDTO> buscarTodos(@QueryParam("page") @DefaultValue("0") int page, @QueryParam("pageSize") @DefaultValue("100") int pageSize) { 
        return baixoCustomizadoService.findAll(page, pageSize);
    }

    @GET
    @Path("/search")
    public List<BaixoCustomizadoResponseDTO> search(@QueryParam("query") String query, @QueryParam("page") @DefaultValue("0") int page, @QueryParam("pageSize") @DefaultValue("100") int pageSize) {
        return baixoCustomizadoService.search(query, page, pageSize);
    }

    @GET
    @Path("/count")
    public Long total() {
        return baixoCustomizadoService.count();
    }

    @GET
    @Path("/{id}")
    public BaixoCustomizadoResponseDTO findById(@PathParam("id") long id) {
        return baixoCustomizadoService.findById(id);
    }

    @POST
    public Response create(BaixoCustomizadoDTO dto){
        return Response.status(Response.Status.CREATED).entity(baixoCustomizadoService.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(long id, BaixoCustomizadoDTO dto){
        baixoCustomizadoService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(long id){
        baixoCustomizadoService.delete(id);
        return Response.noContent().build();
    }

}