package br.com.b2w.starwars.bo.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.b2w.starwars.bo.PlanetaBo;
import br.com.b2w.starwars.exceptions.ApplicationBusinessException;
import br.com.b2w.starwars.exceptions.BasicException;
import br.com.b2w.starwars.exceptions.ErroInesperadoException;
import br.com.b2w.starwars.exceptions.ObjetoNaoEncontradoException;
import br.com.b2w.starwars.modelo.Planeta;
import br.com.b2w.starwars.persistencia.dao.GenericDAO;
import br.com.b2w.starwars.persistencia.dao.PlanetaDAO;
import br.com.b2w.starwars.regra.RegraPlaneta;
import br.com.b2w.starwars.regra.insert.RtsRegraPlanetaInsert;
import br.com.b2w.starwars.swapi.client.StarWarsApi;
import br.com.b2w.starwars.swapi.client.Swapi;
import br.com.b2w.starwars.swapi.client.modelo.Planet;
import br.com.b2w.starwars.swapi.client.modelo.SWModelList;
import retrofit2.Call;
import retrofit2.Response;

@Stateless
public class StarWarsPlanetaBo extends StarWarsGenericBo<Planeta> implements PlanetaBo {

	@EJB
	private PlanetaDAO dao;
	
	private RegraPlaneta regra;

	
	public StarWarsPlanetaBo() {
		this.regra = new RtsRegraPlanetaInsert(this);
	}

	@Override
	public void incluir(Planeta obj) throws ApplicationBusinessException, BasicException {
		Planet planet;
		try {
			planet = buscarPorNomeApi(obj.getNome());
			if(planet != null)
				obj.setQuantidadeAparicao(planet.getFilmsUrls().size());
			super.incluir(obj);
		}catch (ApplicationBusinessException | BasicException e) {
			throw e;
		}catch (Exception e) {
			throw new ErroInesperadoException();
		}
	}

	@Override
	public boolean existePorNome(String nome) throws BasicException {
		
		try {
			Planet planet = buscarPorNomeApi(nome);
			return planet != null;
				
		} catch (Exception e) {
			throw new ErroInesperadoException();
		}
	}

	private Planet buscarPorNomeApi(String nome) throws Exception {
			Swapi api = StarWarsApi.getApi();
		
			int page = 1;
			SWModelList<Planet> data = null;
			do {
			
				Call<SWModelList<Planet>> planetas = api.getAllPlanets(page++);
				
				Response<SWModelList<Planet>> response = planetas.execute();
				
				if(response.isSuccessful()) {
					data = response.body();
					
						for(Planet planet : data.getResults()) {
							if(nome.equalsIgnoreCase(planet.getName())) {
								return planet;
							}
						}
						
						
				}
			} while(data != null && data.hasMore());
			
			return null;
	}

	@Override
	protected GenericDAO<Planeta> getDao() {
		return dao;
	}

	@Override
	public long contarTodos() {
		return dao.contarTodos();
	}

	@Override
	public List<Planeta> listar(Integer page) throws ObjetoNaoEncontradoException {
		List<Planeta> planetas = dao.listar(page);
		
		if(planetas.isEmpty())
			throw new ObjetoNaoEncontradoException();
		
		return planetas;
	}
	
	@Override
	public void criticar(Planeta obj) throws ApplicationBusinessException, BasicException {
		regra.clearAllValidacoes();
		regra.executarRegraNegocio(obj);
		
		if(regra.existeErroImpeditivo()) {
			throw new ApplicationBusinessException(regra.getErrosImpeditivos(), regra.getErrosAceitaveis(), regra.getAlertas());
		}
	}

}
