package org.acme.model.converterjpa;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.acme.model.TipoAcessorio;

@Converter(autoApply = true)
public class TipoAcessorioConverter implements AttributeConverter<TipoAcessorio, Long> {

    @Override
    public Long convertToDatabaseColumn(TipoAcessorio tipoAcessorio){
        return tipoAcessorio == null ? null : tipoAcessorio.getID();
    }

    @Override
    public TipoAcessorio convertToEntityAttribute(Long id){
        return TipoAcessorio.valueOf(id);
    }

}





