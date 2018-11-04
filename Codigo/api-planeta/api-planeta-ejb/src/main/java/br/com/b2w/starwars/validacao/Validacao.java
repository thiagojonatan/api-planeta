package br.com.b2w.starwars.validacao;

import br.com.b2w.starwars.enums.ChaveMensagem;
import br.com.b2w.starwars.util.MessageUtil;

public abstract class Validacao<T> {
	
	public void validar(T object) {
		
		if(object == null)
			throw new ValidacaoFalhaException(MessageUtil.getMessage(ChaveMensagem.MSG_E_CAMPO_BRANCO.name()));
	}

}
