package org.acme.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

import org.acme.dto.BaixoCustomizadoDTO;
import org.acme.dto.BaixoCustomizadoResponseDTO;
import org.acme.service.BaixoCustomizadoService;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("baixo-customizado")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BaixoCustomizadoResource {

    @Inject
    BaixoCustomizadoService baixoCustomizadoService;

    @Inject
    JsonWebToken jwt;

    @GET
    @Path("/buscarTodos")
    @RolesAllowed("admin")
    public List<BaixoCustomizadoResponseDTO> buscarTodos(@QueryParam("page") @DefaultValue("0") int page, @QueryParam("pageSize") @DefaultValue("100") int pageSize) { 
        return baixoCustomizadoService.findAll(page, pageSize);
    }

    @GET
    @Path("/search")
    @RolesAllowed("admin")
    public List<BaixoCustomizadoResponseDTO> search(@QueryParam("query") String query, @QueryParam("page") @DefaultValue("0") int page, @QueryParam("pageSize") @DefaultValue("100") int pageSize) {
        return baixoCustomizadoService.search(query, page, pageSize);
    }

    @GET
    @Path("/count")
    @RolesAllowed("admin")
    public Long total() {
        return baixoCustomizadoService.count();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"User", "admin", "user"})
    public BaixoCustomizadoResponseDTO findById(@PathParam("id") long id) {
        return baixoCustomizadoService.findById(id);
    }

    @GET
    @Path("/me")
    @RolesAllowed({"User", "admin", "user"})
    public List<BaixoCustomizadoResponseDTO> getMyProjetos() {
        return baixoCustomizadoService.getMyProjetos(resolveUsername());
    }

    @POST
    @RolesAllowed({"User", "admin", "user"})
    public Response create(BaixoCustomizadoDTO dto){
        return Response.status(Response.Status.CREATED).entity(baixoCustomizadoService.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response update(@PathParam("id") long id, BaixoCustomizadoDTO dto){
        return Response.ok(baixoCustomizadoService.update(id, dto)).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response delete(@PathParam("id") long id){
        baixoCustomizadoService.delete(id);
        return Response.noContent().build();
    }

    private String resolveUsername() {
        if (jwt == null) return null;
        try {
            String preferredUsername = jwt.getClaim("preferred_username");
            if (preferredUsername != null && !preferredUsername.isBlank()) {
                return preferredUsername;
            }
            String upn = jwt.getClaim("upn");
            if (upn != null && !upn.isBlank()) {
                return upn;
            }
            return jwt.getSubject();
        } catch (Exception e) {
            return null;
        }
    }
}