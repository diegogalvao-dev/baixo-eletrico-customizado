package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

import org.acme.dto.ProdutoDTO;
import org.acme.dto.ProdutoResponseDTO;
import org.acme.service.ProdutoService;

@Path("produto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    @Inject
    ProdutoService produtoService;

    @GET
    @Path("/buscarTodos")
    public List<ProdutoResponseDTO> buscarTodos(@QueryParam("page") @DefaultValue("0") int page, @QueryParam("pageSize") @DefaultValue("100") int pageSize) {
        return produtoService.findAll(page, pageSize);
    }

    @GET
    @Path("/search")
    public List<ProdutoResponseDTO> search(@QueryParam("query") String query, @QueryParam("page") @DefaultValue("0") int page, @QueryParam("pageSize") @DefaultValue("100") int pageSize) {
        return produtoService.search(query, page, pageSize);
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

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") long id, ProdutoDTO dto) {
        produtoService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long id) {
        produtoService.delete(id);
        return Response.noContent().build();
    }

}
