package br.com.b2w.starwars.swapi.client.modelo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Planet implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String diameter;
	private String gravity;
	private String population;
	private String climate;
	private String terrain;
	private String created;
	private String edited;
	private String url;

    @JsonProperty("rotation_period")
    private String rotationPeriod;

    @JsonProperty("orbital_period")
    private String orbitalPeriod;

    @JsonProperty("surface_water")
    private String surfaceWater;

    @JsonProperty("residents")
    private List<String> residentsUrls;

    @JsonProperty("films")
    private List<String> filmsUrls;

	public String getName() {
		return name;
	}

	public String getDiameter() {
		return diameter;
	}

	public String getGravity() {
		return gravity;
	}

	public String getPopulation() {
		return population;
	}

	public String getClimate() {
		return climate;
	}

	public String getTerrain() {
		return terrain;
	}

	public String getCreated() {
		return created;
	}

	public String getEdited() {
		return edited;
	}

	public String getUrl() {
		return url;
	}

	public String getRotationPeriod() {
		return rotationPeriod;
	}

	public String getOrbitalPeriod() {
		return orbitalPeriod;
	}

	public String getSurfaceWater() {
		return surfaceWater;
	}

	public List<String> getResidentsUrls() {
		return residentsUrls;
	}

	public List<String> getFilmsUrls() {
		return filmsUrls;
	}
    
}
