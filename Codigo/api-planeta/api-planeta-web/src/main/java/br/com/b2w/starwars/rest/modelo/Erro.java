package br.com.b2w.starwars.rest.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.b2w.starwars.util.ErroCampo;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Erro {

	private List<ErroCampo> erros;
	private List<ErroCampo> alertas;

	public List<ErroCampo> getErros() {
		return erros;
	}

	public void setErros(List<ErroCampo> erros) {
		this.erros = erros;
	}

	public List<ErroCampo> getAlertas() {
		return alertas;
	}

	public void setAlertas(List<ErroCampo> alertas) {
		this.alertas = alertas;
	}

	public boolean addErro(ErroCampo campo) {
		if (erros == null)
			erros = new ArrayList<>();

		return erros.add(campo);
	}

}
