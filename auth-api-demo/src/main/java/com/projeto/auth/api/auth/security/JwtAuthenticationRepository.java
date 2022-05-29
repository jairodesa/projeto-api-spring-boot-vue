package com.projeto.auth.api.auth.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.auth.api.auth.repository.UsuarioRepository;


@Repository
public class JwtAuthenticationRepository {
	
	@Autowired
	private UsuarioRepository autenticacaoRepository;

	@Transactional(readOnly = true)
	public UsuarioAutenticado loadUserByUsername(String username) {
		try {
			UsuarioAutenticado usuario = autenticacaoRepository.findUsuario(username);
			return usuario;
		} catch (EmptyResultDataAccessException e) {
			throw new UsernameNotFoundException(username);
		}
	}

	 

}
