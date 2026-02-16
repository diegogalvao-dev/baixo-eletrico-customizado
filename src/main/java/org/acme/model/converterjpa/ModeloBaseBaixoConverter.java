package org.acme.model.converterjpa;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.acme.model.ModeloBaseBaixo;

@Converter(autoApply = true)
public class ModeloBaseBaixoConverter implements AttributeConverter<ModeloBaseBaixo, Long> {

    @Override
    public Long convertToDatabaseColumn(ModeloBaseBaixo modeloBaseBaixo){
        return modeloBaseBaixo == null ? null : modeloBaseBaixo.getID();
    }

    @Override
    public ModeloBaseBaixo convertToEntityAttribute(Long id){
        return ModeloBaseBaixo.valueOf(id);
    }

}





