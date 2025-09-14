package org.acme.dto;

/**
 * DTO (Data Transfer Object) para carregar os dados da configuração eletrônica.
 * Como a ConfiguracaoEletronica é uma "parte" de um BaixoCustomizado (Composição),
 * este DTO será aninhado dentro do BaixoCustomizadoDTO para criar tudo de uma vez.
 * Usamos um 'record' do Java 16+ para uma definição concisa e imutável.
 */
public record ConfiguracaoEletronicaDTO(
        Integer volumeKnobs,
        Integer toneKnobs,
        Boolean circuitoAtivo
) {
}
