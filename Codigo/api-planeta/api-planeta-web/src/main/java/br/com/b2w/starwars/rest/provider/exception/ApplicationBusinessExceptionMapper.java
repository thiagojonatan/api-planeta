package br.com.b2w.starwars.rest.provider.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.b2w.starwars.enums.ConstantsMessage;
import br.com.b2w.starwars.exceptions.ApplicationBusinessException;
import br.com.b2w.starwars.rest.modelo.Aviso;
import br.com.b2w.starwars.rest.modelo.Erro;
import br.com.b2w.starwars.util.MessageUtil;

@Provider
public class ApplicationBusinessExceptionMapper implements ExceptionMapper<ApplicationBusinessException> {

	@Override
	public Response toResponse(ApplicationBusinessException exception) {
		
		String mensagem = MessageUtil.getMessage(ConstantsMessage.ERROR_00006.name());
		String mensagemErro = exception.getMensagem();
		
		if(mensagemErro != null) {
			mensagem = mensagemErro;
		}
		
		Aviso aviso = new Aviso(ConstantsMessage.ERROR_00006.getCodigo(), mensagem);
		Erro erro = new Erro();
		erro.setAlertas(exception.getAlertas());
		erro.setErros(exception.getErros());
		aviso.setCadastroErro(erro);
		return Response.status(aviso.getCode()).entity(aviso).type(MediaType.APPLICATION_JSON).build();
	}
}
