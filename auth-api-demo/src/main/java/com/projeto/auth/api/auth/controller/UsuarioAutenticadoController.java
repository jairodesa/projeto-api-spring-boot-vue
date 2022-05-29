package com.projeto.auth.api.auth.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.auth.api.auth.exception.NegocioException;
import com.projeto.auth.api.auth.security.UsuarioAutenticado;
import com.projeto.auth.api.auth.sevice.UsuarioService;

@RestController 
@RequestMapping("autenticar")
public class UsuarioAutenticadoController {

	private UsuarioService service;

	@Autowired
	public UsuarioAutenticadoController(UsuarioService service) {
		this.service = service;
	}

	@GetMapping(value = "sessionInfo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioAutenticado> sessionInfo() throws NegocioException {
		UsuarioAutenticado autenticado = this.service.obterDadosUsuarioAutenticado();
		return ResponseEntity.ok().body(autenticado);
	}

}
