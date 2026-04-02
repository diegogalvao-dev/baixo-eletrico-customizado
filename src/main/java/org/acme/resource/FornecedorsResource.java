package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.acme.dto.FornecedorDTO;
import org.acme.service.FornecedorService;

@Path("Fornecedor")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FornecedorsResource {

    @Inject
    FornecedorService fornecedorService;

    @GET
    public Response findAll(){
        return Response.ok().entity(fornecedorService.findAll()).build();
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
