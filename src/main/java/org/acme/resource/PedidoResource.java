package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.acme.dto.PedidoDTO;
import org.acme.service.PedidoService;

@Path("pedido")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    PedidoService pedidoService;

    @GET
    public Response findAll() {
        return Response.ok().entity(pedidoService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") long id) {
        return Response.ok().entity(pedidoService.findById(id)).build();
    }

    @POST
    public Response create(PedidoDTO dto) {
        return Response.status(Response.Status.CREATED).entity(pedidoService.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") long id, PedidoDTO dto) {
        pedidoService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long id) {
        pedidoService.delete(id);
        return Response.noContent().build();
    }

}
