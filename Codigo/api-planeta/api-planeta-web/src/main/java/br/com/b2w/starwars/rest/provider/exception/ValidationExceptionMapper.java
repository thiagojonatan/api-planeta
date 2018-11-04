package br.com.b2w.starwars.rest.provider.exception;

import java.util.Iterator;
import java.util.List;

import javax.validation.ConstraintDeclarationException;
import javax.validation.ConstraintDefinitionException;
import javax.validation.GroupDefinitionException;
import javax.validation.ValidationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;
import org.jboss.resteasy.api.validation.ResteasyConstraintViolation;
import org.jboss.resteasy.api.validation.ResteasyViolationException;
import org.jboss.resteasy.api.validation.Validation;
import org.jboss.resteasy.api.validation.ViolationReport;

import br.com.b2w.starwars.enums.ConstantsMessage;
import br.com.b2w.starwars.rest.modelo.Aviso;
import br.com.b2w.starwars.rest.modelo.Erro;
import br.com.b2w.starwars.util.ErroCampo;
import br.com.b2w.starwars.util.MessageUtil;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

	private static final Logger LOGGER = Logger.getLogger(ValidationExceptionMapper.class);
	
    @Override
    public Response toResponse(ValidationException exception) {
    	
        if (exception instanceof ConstraintDefinitionException) {
            return buildResponse(unwrapException(exception), MediaType.TEXT_PLAIN, Status.INTERNAL_SERVER_ERROR);
        }
        if (exception instanceof ConstraintDeclarationException) {
            return buildResponse(unwrapException(exception), MediaType.TEXT_PLAIN, Status.INTERNAL_SERVER_ERROR);
        }
        if (exception instanceof GroupDefinitionException) { 
            return buildResponse(unwrapException(exception), MediaType.TEXT_PLAIN, Status.INTERNAL_SERVER_ERROR);
        }
        if (exception instanceof ResteasyViolationException) {
            ResteasyViolationException resteasyViolationException = ResteasyViolationException.class.cast(exception);
            Exception e = resteasyViolationException.getException();
            if (e != null) {
                return buildResponse(unwrapException(e), MediaType.TEXT_PLAIN, Status.INTERNAL_SERVER_ERROR);
            } else if (resteasyViolationException.getReturnValueViolations().size() == 0) {
            	
            	Aviso aviso = new Aviso(ConstantsMessage.ERROR_00006.getCodigo(), MessageUtil.getMessage(ConstantsMessage.ERROR_00006.name()));
            	Erro cadastroErro = new Erro();
            	aviso.setCadastroErro(cadastroErro);
            	for(ResteasyConstraintViolation violation : resteasyViolationException.getViolations()) {
            		String[] partesPath = violation.getPath().split("\\.");
            		
					if(partesPath.length == 3) {
            			ErroCampo campo = new ErroCampo(MessageUtil.getMessage(partesPath[2]), violation.getMessage(), violation.getValue(), null);
            			cadastroErro.addErro(campo);
					}
            	}

        		LOGGER.info(exception.getMessage(), resteasyViolationException);

        		return Response.status(aviso.getCode()).entity(aviso).type(MediaType.APPLICATION_JSON).build();
            	
            } else {
                return buildViolationReportResponse(resteasyViolationException, Status.INTERNAL_SERVER_ERROR);
            }
        }
        return buildResponse(unwrapException(exception), MediaType.TEXT_PLAIN, Status.INTERNAL_SERVER_ERROR);
    }

    protected Response buildResponse(Object entity, String mediaType, Status status) {
        ResponseBuilder builder = Response.status(status).entity(entity);
        builder.type(MediaType.TEXT_PLAIN);
        builder.header(Validation.VALIDATION_HEADER, "true");
        return builder.build();
    }

    protected Response buildViolationReportResponse(ResteasyViolationException exception, Status status) {
        ResponseBuilder builder = Response.status(status);
        builder.header(Validation.VALIDATION_HEADER, "true");

        // Check standard media types.
        MediaType mediaType = getAcceptMediaType(exception.getAccept());
        if (mediaType != null) {
            builder.type(mediaType);
            builder.entity(new ViolationReport(exception));
            return builder.build();
        }

        // Default media type.
        builder.type(MediaType.TEXT_PLAIN);
        builder.entity(exception.toString());
        return builder.build();
    }

    protected String unwrapException(Throwable t) {
        StringBuffer sb = new StringBuffer();
        doUnwrapException(sb, t);
        return sb.toString();
    }

    private void doUnwrapException(StringBuffer sb, Throwable t) {
        if (t == null) {
            return;
        }
        sb.append(t.toString());
        if (t.getCause() != null && t != t.getCause()) {
            sb.append('[');
            doUnwrapException(sb, t.getCause());
            sb.append(']');
        }
    }

    private MediaType getAcceptMediaType(List<MediaType> accept) {
        Iterator<MediaType> it = accept.iterator();
        while (it.hasNext()) {
            MediaType mt = it.next();
            if (MediaType.APPLICATION_JSON_TYPE.getType().equals(mt.getType())
                    && MediaType.APPLICATION_JSON_TYPE.getSubtype().equals(mt.getSubtype())) {
                return MediaType.APPLICATION_JSON_TYPE;
            }
        }
        return null;
    }
}
