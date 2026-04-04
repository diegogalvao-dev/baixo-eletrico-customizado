package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.acme.dto.PedidoItemDTO;
import org.acme.service.PedidoItemService;

@Path("pedidoitem")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoItemResource {

    @Inject
    PedidoItemService pedidoItemService;

    @GET
    public Response findAll() {
        return Response.ok().entity(pedidoItemService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") long id) {
        return Response.ok().entity(pedidoItemService.findById(id)).build();
    }

    @POST
    public Response create(PedidoItemDTO dto) {
        return Response.status(Response.Status.CREATED).entity(pedidoItemService.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") long id, PedidoItemDTO dto) {
        pedidoItemService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long id) {
        pedidoItemService.delete(id);
        return Response.noContent().build();
    }

}
