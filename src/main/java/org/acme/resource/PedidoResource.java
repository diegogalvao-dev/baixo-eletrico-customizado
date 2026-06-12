package org.acme.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.acme.dto.PedidoDTO;
import org.acme.service.PedidoService;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("pedido")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    PedidoService pedidoService;

    @Inject
    JsonWebToken jwt;

    @GET
    @RolesAllowed("admin")
    public Response findAll() {
        return Response.ok().entity(pedidoService.findAll()).build();
    }

    @GET
    @Path("/me")
    @RolesAllowed({"User", "admin", "user"}) // Permite ambos casings
    public Response findMyPedidos() {
        String username = resolveUsername();
        return Response.ok().entity(pedidoService.findByUser(username)).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"User", "admin", "user"})
    public Response findById(@PathParam("id") long id) {
        return Response.ok().entity(pedidoService.findById(id)).build();
    }

    @POST
    @RolesAllowed({"User", "admin", "user"})
    public Response create(PedidoDTO dto) {
        // Se a chamada veio autenticada, associamos ao usuário logado, caso não tenha sido enviado o id.
        // O PedidoServiceImpl no entanto não pega do JWT atualmente, ele pega dto.pessoaCliente().
        // Para ficar seguro, faremos isso no DTO se estiver vazio. Mas como o frontend envia nulo (ou não envia), 
        // e o serviço lança exceção se pessoaCliente!=null for inválido, não precisamos injetar aqui.
        // Espera, no create em PedidoServiceImpl ele só seta se dto.pessoaCliente() != null!
        // Se for null, o pedido fica sem usuário? Isso é um erro! 
        // O correto é o backend extrair o usuário do token e associar ao pedido. 
        return Response.status(Response.Status.CREATED).entity(pedidoService.create(dto, resolveUsername())).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response update(@PathParam("id") long id, PedidoDTO dto) {
        pedidoService.update(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response delete(@PathParam("id") long id) {
        pedidoService.delete(id);
        return Response.noContent().build();
    }

    private String resolveUsername() {
        String preferredUsername = jwt.getClaim("preferred_username");
        if (preferredUsername != null && !preferredUsername.isBlank()) {
            return preferredUsername;
        }

        String upn = jwt.getClaim("upn");
        if (upn != null && !upn.isBlank()) {
            return upn;
        }

        return jwt.getSubject();
    }

}
