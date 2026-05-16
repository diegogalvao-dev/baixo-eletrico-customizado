package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

@ApplicationScoped
public class ArquivoServiceImpl implements ArquivoService {

    @ConfigProperty(name = "seaweedfs.filer.url")
    String filerUrl;

    private final HttpClient httpClient = HttpClient.newBuilder().build();

    @Override
    public String salvar(String nomeArquivo, byte[] arquivo) {
        // Gerar um nome único para evitar colisões
        String extensao = nomeArquivo.contains(".") ? nomeArquivo.substring(nomeArquivo.lastIndexOf(".")) : "";
        String novoNome = UUID.randomUUID().toString() + extensao;
        String path = "/arquivos/" + novoNome;

        try {
            // No SeaweedFS Filer, podemos fazer um PUT direto com o corpo do arquivo
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(filerUrl + path))
                    .header("Content-Type", "application/octet-stream")
                    .PUT(HttpRequest.BodyPublishers.ofByteArray(arquivo))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 201 || response.statusCode() == 200 || response.statusCode() == 204) {
                return novoNome; // Retornamos o nome único como nosso "fid"
            } else {
                throw new RuntimeException("Erro ao salvar arquivo no SeaweedFS: " + response.body());
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro na comunicação com SeaweedFS", e);
        }
    }

    @Override
    public InputStream download(String fid) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(filerUrl + "/arquivos/" + fid))
                    .GET()
                    .build();

            HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());

            if (response.statusCode() == 200) {
                return response.body();
            } else {
                throw new RuntimeException("Arquivo não encontrado no SeaweedFS: " + fid);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar arquivo no SeaweedFS", e);
        }
    }

    @Override
    public void excluir(String fid) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(filerUrl + "/arquivos/" + fid))
                    .DELETE()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 204 && response.statusCode() != 200 && response.statusCode() != 404) {
                throw new RuntimeException("Erro ao excluir arquivo no SeaweedFS: " + response.body());
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao excluir arquivo no SeaweedFS", e);
        }
    }
}
