package br.com.b2w.starwars.bo;

import java.util.List;

import br.com.b2w.starwars.exceptions.ObjetoNaoEncontradoException;

public interface ConsultaBo<T> {
    
   public List<T> listar();

   public T buscar(Object id) throws ObjetoNaoEncontradoException;
   
}
