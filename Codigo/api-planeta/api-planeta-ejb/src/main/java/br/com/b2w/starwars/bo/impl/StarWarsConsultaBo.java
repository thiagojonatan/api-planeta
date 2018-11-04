package br.com.b2w.starwars.bo.impl;

import java.util.List;

import br.com.b2w.starwars.bo.ConsultaBo;
import br.com.b2w.starwars.exceptions.ObjetoNaoEncontradoException;
import br.com.b2w.starwars.persistencia.dao.ConsultaDAO;

public abstract class StarWarsConsultaBo<T> implements ConsultaBo<T> {

	protected abstract ConsultaDAO<T> getDao();

	public List<T> listar() {
		return getDao().listar();
	}


	public T buscar(Object id) throws ObjetoNaoEncontradoException {
		T result = getDao().buscar(id);
		if(result == null)
			throw new ObjetoNaoEncontradoException();
		
		return result;
	}
	
}
