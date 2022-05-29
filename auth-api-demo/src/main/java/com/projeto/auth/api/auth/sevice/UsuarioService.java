package com.projeto.auth.api.auth.sevice;


import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.querydsl.core.types.dsl.BooleanExpression;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.projeto.auth.api.auth.entity.Usuario;
import com.projeto.auth.api.auth.repository.UsuarioRepository;
import com.projeto.auth.api.auth.security.JwtUsuarioAutenticado;
import com.projeto.auth.api.auth.security.UsuarioAutenticado;

@Service
public class UsuarioService implements ProjetoAuthService<Usuario, Long>{

	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public Usuario findOne(Long id) {
		return this.repository.findById(id).get();
	}

	@Override
	public List<Usuario> findAll() {
		return this.repository.findAll(Sort.by(Direction.DESC, "id"));	}

	@Override
	public List<Usuario> findAll(Map<String, String> params) {
		Iterable<Usuario> resultado = this.repository.findAll(this.getPredicate(params),
				Sort.by(Direction.DESC, "id"));
		return ImmutableList.copyOf(resultado);
	}

	@Override
	public Usuario insert(Usuario entidade) {
		Usuario en = this.repository.saveAndFlush(entidade);
		return en;
	}

	@Override
	public Usuario patch(Long id, Usuario entidade) {
		Usuario entidadeDb = this.findOne(id);
		Usuario en = this.repository.save(entidadeDb);
		return en;
	}

	@Override
	public void delete(Long pk) {
		throw new java.lang.SecurityException("Operação não permitida");
		
	}
	public UsuarioAutenticado obterDadosUsuarioAutenticado() {
		return repository.findUsuario(JwtUsuarioAutenticado.getLoginUsuarioAutenticado());
	}
	


	protected BooleanExpression getPredicate(Map<String, String> params) {
		
		/*
		 * Usuario usuario = Usuario.servidor; BooleanExpression queryExpression =
		 * servidor.pk.isNotNull(); if (params.get("nome") != null) { BooleanExpression
		 * nome = servidor.nome.likeIgnoreCase("%" + params.get("nome") + "%");
		 * queryExpression = queryExpression.and(nome); }
		 * 
		 * if (params.get("cpf") != null) { BooleanExpression cpf =
		 * servidor.cpf.eq(params.get("cpf")); queryExpression =
		 * queryExpression.and(cpf); }
		 */
		 
	
		return null;

		//return queryExpression;
	}

}
