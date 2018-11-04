package br.com.b2w.starwars.exceptions;

import java.io.Serializable;

import br.com.b2w.starwars.enums.ConstantsMessage;

public class ObjetoNaoEncontradoException extends BasicException implements Serializable {

	private static final long serialVersionUID = 934886159117435161L;
	
	public ObjetoNaoEncontradoException() {
		super(ConstantsMessage.ERROR_00000);
	}
}
