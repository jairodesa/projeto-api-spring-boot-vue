package com.projeto.auth.api.auth.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;

import javax.persistence.Table;


import com.projeto.auth.api.auth.enums.SituacaoUsuario;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "TB_USUARIO")
@AttributeOverride(name = "pk", column = @Column(name = "ID_USUARIO"))
@Data
@EqualsAndHashCode(callSuper = true)
public class Usuario extends Entidade<Long>{


	@Column(name = "DS_NOME", length = 150)
	private String nome;
	
	@Column(name = "NU_CPF", unique = true, length = 30)
	private String cpf;
	
	@Column(name = "CD_SITUACAO")
	@Convert(converter = SituacaoUsuario.Converter.class)
	private SituacaoUsuario situacao;
}
