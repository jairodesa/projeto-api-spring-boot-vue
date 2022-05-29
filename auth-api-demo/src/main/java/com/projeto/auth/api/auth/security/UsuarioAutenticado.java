package com.projeto.auth.api.auth.security;

import java.io.Serializable;

import com.projeto.auth.api.auth.enums.SituacaoUsuario;

import lombok.Data;

@Data
public class UsuarioAutenticado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String login;
	
	public UsuarioAutenticado(Long id, String login, String matricula, String nome, SituacaoUsuario situacao,
			boolean senhaExpirada, String senha) {
		super();
		this.id = id;
		this.login = login;
		this.matricula = matricula;
		this.nome = nome;
		this.situacao = situacao;
		this.senhaExpirada = senhaExpirada;
		this.senha = senha;
	}

	private String matricula;

	private String nome;
	
	private SituacaoUsuario situacao;
	
	private boolean senhaExpirada;
	
	private String senha;
}
