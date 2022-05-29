package com.projeto.auth.api.auth.controller;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.projeto.auth.api.auth.sevice.ProjetoAuthService;

@CrossOrigin(origins = "*")
public class ProjetoAuthController <T, K extends Serializable> {

	
	private ProjetoAuthService<T, K>  service;
	
	@Autowired
	public ProjetoAuthController(ProjetoAuthService<T, K> service) {
		this.service = service;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<T>> findAll() {
		List<T> registros = this.service.findAll();
		return ResponseEntity.ok().body(registros);
	}

	@GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<T>> search(@RequestParam Map<String, String> params) {
		Collection<T> registros = this.service.findAll(params);
		return ResponseEntity.ok().body(registros);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<T> findOne(@PathVariable("id") K id) {
		T objetoEntidade = this.service.findOne(id);
		return ResponseEntity.ok().body(objetoEntidade);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<T> insert(@Valid @RequestBody T entidade) {
		T persisted = this.service.insert(entidade);
		return ResponseEntity.ok().body(persisted);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<T> patch(@PathVariable("id") K id, @Valid @RequestBody T entidade) {
		T objetoEntidade = this.service.patch(id, entidade);
		return ResponseEntity.ok().body(objetoEntidade);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> delete(@PathVariable("id") K id) {
		this.service.delete(id);
		return ResponseEntity.ok().build();
	}
}
