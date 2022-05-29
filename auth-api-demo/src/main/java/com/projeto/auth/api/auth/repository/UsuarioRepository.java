package com.projeto.auth.api.auth.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projeto.auth.api.auth.entity.Usuario;
import com.projeto.auth.api.auth.security.UsuarioAutenticado;

@Repository
public interface UsuarioRepository extends AuthRepository<Usuario, Long> {

	Usuario findByLogin(String login);

	@Query("select new com.projeto.auth.api.auth.security.UsuarioAutenticado(s.id, s.login, s.matricula, s.nome , s.situacao, s.senhaExpirada, s.senha) from Usuario s where s.nome = ?1")
	UsuarioAutenticado findUsuario(String username);

	@Query("select s from Usuario s where s.cpf = :chave")
	Usuario findByCpf(@Param("chave") String chave);

}
