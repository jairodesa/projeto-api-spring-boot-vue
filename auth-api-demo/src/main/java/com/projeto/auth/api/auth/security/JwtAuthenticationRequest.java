package com.projeto.auth.api.auth.security;

import java.io.Serializable;

public class JwtAuthenticationRequest implements Serializable {

	private String usuario;

	private String senha;
	
	public JwtAuthenticationRequest() {
		super();
	}

	public JwtAuthenticationRequest(String usuario, String senha) {
		super();
		this.usuario = usuario;
		this.senha = senha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}