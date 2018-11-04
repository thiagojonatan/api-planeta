package br.com.b2w.starwars.rest.provider.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import br.com.b2w.starwars.enums.ConstantsMessage;
import br.com.b2w.starwars.rest.modelo.Aviso;
import br.com.b2w.starwars.util.MessageUtil;

@Provider
public class RunTimeExceptionMapper implements ExceptionMapper<RuntimeException> {

	private static final Logger LOGGER = Logger.getLogger(RunTimeExceptionMapper.class);
	
	@Override
	public Response toResponse(RuntimeException exception) {
		Aviso erroDTO = null;
		
		
		if(exception instanceof javax.ejb.EJBTransactionRolledbackException){
			erroDTO = procuraException(exception);
			LOGGER.info(String.format("Usuário tentando inserir registro repetido."));
		}
		
		if(erroDTO == null) {
			erroDTO = new Aviso(ConstantsMessage.ERROR_00001.getCodigo(), MessageUtil.getMessage(ConstantsMessage.ERROR_00001.name()), exception.getMessage());
		}
		
		LOGGER.info(exception.getMessage(), exception);
		
		return Response.status(erroDTO.getCode()).entity(erroDTO).type(MediaType.APPLICATION_JSON).build();
	}
	
	private Aviso procuraException(Throwable ex) {
		
		if(ex == null){
			return null;
		}
		
		if(ex.getCause() instanceof java.sql.SQLIntegrityConstraintViolationException) {
			if(ex.getCause().getMessage().contains("restrição exclusiva") || ex.getCause().getMessage().contains("unique constraint")){
				return new Aviso(ConstantsMessage.ERROR_00002.getCodigo(), MessageUtil.getMessage(ConstantsMessage.ERROR_00002.name()), ex.getCause().getMessage());
			}
		}
		
		return procuraException(ex.getCause());
	}
}
