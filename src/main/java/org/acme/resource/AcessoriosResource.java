package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

import org.acme.dto.AcessorioResponseDTO;
import org.acme.dto.AcessoriosDTO;
import org.acme.service.AcessoriosService;

@Path("Acessorio")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AcessoriosResource {

    @Inject
    AcessoriosService acessoriosService;

    @GET
    @Path("/buscarTodos")
    public List<AcessorioResponseDTO> buscarTodos(@QueryParam("page") @DefaultValue("0") int page, @QueryParam("pageSize") @DefaultValue("100") int pageSize) { 
        return acessoriosService.findAll(page, pageSize);
    }

    @GET
    @Path("/search")
    public List<AcessorioResponseDTO> search(@QueryParam("query") String query, @QueryParam("page") @DefaultValue("0") int page, @QueryParam("pageSize") @DefaultValue("100") int pageSize) {
        return acessoriosService.search(query, page, pageSize);
    }

    @GET
    @Path("/count")
    public Long total() {
        return acessoriosService.count();
    }

    @GET
    @Path("/{id}")
    public AcessorioResponseDTO findById(@PathParam("id") long id) {
        return acessoriosService.findById(id);
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
