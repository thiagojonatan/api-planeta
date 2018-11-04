package br.com.b2w.starwars.rest.modelo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(value=Include.NON_NULL)
public class Aviso implements Serializable {
	
	private static final long serialVersionUID = -175992891002084437L;
	
	private int code;
	private String mensagem;
	private String detalhe;
	private Erro cadastroErro;

	public Aviso() {
		super();
	}

	public Aviso(String mensagem) {
		super();
		this.mensagem = mensagem;
	}
	
	public Aviso(int code, String mensagem) {
		super();
		this.code = code;
		this.mensagem = mensagem;
	}

	public Aviso(int code, String mensagem, String detalhe) {
		super();
		this.code = code;
		this.mensagem = mensagem;
		this.detalhe = detalhe;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getDetalhe() {
		return detalhe;
	}

	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}

	public Erro getCadastroErro() {
		return cadastroErro;
	}

	public void setCadastroErro(Erro cadastroErro) {
		this.cadastroErro = cadastroErro;
	}

}
