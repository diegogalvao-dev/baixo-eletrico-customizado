package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

import org.acme.dto.BaixoResponseDTO;
import org.acme.dto.CaptadorAtivoDTO;
import org.acme.dto.CaptadorAtivoResponseDTO;
import org.acme.service.CaptadorAtivoService;

@Path("captador-ativo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CaptadorAtivoResource {

    @Inject
    CaptadorAtivoService captadorAtivoService;

    @GET
    @Path("/buscarTodos")
    public List<CaptadorAtivoResponseDTO> buscarTodos(@QueryParam("page") @DefaultValue("0") int page, @QueryParam("pageSize") @DefaultValue("100") int pageSize) { 
        return captadorAtivoService.findAll(page, pageSize);
    }

        @GET
    @Path("/search")
    public List<CaptadorAtivoResponseDTO> search(@QueryParam("query") String query, @QueryParam("page") @DefaultValue("0") int page, @QueryParam("pageSize") @DefaultValue("100") int pageSize) {
        return captadorAtivoService.search(query, page, pageSize);
    }

    @GET
    @Path("/count")
    public Long total() {
        return captadorAtivoService.count();
    }

    @GET
    @Path("/{id}")
    public CaptadorAtivoResponseDTO findById(@PathParam("id") long id) {
        return captadorAtivoService.findById(id);
    }

    @POST
    public Response create(CaptadorAtivoDTO dto){
        return Response.status(Response.Status.CREATED).entity(captadorAtivoService.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(long id, CaptadorAtivoDTO dto){
        captadorAtivoService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(long id){
        captadorAtivoService.delete(id);
        return Response.noContent().build();
    }

}