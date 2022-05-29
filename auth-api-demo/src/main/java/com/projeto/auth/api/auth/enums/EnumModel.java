package com.projeto.auth.api.auth.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonDeserialize(using = EnumModelDeserializer.class)
public interface EnumModel<K> {

	K getCodigo();

	String getDescricao();

	default String getType() {
		return this.getClass().getName();
	}

	default boolean matchCodigo(K codigo) {
	    if(codigo != null ) {
            return getCodigo().toString().equals(codigo.toString());
        }
        return false;
	}
	
	

}