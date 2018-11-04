package br.com.b2w.starwars.regra;

import java.util.List;

import br.com.b2w.starwars.exceptions.BasicException;

public interface RegraNegocio<T, E> {
	
	void executarRegraNegocio(T validado) throws BasicException;
	
	boolean addImpeditivo(E erro);
	
	boolean addAceitavel(E erro);
	
	boolean addAlerta(E alerta);
	
	boolean addAllImpeditivos(List<E> erros); 
	
	boolean addAllAceitaveis(List<E> erros);
	
	boolean addAllAlertas(List<E> alertas);
	
	List<E> getErrosImpeditivos();

	List<E> getErrosAceitaveis();

	List<E> getAlertas();
	
	boolean existeErroImpeditivo();
	
	boolean existeErroAceitavel();
	
	boolean existeAlerta();

	void clearAllValidacoes();
	
}
