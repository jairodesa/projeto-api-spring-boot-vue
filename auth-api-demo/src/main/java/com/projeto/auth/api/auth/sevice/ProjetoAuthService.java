package com.projeto.auth.api.auth.sevice;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface ProjetoAuthService <T, K extends Serializable> {

	public T findOne(K pk);

	public List<T> findAll();

	public List<T> findAll(Map<String, String> params);

	public T insert(T entidade);

	public T patch(K pk, T entidade);

	public void delete(K pk);

}