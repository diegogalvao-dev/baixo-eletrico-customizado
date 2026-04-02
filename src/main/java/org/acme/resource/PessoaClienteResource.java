package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.acme.dto.PessoaClienteDTO;
import org.acme.service.PessoaClienteService;

@Path("pessoa-cliente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PessoaClienteResource {

    @Inject
    PessoaClienteService pessoaClienteService;

    @GET
    public Response findAll(){
        return Response.ok().entity(pessoaClienteService.findAll()).build();
    }

    @POST
    public Response create(PessoaClienteDTO dto){
        return Response.status(Response.Status.CREATED).entity(pessoaClienteService.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(long id, PessoaClienteDTO dto){
        pessoaClienteService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(long id){
        pessoaClienteService.delete(id);
        return Response.noContent().build();
    }

}