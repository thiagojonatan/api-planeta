package br.com.b2w.starwars.validacao.factory;

import br.com.b2w.starwars.enums.ValidacaoTipo;
import br.com.b2w.starwars.validacao.ValidaNulo;
import br.com.b2w.starwars.validacao.Validacao;

@SuppressWarnings("rawtypes")
public class ValidacaoFactory {

	public static Validacao getValidacao(ValidacaoTipo tipo) {
		switch (tipo) {

		case NULO:
			return new ValidaNulo();

		default:
			throw new IllegalArgumentException("Validação não existe!");
		}
	}

}
