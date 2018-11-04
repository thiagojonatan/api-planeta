package br.com.b2w.starwars.regra;

import br.com.b2w.starwars.exceptions.BasicException;
import br.com.b2w.starwars.modelo.Planeta;
import br.com.b2w.starwars.util.ErroCampo;

public interface RegraPlaneta extends RegraNegocio<Planeta, ErroCampo> {
	
	void validarNome(Planeta planeta) throws BasicException;
}
