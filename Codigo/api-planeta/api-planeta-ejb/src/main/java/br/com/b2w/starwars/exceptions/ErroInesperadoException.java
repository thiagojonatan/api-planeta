package br.com.b2w.starwars.exceptions;

import java.io.Serializable;

import br.com.b2w.starwars.enums.ConstantsMessage;

public class ErroInesperadoException extends BasicException implements Serializable {

	private static final long serialVersionUID = -8919140089695539854L;

	public ErroInesperadoException() {
		super(ConstantsMessage.ERROR_00001);
	}
}
