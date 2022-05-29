package com.projeto.auth.api.auth.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	private JwtTokenUtil jwtUtil;

	private UserDetailsService userDetailsService;

	private Environment env;

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtTokenUtil jwtUtil,
			UserDetailsService userDetailsService, Environment env) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
		this.env = env;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String header = request.getHeader(env.getProperty("jwt.header.name"));
		if (header != null && header.startsWith(env.getProperty("jwt.token.prefix"))) {
			UsernamePasswordAuthenticationToken auth = getAuthentication(header.substring(7));
			if (auth != null) {
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		String username = jwtUtil.getUsernameFromToken(token);
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
		if (jwtUtil.validateToken(token, userDetails)) {
			return new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
		}
		return null;
	}
}
