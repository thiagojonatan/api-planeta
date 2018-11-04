package br.com.b2w.starwars.persistencia.dao.mongo;

import javax.ejb.Stateless;

import br.com.b2w.starwars.modelo.Planeta;
import br.com.b2w.starwars.persistencia.dao.PlanetaDAO;

@Stateless
public class MongoPlanetaDAO extends MongoGenericDao<Planeta> implements PlanetaDAO {


}
