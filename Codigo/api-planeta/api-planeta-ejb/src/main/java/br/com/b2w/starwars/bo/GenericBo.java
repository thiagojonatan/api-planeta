package br.com.b2w.starwars.bo;

import br.com.b2w.starwars.exceptions.ApplicationBusinessException;
import br.com.b2w.starwars.exceptions.BasicException;

public interface GenericBo<T> extends ConsultaBo<T>, CriticaBo<T> {
    
   public void incluir(T obj) throws ApplicationBusinessException, BasicException;
   
   public void alterar(T obj) throws ApplicationBusinessException, BasicException;
   
   public void excluir(T obj) throws ApplicationBusinessException, BasicException;
   
   public int excluirTodos();
   
}
