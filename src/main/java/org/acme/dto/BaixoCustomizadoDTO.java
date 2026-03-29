package org.acme.dto;

import org.acme.model.BaixoCor;
import org.acme.model.BaixoModeloBase;
import org.acme.model.BaixoStatus;
import org.acme.model.ConfiguracaoEletronica;

import java.util.List;

public record BaixoCustomizadoDTO(
        BaixoModeloBase baixoModeloBase,
        String description,
        BaixoCor baixoCor,
        ConfiguracaoEletronica configuracaoEletronica,
        List<Long> captadorList,
        Double estimatedPrice,
        BaixoStatus baixoStatus,
        Long pessoaCliente,
        Long pessoaLuthier

) { }
