package br.com.b2w.starwars.regra.abstrato;

import br.com.b2w.starwars.bo.PlanetaBo;
import br.com.b2w.starwars.exceptions.BasicException;
import br.com.b2w.starwars.modelo.Planeta;
import br.com.b2w.starwars.regra.RegraPlaneta;

public abstract class AbstractRegraPlaneta extends AbstractRegraNegocio<Planeta> implements RegraPlaneta {

	protected PlanetaBo bo;

	public AbstractRegraPlaneta() {
	}

	public AbstractRegraPlaneta(PlanetaBo bo) {
		super();
		this.bo = bo;
	}
	
	@Override
	protected void personalizarRegraNegocio(Planeta object) throws BasicException {
		validarNome(object);
	}
}
