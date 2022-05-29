package com.projeto.auth.api.auth.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AuthRepository<T, K extends Serializable>
		extends JpaRepository<T, K>, QuerydslPredicateExecutor<T> {

	

}
