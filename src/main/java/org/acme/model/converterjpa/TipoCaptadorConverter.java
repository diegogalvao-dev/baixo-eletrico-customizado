package org.acme.model.converterjpa;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.acme.model.CaptadorPosicao;

@Converter(autoApply = true)
public class TipoCaptadorConverter implements AttributeConverter<CaptadorPosicao, Long> {

    @Override
    public Long convertToDatabaseColumn(CaptadorPosicao captadorPosicao){
        return captadorPosicao == null ? null : captadorPosicao.getID();
    }

    @Override
    public CaptadorPosicao convertToEntityAttribute(Long id){
        return CaptadorPosicao.valueOf(id);
    }

}





