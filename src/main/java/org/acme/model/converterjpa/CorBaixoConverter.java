package org.acme.model.converterjpa;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.acme.model.CorBaixo;

@Converter(autoApply = true)
public class CorBaixoConverter implements AttributeConverter<CorBaixo, Long> {

    @Override
    public Long convertToDatabaseColumn(CorBaixo corBaixo){
        return corBaixo == null ? null : corBaixo.getID();
    }

    @Override
    public CorBaixo convertToEntityAttribute(Long id){
        return CorBaixo.valueOf(id);
    }

}





