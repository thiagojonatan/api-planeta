package br.com.b2w.starwars.swapi.client;

import br.com.b2w.starwars.swapi.client.modelo.Planet;
import br.com.b2w.starwars.swapi.client.modelo.SWModelList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Swapi {

	@GET("planets/")
	Call<SWModelList<Planet>> getAllPlanets(@Query("page") Integer page);
	
}
