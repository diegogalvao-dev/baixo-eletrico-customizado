package org.acme.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;

import org.acme.dto.AuthUsuarioDTO;
import org.acme.dto.AuthResponseDTO;
import org.acme.dto.UsuarioResponseDTO;
import org.acme.dto.CadastroUsuarioDTO;
import org.acme.dto.UsuarioDTO;
import org.acme.service.HashService;
import org.acme.service.JwtService;
import org.acme.service.UsuarioService;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    HashService hashService;

    @Inject
    UsuarioService usuarioService;

    @Inject
    JwtService jwtService;

    @Inject
    JsonWebToken jwt;

    @POST
    @Path("/login")
    public Response login(@Valid AuthUsuarioDTO authDTO) {
        UsuarioResponseDTO usuario = autenticar(authDTO);
        String token = jwtService.generateJwt(usuario);
        return Response.ok(new AuthResponseDTO(token, "Bearer")).build();
    }

    @POST
    @Path("/register")
    public Response register(@Valid CadastroUsuarioDTO registerDTO) {
        // Nome assume o valor do username para simplicidade no formulário de 3 campos
        UsuarioDTO dto = new UsuarioDTO(
            registerDTO.username(), 
            registerDTO.username(), 
            registerDTO.email(), 
            registerDTO.senha(), 
            2 // Perfil 2 = Comum
        );
        UsuarioResponseDTO usuario = usuarioService.create(dto);
        return Response.status(Status.CREATED).entity(usuario).build();
    }

    @GET
    @Path("/me")
    @Authenticated
    public Response me() {
        UsuarioResponseDTO usuario = usuarioService.findByUsername(jwt.getSubject());

        if (usuario == null) {
            throw new jakarta.ws.rs.WebApplicationException("Usuario nao encontrado", Status.UNAUTHORIZED);
        }

        return Response.ok(usuario).build();
    }

    private UsuarioResponseDTO autenticar(AuthUsuarioDTO authDTO) {
        String hash = hashService.getHashSenha(authDTO.senha());

        UsuarioResponseDTO usuario = usuarioService.findByLoginAndSenha(authDTO.login(), hash);

        if (usuario == null) {
            throw new jakarta.ws.rs.WebApplicationException("Login ou senha inválidos", Status.UNAUTHORIZED);
        } 
        return usuario;
    }
}
