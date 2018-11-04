package br.com.b2w.starwars.bo.impl;

import br.com.b2w.starwars.bo.GenericBo;
import br.com.b2w.starwars.exceptions.ApplicationBusinessException;
import br.com.b2w.starwars.exceptions.BasicException;
import br.com.b2w.starwars.persistencia.dao.GenericDAO;

public abstract class StarWarsGenericBo<T> extends StarWarsConsultaBo<T> implements GenericBo<T> {

    protected abstract GenericDAO<T> getDao();

    public void incluir(T obj) throws ApplicationBusinessException, BasicException{
        criticar(obj);
        getDao().incluir(obj);
    }

    public void alterar(T obj) throws ApplicationBusinessException, BasicException{
        criticar(obj);
        getDao().alterar(obj);
    }

    public void excluir(T obj) throws ApplicationBusinessException, BasicException {
    	criticar(obj);
		getDao().excluir(obj);
    }

    public int excluirTodos() {
        return getDao().excluirTodos();
    }

	@Override
	public void criticar(T obj) throws ApplicationBusinessException, BasicException {}
}
