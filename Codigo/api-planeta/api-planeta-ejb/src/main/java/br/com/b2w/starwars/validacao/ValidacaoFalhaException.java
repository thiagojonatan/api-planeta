package br.com.b2w.starwars.validacao;

public class ValidacaoFalhaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String mensagem;

	public ValidacaoFalhaException(String mensagem) {
		super();
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}
	
	

}
