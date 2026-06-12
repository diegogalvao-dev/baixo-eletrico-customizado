package org.acme.resource;

import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

import org.acme.dto.ProdutoResponseDTO;
import org.acme.service.FavoritosService;

import io.quarkus.security.identity.SecurityIdentity;

@Path("/favoritos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class FavoritosResource {

    @Inject
    FavoritosService favoritosService;

    @Inject
    SecurityIdentity identity;

    @GET
    public List<ProdutoResponseDTO> getFavoritos() {
        return favoritosService.getFavoritos(identity.getPrincipal().getName());
    }

    @POST
    @Path("/{produtoId}")
    public Response adicionarFavorito(@PathParam("produtoId") Long produtoId) {
        favoritosService.adicionarFavorito(identity.getPrincipal().getName(), produtoId);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{produtoId}")
    public Response removerFavorito(@PathParam("produtoId") Long produtoId) {
        favoritosService.removerFavorito(identity.getPrincipal().getName(), produtoId);
        return Response.noContent().build();
    }
}
