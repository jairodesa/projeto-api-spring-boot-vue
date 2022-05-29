package com.projeto.auth.api.auth.jpaconverters;

import javax.persistence.AttributeConverter;

import com.projeto.auth.api.auth.enums.EnumModel;
import com.projeto.auth.api.auth.enums.EnumModelHelper;


public abstract class EnumModelConverter<T extends Enum<T> & EnumModel<E>, E> implements AttributeConverter<T, E> {

	private final Class<T> clazz;

	public EnumModelConverter(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public E convertToDatabaseColumn(T attribute) {
		return attribute != null ? attribute.getCodigo() : null;
	}

	@Override
	public T convertToEntityAttribute(E dbData) {
		T[] enums = clazz.getEnumConstants();
		return EnumModelHelper.get().obterInstancia(enums, dbData);
	}

}
