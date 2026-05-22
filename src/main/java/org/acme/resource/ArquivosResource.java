package org.acme.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.BaixoCustomizado;
import org.acme.repository.AcessoriosRepository;
import org.acme.repository.BaixoCustomizadoRepository;
import org.acme.repository.BaixoRepository;
import org.acme.service.ArquivoService;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import java.io.InputStream;
import java.util.ArrayList;

@RolesAllowed("admin") 
@Path("/arquivos")
public class ArquivosResource {

    @Inject
    ArquivoService arquivoService;

    @Inject
    BaixoCustomizadoRepository baixoCustomizadoRepository;

    @Inject
    BaixoRepository baixoRepository;

    @Inject
    AcessoriosRepository acessoriosRepository;

    @Inject
    jakarta.persistence.EntityManager entityManager;

    @PATCH
    @Path("/baixocustomizado/{id}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Transactional
    public Response uploadBaixoCustomizado(@PathParam("id") Long id, @RestForm("file") FileUpload file) {
        try {
            BaixoCustomizado entity = baixoCustomizadoRepository.findById(id);
            if (entity == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            // Ler bytes do arquivo
            byte[] bytes = java.nio.file.Files.readAllBytes(file.filePath());
            
            // Salvar no SeaweedFS
            String fid = arquivoService.salvar(file.fileName(), bytes);

            // Adicionar à lista de imagens da entidade
            if (entity.getNomeImagens() == null) {
                entity.setNomeImagens(new ArrayList<>());
            }
            entity.getNomeImagens().add(fid);

            return Response.ok(fid).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro no upload: " + e.getMessage()).build();
        }
    }

    @PATCH
    @Path("/baixo/{id}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Transactional
    public Response uploadBaixo(@PathParam("id") Long id, @RestForm("file") FileUpload file) {
        try {
            var entity = baixoRepository.findById(id);
            if (entity == null) return Response.status(Response.Status.NOT_FOUND).build();

            byte[] bytes = java.nio.file.Files.readAllBytes(file.filePath());
            String fid = arquivoService.salvar(file.fileName(), bytes);

            if (entity.getNomeImagens() == null) entity.setNomeImagens(new ArrayList<>());
            entity.getNomeImagens().add(fid);

            return Response.ok(fid).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PATCH
    @Path("/acessorio/{id}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Transactional
    public Response uploadAcessorio(@PathParam("id") Long id, @RestForm("file") FileUpload file) {
        try {
            var entity = acessoriosRepository.findById(id);
            if (entity == null) return Response.status(Response.Status.NOT_FOUND).build();

            byte[] bytes = java.nio.file.Files.readAllBytes(file.filePath());
            String fid = arquivoService.salvar(file.fileName(), bytes);

            if (entity.getNomeImagens() == null) entity.setNomeImagens(new ArrayList<>());
            entity.getNomeImagens().add(fid);

            return Response.ok(fid).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/download/{fid}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("fid") String fid) {
        try {
            InputStream is = arquivoService.download(fid);
            return Response.ok(is)
                    .header("Content-Disposition", "attachment; filename=\"" + fid + "\"")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{fid}")
    @Transactional
    public Response delete(@PathParam("fid") String fid) {
        try {
            // 1. Remove do storage físico
            arquivoService.excluir(fid);

            // 2. Remove a referência das tabelas de ElementCollection
            entityManager.createNativeQuery("DELETE FROM produto_imagens WHERE nome_imagem = :fid")
                    .setParameter("fid", fid)
                    .executeUpdate();

            entityManager.createNativeQuery("DELETE FROM baixocustomizado_imagens WHERE nome_imagem = :fid")
                    .setParameter("fid", fid)
                    .executeUpdate();

            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
