package org.acme.dto;

import org.acme.model.BaixoCor;
import org.acme.model.BaixoModeloBase;
import org.acme.model.BaixoStatus;
import org.acme.model.Captador;
import org.acme.model.ConfiguracaoEletronica;
import org.acme.model.PessoaCliente;
import org.acme.model.PessoaLuthier;

import java.util.List;

public record BaixoCustomizadoDTO(
        BaixoModeloBase baixoModeloBase,
        String description,
        BaixoCor baixoCor,
        Long configuracaoEletronica,
        List<Long> captadorList,
        Double estimatedPrice,
        BaixoStatus baixoStatus,
        Long pessoaCliente,
        Long pessoaLuthier

) { }
