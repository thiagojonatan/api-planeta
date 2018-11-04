package br.com.b2w.starwars.persistencia.dao;

import br.com.b2w.starwars.exceptions.BasicException;

public interface GenericDAO<T> extends ConsultaDAO<T> {
	
	void incluir(T entity);

	void alterar(T entity);

	void excluir(T entity) throws BasicException;
        
    int excluirTodos();
    
}
