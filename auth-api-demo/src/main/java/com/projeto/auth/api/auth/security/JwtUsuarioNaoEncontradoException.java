package com.projeto.auth.api.auth.security;

import org.springframework.dao.EmptyResultDataAccessException;

public class JwtUsuarioNaoEncontradoException extends RuntimeException {

	public JwtUsuarioNaoEncontradoException(String descricao, EmptyResultDataAccessException e) {
		super(descricao, e);
	}

}
