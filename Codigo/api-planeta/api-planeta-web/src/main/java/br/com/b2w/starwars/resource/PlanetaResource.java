package br.com.b2w.starwars.resource;

import java.net.URI;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import br.com.b2w.starwars.bo.PlanetaBo;
import br.com.b2w.starwars.exceptions.ApplicationBusinessException;
import br.com.b2w.starwars.exceptions.BasicException;
import br.com.b2w.starwars.modelo.Planeta;
import br.com.b2w.starwars.swapi.client.modelo.SWModelList;
import br.com.b2w.starwars.util.Constants;

@Path("/planetas")
@Produces({ "application/json; charset=UTF-8", MediaType.APPLICATION_XML })
@Consumes({ "application/json; charset=UTF-8", MediaType.APPLICATION_XML })
public class PlanetaResource {
	
	@Context
	private UriInfo uriInfo;
	
	@EJB
	private PlanetaBo bo;
	
	@GET
	public Response getAllPlanets(@QueryParam("page") @DefaultValue("1") Integer page) throws BasicException {
		
		SWModelList<Planeta> lista = new SWModelList<>();
		lista.setResults(bo.listar(page));
		lista.setCount((int)bo.contarTodos());
		if((page * Constants.SIZE) < lista.getCount()) {
			URI uri = UriBuilder.fromPath(uriInfo.getBaseUri() + "planetas/")
					.queryParam("page", page + 1)
					.build();
			lista.setNext(uri.toString());
		}
		else 
			lista.setNext(null);
		
		if(page > 1) {
			URI uri = UriBuilder.fromPath(uriInfo.getBaseUri() + "planetas/")
					.queryParam("page", page - 1)
					.build();
			lista.setPrevious(uri.toString());
		}
		
		GenericEntity<SWModelList<Planeta>> entity = new GenericEntity<SWModelList<Planeta>>(lista) {
		};
		
		return Response.ok(entity).build();
		
	}
	
	
	@GET
	@Path("{id:[0-9]{1,5}}")
	public Response getPlanet(@PathParam("id") int id) throws BasicException {
		
		Planeta planeta =  bo.buscar(id);
		return Response.ok(planeta).build();
	}
	
	@POST
	public Response inserir(Planeta planeta) throws BasicException, ApplicationBusinessException {
		
		bo.incluir(planeta);
		
		URI uri = UriBuilder.fromPath("planetas/{id}").build(planeta.getId());
			
		return Response.created(uri).entity(planeta).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response remover(@PathParam("id") Integer id) throws ApplicationBusinessException, BasicException{
		Planeta planeta =  bo.buscar(id);
		bo.excluir(planeta);
		return Response.ok().build();
	}

}
