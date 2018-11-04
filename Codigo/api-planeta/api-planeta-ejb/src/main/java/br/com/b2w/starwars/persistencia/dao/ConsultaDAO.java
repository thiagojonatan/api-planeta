package br.com.b2w.starwars.persistencia.dao;

import java.util.List;

public interface ConsultaDAO<T> {

	T buscar(Object id);

	List<T> listar();

	long contarTodos();

	List<T> listar(Integer page);

}
