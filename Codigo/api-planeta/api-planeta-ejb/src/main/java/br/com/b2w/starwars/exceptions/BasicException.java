package br.com.b2w.starwars.exceptions;

import br.com.b2w.starwars.enums.ConstantsMessage;
import br.com.b2w.starwars.util.MessageUtil;

public class BasicException extends Exception {

	private static final long serialVersionUID = -26924287209119579L;

	private int code;
	
	protected BasicException(ConstantsMessage codigoErro) {
		this(MessageUtil.getMessage(codigoErro.name()), codigoErro.getCodigo());
	}
	
	public BasicException(final String message) {
		super(message);
	}
	
	public BasicException(final Throwable cause) {
		super(cause);
	}

	public BasicException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public BasicException(final Exception e) {
		super(e);
	}

	public BasicException(final String message, int code) {
		super(message);
		this.code = code;
	}
	
	public BasicException() {
		super();
	}

	public BasicException(final String msg, final Exception e) {
		super(msg, e);
	}

	public int getCode() {
		return code;
	}

}