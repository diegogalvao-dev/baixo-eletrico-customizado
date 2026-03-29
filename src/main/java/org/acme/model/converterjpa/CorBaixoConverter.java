package org.acme.model.converterjpa;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.acme.model.BaixoCor;

@Converter(autoApply = true)
public class CorBaixoConverter implements AttributeConverter<BaixoCor, Long> {

    @Override
    public Long convertToDatabaseColumn(BaixoCor baixoCor){
        return baixoCor == null ? null : baixoCor.getID();
    }

    @Override
    public BaixoCor convertToEntityAttribute(Long id){
        return BaixoCor.valueOf(id);
    }

}





