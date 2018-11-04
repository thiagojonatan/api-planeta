import java.io.IOException;

import br.com.b2w.starwars.swapi.client.StarWarsApi;
import br.com.b2w.starwars.swapi.client.Swapi;
import br.com.b2w.starwars.swapi.client.modelo.Planet;
import br.com.b2w.starwars.swapi.client.modelo.SWModelList;
import retrofit2.Call;
import retrofit2.Response;

public class Main {

	public static void main(String[] args) throws IOException {
		Swapi api = StarWarsApi.getApi();
		
		Call<SWModelList<Planet>> planetas = api.getAllPlanets(1);
		
		Response<SWModelList<Planet>> response = planetas.execute();
		
		if(response.isSuccessful()) {
			SWModelList<Planet> data = response.body();
			data.getResults().forEach(item -> System.out.println(item.getName()));
		}

	}

}
