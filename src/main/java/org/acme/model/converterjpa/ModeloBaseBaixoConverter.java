package org.acme.model.converterjpa;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.acme.model.BaixoModeloBase;

@Converter(autoApply = true)
public class ModeloBaseBaixoConverter implements AttributeConverter<BaixoModeloBase, Long> {

    @Override
    public Long convertToDatabaseColumn(BaixoModeloBase baixoModeloBase){
        return baixoModeloBase == null ? null : baixoModeloBase.getID();
    }

    @Override
    public BaixoModeloBase convertToEntityAttribute(Long id){
        return BaixoModeloBase.valueOf(id);
    }

}





