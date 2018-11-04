package br.com.b2w.starwars.regra.insert;

import br.com.b2w.starwars.bo.PlanetaBo;
import br.com.b2w.starwars.enums.ChaveMensagem;
import br.com.b2w.starwars.exceptions.BasicException;
import br.com.b2w.starwars.modelo.Planeta;
import br.com.b2w.starwars.regra.abstrato.AbstractRegraPlaneta;
import br.com.b2w.starwars.util.ErroCampo;
import br.com.b2w.starwars.util.MessageUtil;

public class RtsRegraPlanetaInsert extends AbstractRegraPlaneta {
	
	public RtsRegraPlanetaInsert(PlanetaBo bo) {
		super(bo);
	}

	@Override
	public void validarNome(Planeta planeta) throws BasicException {
		
		boolean existe = bo.existePorNome(planeta.getNome());
		
		if(!existe) {
			addImpeditivo(new ErroCampo(MessageUtil.getMessage("nome"), MessageUtil.getMessage(ChaveMensagem.NOME_INEXISTENTE.name()), planeta.getNome(), ""));
		}
	}
}
