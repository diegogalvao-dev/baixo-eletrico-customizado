package org.acme.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;

import org.acme.dto.AuthUsuarioDTO;
import org.acme.dto.AuthResponseDTO;
import org.acme.dto.UsuarioResponseDTO;
import org.acme.dto.CadastroUsuarioDTO;
import org.acme.dto.UsuarioDTO;
import org.acme.dto.EsqueciSenhaDTO;
import org.acme.service.KeycloakAuthService;
import org.acme.service.KeycloakUserProvisioningService;
import org.acme.service.UsuarioService;
import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.transaction.Transactional;
import org.acme.dto.AtualizarPerfilDTO;
import org.acme.dto.AlterarSenhaDTO;
import org.acme.model.Usuario;
import org.acme.repository.UsuarioRepository;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    UsuarioService usuarioService;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    KeycloakAuthService keycloakAuthService;

    @Inject
    KeycloakUserProvisioningService keycloakUserProvisioningService;

    @Inject
    JsonWebToken jwt;

    @POST
    @Path("/login")
    public Response login(@Valid AuthUsuarioDTO authDTO) {
        AuthResponseDTO authResponse = keycloakAuthService.login(authDTO);
        UsuarioResponseDTO usuario = usuarioService.findByUsername(authDTO.login());

        if (usuario == null) {
            throw new jakarta.ws.rs.WebApplicationException(
                    "Usuario autenticado no Keycloak, mas nao cadastrado na API",
                    Status.FORBIDDEN);
        }

        return Response.ok(authResponse).build();
    }

    @POST
    @Path("/register")
    public Response register(@Valid CadastroUsuarioDTO registerDTO) {
        UsuarioDTO dto = new UsuarioDTO(
            registerDTO.username(), 
            registerDTO.username(), 
            registerDTO.email(), 
            registerDTO.senha(), 
            2 // Perfil 2 = Comum
        );

        // Cria no Keycloak primeiro
        keycloakUserProvisioningService.createUser(dto);

        // Depois salva no banco local
        try {
            UsuarioResponseDTO usuario = usuarioService.create(dto);
            return Response.status(Status.CREATED).entity(usuario).build();
        } catch (Exception e) {
            // Em caso de falha no banco local, tenta remover do Keycloak para consistencia
            keycloakUserProvisioningService.deleteUser(dto.username());
            throw new jakarta.ws.rs.WebApplicationException("Falha ao salvar usuario no banco de dados local", Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    @Path("/esqueci-senha")
    public Response esqueciSenha(@Valid EsqueciSenhaDTO dto) {
        // Envia email de recuperacao de senha atraves do Keycloak
        keycloakUserProvisioningService.forgotPassword(dto.username());
        return Response.ok().build();
    }

    @GET
    @Path("/me")
    @Authenticated
    public Response me() {
        UsuarioResponseDTO usuario = usuarioService.findByUsername(resolveUsername());

        if (usuario == null) {
            throw new jakarta.ws.rs.WebApplicationException("Usuario nao encontrado", Status.UNAUTHORIZED);
        }

        return Response.ok(usuario).build();
    }

    @PATCH
    @Path("/me")
    @Authenticated
    @Transactional
    public Response atualizarPerfil(AtualizarPerfilDTO dto) {
        String currentUsername = resolveUsername();
        Usuario usuario = usuarioRepository.findByUsername(currentUsername);
        if (usuario == null) {
            throw new jakarta.ws.rs.WebApplicationException("Usuario nao encontrado", Status.NOT_FOUND);
        }

        if (dto.username() != null && !dto.username().isBlank() && !dto.username().equals(currentUsername)) {
            // Atualiza no Keycloak primeiro
            keycloakUserProvisioningService.updateUsername(currentUsername, dto.username());
            // Depois atualiza local
            usuario.setUsername(dto.username());
        }

        if (dto.nome() != null && !dto.nome().isBlank()) {
            usuario.setNome(dto.nome());
        }
        if (dto.telefone() != null) {
            usuario.setTelefone(dto.telefone());
        }
        return Response.ok(UsuarioResponseDTO.valueOf(usuario)).build();
    }

    @PUT
    @Path("/me/senha")
    @Authenticated
    public Response alterarSenha(@Valid AlterarSenhaDTO dto) {
        String username = resolveUsername();
        
        // Valida se a senha atual esta correta tentando simular um login
        try {
            keycloakAuthService.login(new AuthUsuarioDTO(username, dto.senhaAtual()));
        } catch (Exception e) {
            throw new jakarta.ws.rs.WebApplicationException("Senha atual incorreta", Status.FORBIDDEN);
        }

        // Se passar, atualiza a senha no Keycloak
        keycloakUserProvisioningService.updatePassword(username, dto.novaSenha());
        
        // Opcionalmente atualiza o hash no banco local caso mantenha sincronizado, 
        // mas o Keycloak que gerencia auth.
        
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
