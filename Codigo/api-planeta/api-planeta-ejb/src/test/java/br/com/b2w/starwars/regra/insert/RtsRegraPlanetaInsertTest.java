package br.com.b2w.starwars.regra.insert;

import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

import br.com.b2w.starwars.bo.PlanetaBo;
import br.com.b2w.starwars.enums.ChaveMensagem;
import br.com.b2w.starwars.exceptions.BasicException;
import br.com.b2w.starwars.modelo.Planeta;
import br.com.b2w.starwars.util.MessageUtil;

public class RtsRegraPlanetaInsertTest {

	private Mockery context = new Mockery();
	private PlanetaBo bo = context.mock(PlanetaBo.class);
	private Planeta planeta = new Planeta();
	
	private RtsRegraPlanetaInsert regraInsert = new RtsRegraPlanetaInsert(bo);
	
	@Test
	public void verificarNomeExistente() throws BasicException {
		
		planeta.setNome("Teste");
		
		context.checking(new Expectations() {{
			oneOf(bo).existePorNome(planeta.getNome()); 
                        will(returnValue(false));
		}});
		
		regraInsert.validarNome(planeta);
		
		assertEquals(true, regraInsert.existeErroImpeditivo());
		assertEquals(MessageUtil.getMessage("nome"), regraInsert.getErrosImpeditivos().get(0).getDescricaoCampo());
		assertEquals(MessageUtil.getMessage(ChaveMensagem.NOME_INEXISTENTE.name()), regraInsert.getErrosImpeditivos().get(0).getMsgErro());
	}

}
