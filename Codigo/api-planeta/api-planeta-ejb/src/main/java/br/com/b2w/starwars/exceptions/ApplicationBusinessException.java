package br.com.b2w.starwars.exceptions;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.b2w.starwars.util.ErroCampo;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ApplicationBusinessException extends Exception {

	private static final long serialVersionUID = 1L;

	private String mensagem;
	private List<ErroCampo> erros;
	private List<ErroCampo> errosAceitaveis;
	private List<ErroCampo> alertas;

	public ApplicationBusinessException() {
		super();
	}

	public ApplicationBusinessException(String msg) {
		super(msg);
		this.mensagem = msg;
	}

	public ApplicationBusinessException(List<ErroCampo> erros, List<ErroCampo> errosAceitaveis,
			List<ErroCampo> alertas) {
		super();
		this.erros = new ArrayList<>(erros);
		this.errosAceitaveis = new ArrayList<>(errosAceitaveis);
		this.alertas = new ArrayList<>(alertas);
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public List<ErroCampo> getErros() {
		return erros;
	}

	public void setErros(List<ErroCampo> erros) {
		this.erros = erros;
	}

	public List<ErroCampo> getErrosAceitaveis() {
		return errosAceitaveis;
	}

	public void setErrosAceitaveis(List<ErroCampo> erros) {
		this.errosAceitaveis = erros;
	}

	public List<ErroCampo> getAlertas() {
		return alertas;
	}

	public void setAlertas(List<ErroCampo> erros) {
		this.alertas = erros;
	}

}
