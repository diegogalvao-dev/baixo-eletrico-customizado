package org.acme.model.converterjpa;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.acme.model.AcessorioTipo;

@Converter(autoApply = true)
public class TipoAcessorioConverter implements AttributeConverter<AcessorioTipo, Long> {

    @Override
    public Long convertToDatabaseColumn(AcessorioTipo acessorioTipo){
        return acessorioTipo == null ? null : acessorioTipo.getID();
    }

    @Override
    public AcessorioTipo convertToEntityAttribute(Long id){
        return AcessorioTipo.valueOf(id);
    }

}





