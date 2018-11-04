package br.com.b2w.starwars.bo;

import java.util.List;

import br.com.b2w.starwars.exceptions.BasicException;
import br.com.b2w.starwars.exceptions.ObjetoNaoEncontradoException;
import br.com.b2w.starwars.modelo.Planeta;

public interface PlanetaBo extends GenericBo<Planeta> {

	boolean existePorNome(String nome) throws BasicException;

	long contarTodos();

	List<Planeta> listar(Integer page) throws ObjetoNaoEncontradoException;
}
