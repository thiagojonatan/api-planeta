package br.com.b2w.starwars.enums;

public enum ConstantsMessage {

	ERROR_00000(404), ERROR_00001(500), ERROR_00002(412), 
	ERROR_00003(401), ERROR_00004(403), ERROR_00005(400),
	ERROR_00006(422), ERROR_00007(401), ERROR_00008(422);
	
	int codigoHtml;
	
	private ConstantsMessage(int codigoHtml) {
		this.codigoHtml = codigoHtml;
	}
	
	public int getCodigo() {
		return codigoHtml;
	}
}
