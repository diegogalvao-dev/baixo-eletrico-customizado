package org.acme.model.converterjpa;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.acme.model.TipoCaptador;

@Converter(autoApply = true)
public class TipoCaptadorConverter implements AttributeConverter<TipoCaptador, Long> {

    @Override
    public Long convertToDatabaseColumn(TipoCaptador tipoCaptador){
        return tipoCaptador == null ? null : tipoCaptador.getID();
    }

    @Override
    public TipoCaptador convertToEntityAttribute(Long id){
        return TipoCaptador.valueOf(id);
    }

}





