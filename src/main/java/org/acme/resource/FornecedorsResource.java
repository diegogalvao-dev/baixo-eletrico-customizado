package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

import org.acme.dto.FornecedorDTO;
import org.acme.dto.FornecedorResponseDTO;
import org.acme.service.FornecedorService;

@Path("Fornecedor")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FornecedorsResource {

    @Inject
    FornecedorService fornecedorService;


@GET
    @Path("/buscarTodos")
    public List<FornecedorResponseDTO> buscarTodos(@QueryParam("page") @DefaultValue("0") int page, @QueryParam("pageSize") @DefaultValue("100") int pageSize) { 
        return fornecedorService.findAll(page, pageSize);
    }

    @GET
    @Path("/search")
    public List<FornecedorResponseDTO> search(@QueryParam("query") String query, @QueryParam("page") @DefaultValue("0") int page, @QueryParam("pageSize") @DefaultValue("100") int pageSize) {
        return fornecedorService.search(query, page, pageSize);
    }

    @GET
    @Path("/count")
    public Long total() {
        return fornecedorService.count();
    }

    @GET
    @Path("/{id}")
    public FornecedorResponseDTO findById(@PathParam("id") long id) {
        return fornecedorService.findById(id);
    }

    @POST
    public Response create(FornecedorDTO dto){
        return Response.status(Response.Status.CREATED).entity(fornecedorService.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(long id, FornecedorDTO dto){
        fornecedorService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(long id){
        fornecedorService.delete(id);
        return Response.noContent().build();
    }

}
