package com.projeto.auth.api.auth.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.projeto.auth.api.auth.enums.SituacaoUsuario;



class JwtUserDetails implements UserDetails {

	private String login;

	private String senha;

	private boolean usuarioCancelado;

	private boolean usuarioBloqueado;

	private boolean senhaExpirada;

	private Collection<? extends GrantedAuthority> authorities;

	public JwtUserDetails(UsuarioAutenticado dadosUsuario) {
		super();
		this.login = dadosUsuario.getLogin();
		this.senha = dadosUsuario.getSenha();
		SituacaoUsuario situacao = dadosUsuario.getSituacao();
		this.usuarioCancelado = SituacaoUsuario.INATIVO == situacao;
		this.usuarioBloqueado = SituacaoUsuario.BLOQUEADO == situacao;
		this.senhaExpirada = false;
		List<GrantedAuthority> auth = new ArrayList<>();
		auth.add(new SimpleGrantedAuthority("ADMIN"));
		this.authorities = auth;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !this.senhaExpirada;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !this.usuarioBloqueado;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !this.senhaExpirada;
	}

	@Override
	public boolean isEnabled() {
		return !this.usuarioCancelado;
	}


}
