package com.projeto.auth.api.auth.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class JwtUsuarioAutenticado {

	public static String getLoginUsuarioAutenticado() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

}
