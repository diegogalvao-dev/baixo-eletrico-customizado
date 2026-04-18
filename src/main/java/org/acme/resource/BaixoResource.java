package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

import org.acme.dto.BaixoDTO;
import org.acme.dto.BaixoResponseDTO;
import org.acme.service.BaixoService;

@Path("baixo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BaixoResource {

    @Inject
    BaixoService baixoService;

    @GET
    @Path("/buscarTodos")
    public List<BaixoResponseDTO> buscarTodos(@QueryParam("page") @DefaultValue("0") int page, @QueryParam("pageSize") @DefaultValue("100") int pageSize) { 
        return baixoService.findAll(page, pageSize);
    }

        @GET
    @Path("/search")
    public List<BaixoResponseDTO> search(@QueryParam("query") String query, @QueryParam("page") @DefaultValue("0") int page, @QueryParam("pageSize") @DefaultValue("100") int pageSize) {
        return baixoService.search(query, page, pageSize);
    }

    @GET
    @Path("/count")
    public Long total() {
        return baixoService.count();
    }

    @GET
    @Path("/{id}")
    public BaixoResponseDTO findById(@PathParam("id") long id) {
        return baixoService.findById(id);
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