package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

import org.acme.dto.CaptadorPassivoDTO;
import org.acme.dto.CaptadorPassivoResponseDTO;
import org.acme.service.CaptadorPassivoService;

@Path("captador-passivo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CaptadorPassivoResource {

    @Inject
    CaptadorPassivoService captadorPassivoService;

    @GET
    @Path("/buscarTodos")
    public List<CaptadorPassivoResponseDTO> buscarTodos(@QueryParam("page") @DefaultValue("0") int page, @QueryParam("pageSize") @DefaultValue("100") int pageSize) { 
        return captadorPassivoService.findAll(page, pageSize);
    }

        @GET
    @Path("/search")
    public List<CaptadorPassivoResponseDTO> search(@QueryParam("query") String query, @QueryParam("page") @DefaultValue("0") int page, @QueryParam("pageSize") @DefaultValue("100") int pageSize) {
        return captadorPassivoService.search(query, page, pageSize);
    }

    @GET
    @Path("/count")
    public Long total() {
        return captadorPassivoService.count();
    }

    @GET
    @Path("/{id}")
    public CaptadorPassivoResponseDTO findById(@PathParam("id") long id) {
        return captadorPassivoService.findById(id);
    }

    @POST
    public Response create(CaptadorPassivoDTO dto){
        return Response.status(Response.Status.CREATED).entity(captadorPassivoService.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(long id, CaptadorPassivoDTO dto){
        captadorPassivoService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(long id){
        captadorPassivoService.delete(id);
        return Response.noContent().build();
    }

}