package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.acme.dto.PessoaLuthierDTO;
import org.acme.service.PessoaLuthierService;

@Path("pessoa-luthier")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PessoaLuthierResource {

    @Inject
    PessoaLuthierService pessoaLuthierService;

    @GET
    public Response findAll(){
        return Response.ok().entity(pessoaLuthierService.findAll()).build();
    }

    @POST
    public Response create(PessoaLuthierDTO dto){
        return Response.status(Response.Status.CREATED).entity(pessoaLuthierService.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(long id, PessoaLuthierDTO dto){
        pessoaLuthierService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(long id){
        pessoaLuthierService.delete(id);
        return Response.noContent().build();
    }

}