package com.projeto.auth.api.auth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	JwtAuthenticationRepository repository;

	@Autowired
	public JwtUserDetailsService(JwtAuthenticationRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsuarioAutenticado dadosUsuario = repository.loadUserByUsername(username);
		return new JwtUserDetails(dadosUsuario);

	}

}