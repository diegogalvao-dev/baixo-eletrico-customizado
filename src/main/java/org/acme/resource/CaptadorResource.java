package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.Captador;
import org.acme.service.CaptadorService;

import java.util.List;

@Path("/captadores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CaptadorResource {

    @Inject
    CaptadorService captadorService;

    @POST
    public Response create(Captador captador) {
        Captador created = captadorService.create(captador);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @GET
    public List<Captador> findAll() {
        return captadorService.findAll();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        Captador captador = captadorService.findById(id);
        if (captador == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(captador).entity(captador).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Captador captador) {
        captadorService.update(id, captador);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        captadorService.delete(id);
        return Response.noContent().build();
    }
}
