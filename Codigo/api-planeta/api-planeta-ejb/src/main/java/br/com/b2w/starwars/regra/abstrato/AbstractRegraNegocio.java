package br.com.b2w.starwars.regra.abstrato;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.b2w.starwars.enums.ValidacaoTipo;
import br.com.b2w.starwars.exceptions.BasicException;
import br.com.b2w.starwars.regra.RegraNegocio;
import br.com.b2w.starwars.util.ErroCampo;
import br.com.b2w.starwars.validacao.ValidacaoFalhaException;
import br.com.b2w.starwars.validacao.factory.ValidacaoFactory;

public abstract class AbstractRegraNegocio<T> implements RegraNegocio<T, ErroCampo> {

	protected List<ErroCampo> errosImpeditivos = new ArrayList<>();
	protected List<ErroCampo> errosAceitaveis = new ArrayList<>();
	protected List<ErroCampo> alertas = new ArrayList<>();

	public boolean addImpeditivo(ErroCampo erro) {
		if (erro != null)
			return errosImpeditivos.add(erro);

		return false;
	}

	public boolean addAceitavel(ErroCampo erro) {
		if (erro != null)
			return errosAceitaveis.add(erro);

		return false;
	}

	public boolean addAlerta(ErroCampo alerta) {
		if (alerta != null)
			return alertas.add(alerta);

		return false;
	}

	public boolean addAllImpeditivos(List<ErroCampo> erros) {
		if (erros != null)
			return errosImpeditivos.addAll(erros);

		return false;
	}

	public boolean addAllAceitaveis(List<ErroCampo> erros) {
		if (erros != null)
			return errosAceitaveis.addAll(erros);

		return false;
	}

	public boolean addAllAlertas(List<ErroCampo> alertas) {
		if (alertas != null)
			return this.alertas.addAll(alertas);

		return false;
	}

	public List<ErroCampo> getErrosImpeditivos() {
		return Collections.unmodifiableList(errosImpeditivos);
	}

	public List<ErroCampo> getErrosAceitaveis() {
		return Collections.unmodifiableList(errosAceitaveis);
	}

	public List<ErroCampo> getAlertas() {
		return Collections.unmodifiableList(alertas);
	}

	@Override
	public boolean existeErroImpeditivo() {
		return !errosImpeditivos.isEmpty();
	}

	@Override
	public boolean existeErroAceitavel() {
		return !errosAceitaveis.isEmpty();
	}

	@Override
	public boolean existeAlerta() {
		return !alertas.isEmpty();
	}

	@Override
	public void clearAllValidacoes() {
		alertas.clear();
		errosAceitaveis.clear();
		errosImpeditivos.clear();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void executarRegraNegocio(T object) throws BasicException {
		try {
			ValidacaoFactory.getValidacao(ValidacaoTipo.NULO).validar(object);

			personalizarRegraNegocio(object);

		} catch (ValidacaoFalhaException ex) {
			throw new IllegalStateException(ex);
		}
	}

	protected abstract void personalizarRegraNegocio(T object) throws BasicException;

}
