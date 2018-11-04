package br.com.b2w.starwars.swapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import br.com.b2w.starwars.swapi.client.StarWarsApi;
import br.com.b2w.starwars.swapi.client.Swapi;
import br.com.b2w.starwars.swapi.client.modelo.Planet;
import br.com.b2w.starwars.swapi.client.modelo.SWModelList;
import retrofit2.Call;
import retrofit2.Response;

public class SimpleTest {
	
	private final Logger log = Logger.getLogger(this.getClass());

    private Swapi api;

    @Before
    public void setUp() {
        api = StarWarsApi.getApi();
    }

    @Test
    public void getAllPlanets() throws Exception {
        Call<SWModelList<Planet>> planets = api.getAllPlanets(null);

        Response<SWModelList<Planet>> response = planets.execute();
        if (response.isSuccessful()) {
            SWModelList<Planet> data = response.body();
            int count = data.getCount();
            assertThat(count).isNotZero().isGreaterThan(0);
        }
        else {
            log.error(response.code() + " - " + response.message());
            fail("Request failed");
        }
    }


}
