package br.com.b2w.starwars.bo;

import br.com.b2w.starwars.exceptions.ApplicationBusinessException;
import br.com.b2w.starwars.exceptions.BasicException;

public interface CriticaBo<T> {
    
    void criticar(T obj) throws ApplicationBusinessException, BasicException;
    
}
