package br.com.b2w.starwars.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(value=Include.NON_NULL)
public class Planeta {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
	@NotNull(message = "{cadastro.campo.notnull}")
	private String nome; 
	
	@NotNull(message = "{cadastro.campo.notnull}")
	private String clima;
	
	@NotNull(message = "{cadastro.campo.notnull}")
	private String terreno;
	
	private Integer quantidadeAparicao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}

	public String getTerreno() {
		return terreno;
	}

	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}

	public Integer getQuantidadeAparicao() {
		return quantidadeAparicao;
	}

	public void setQuantidadeAparicao(Integer quantidadeAparicao) {
		this.quantidadeAparicao = quantidadeAparicao;
	}
	
	
	
}
