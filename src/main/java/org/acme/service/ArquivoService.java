package org.acme.service;

import java.io.InputStream;
import java.util.List;

public interface ArquivoService {
    String salvar(String nomeArquivo, byte[] arquivo);
    InputStream download(String fid);
    void excluir(String fid);
}
