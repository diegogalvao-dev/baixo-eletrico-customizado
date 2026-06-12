package org.acme.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

import org.acme.dto.ProdutoDTO;
import org.acme.dto.ProdutoResponseDTO;
import org.acme.service.ProdutoService;

// @RolesAllowed("admin") 
@Path("produto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    @Inject
    ProdutoService produtoService;

    @GET
    @Path("/searchByPriceRange")
    public List<ProdutoResponseDTO> searchByPriceRange(
        @QueryParam("min") @DefaultValue("0") double min,
        @QueryParam("max") @DefaultValue("1000000") double max,
        @QueryParam("page") @DefaultValue("0") int page,
        @QueryParam("pageSize") @DefaultValue("100") int pageSize) {
        return produtoService.searchByPriceRange(min, max, page, pageSize);
    }

    @GET
    @Path("/buscarTodos")
    public List<ProdutoResponseDTO> buscarTodos(@QueryParam("page") @DefaultValue("0") int page, @QueryParam("pageSize") @DefaultValue("100") int pageSize) {
        return produtoService.findAll(page, pageSize);
    }

    @GET
    @Path("/search")
    public List<ProdutoResponseDTO> search(
        @QueryParam("query") String query,
        @QueryParam("min") Double min,
        @QueryParam("max") Double max,
        @QueryParam("sort") String sort,
        @QueryParam("page") @DefaultValue("0") int page,
        @QueryParam("pageSize") @DefaultValue("100") int pageSize) {
        return produtoService.searchFiltered(query, min, max, sort, page, pageSize);
    }

    @GET
    @Path("/count")
    public Long total() {
        return produtoService.count();
    }

    @GET
    @Path("/{id}")
    public ProdutoResponseDTO findById(@PathParam("id") long id) {
        return produtoService.findById(id);
    }

    @RolesAllowed("admin") 
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") long id, ProdutoDTO dto) {
        produtoService.update(id, dto);
        return Response.noContent().build();
    }

    @RolesAllowed("admin") 
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long id) {
        produtoService.delete(id);
        return Response.noContent().build();
    }

}
