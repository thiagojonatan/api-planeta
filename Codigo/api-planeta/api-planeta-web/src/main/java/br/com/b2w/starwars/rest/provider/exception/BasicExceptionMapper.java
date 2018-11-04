package br.com.b2w.starwars.rest.provider.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import br.com.b2w.starwars.exceptions.BasicException;
import br.com.b2w.starwars.rest.modelo.Aviso;

@Provider
public class BasicExceptionMapper implements ExceptionMapper<BasicException> {

	private static final Logger LOGGER = Logger.getLogger(BasicExceptionMapper.class);

	@Override
	public Response toResponse(BasicException exception) {
		Aviso aviso = null;
		aviso = new Aviso(exception.getCode(), exception.getMessage());

		LOGGER.info(exception.getMessage(), exception);

		return Response.status(aviso.getCode()).entity(aviso).type(MediaType.APPLICATION_JSON).build();
	}
}
