package br.com.rpw.monitoramento.api.dao;

import java.util.List;

public interface GenericDao<T> {
	List<T> get();
	T get(Long id);
	void save(T obj);
	void delete(T obj);
}
