package br.com.b2w.starwars.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import br.com.b2w.starwars.resource.PlanetaResource;
import br.com.b2w.starwars.rest.provider.exception.ApplicationBusinessExceptionMapper;
import br.com.b2w.starwars.rest.provider.exception.BasicExceptionMapper;
import br.com.b2w.starwars.rest.provider.exception.RunTimeExceptionMapper;
import br.com.b2w.starwars.rest.provider.exception.ValidationExceptionMapper;

@ApplicationPath("/api")
public class RestApplication extends Application{
	
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<>();
		classes.add(PlanetaResource.class);
		classes.add(RunTimeExceptionMapper.class);		
		classes.add(BasicExceptionMapper.class);		
		classes.add(ApplicationBusinessExceptionMapper.class);		
		classes.add(ValidationExceptionMapper.class);
		return classes;
	}

}
