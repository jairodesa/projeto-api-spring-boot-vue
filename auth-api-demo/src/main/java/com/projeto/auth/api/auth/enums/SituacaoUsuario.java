package com.projeto.auth.api.auth.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.projeto.auth.api.auth.jpaconverters.EnumModelConverter;

import lombok.Getter;

@Getter
@JsonDeserialize(using = EnumModelDeserializer.class)
public enum SituacaoUsuario implements EnumModel<String> {
	ATIVO("A", "Ativo"), BLOQUEADO("B", "Bloqueado"), INATIVO("C", "Cancelado");

	private String codigo;

	private String descricao;

	private SituacaoUsuario(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	@javax.persistence.Converter(autoApply = true)
	public static class Converter extends EnumModelConverter<SituacaoUsuario, String> {
		public Converter() {
			super(SituacaoUsuario.class);
		}
	}

	public static SituacaoUsuario get(String codigo) {
		return EnumModelHelper.get().obterInstancia(SituacaoUsuario.values(), codigo);
	}

}