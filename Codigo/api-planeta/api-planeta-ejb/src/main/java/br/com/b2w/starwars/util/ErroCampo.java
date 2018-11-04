package br.com.b2w.starwars.util;

public class ErroCampo {
	private String descricaoCampo;
	private String msgErro;
	private String valorCriticado;
	private Object valorChave;

	public ErroCampo(String campo, String msgErro, String valorCriticado,
			Object valorChave) {
		this.msgErro = msgErro;
		this.valorCriticado = valorCriticado;
		this.valorChave = valorChave;
		this.descricaoCampo = campo.toString() != null ? campo.toString() : null;
	}


	public String getDescricaoCampo() {
		return descricaoCampo;
	}

	public String getMsgErro() {
		return msgErro;
	}

	public String getValorCriticado() {
		return valorCriticado;
	}

	public void setValorCriticado(String valorCriticado) {
		this.valorCriticado = valorCriticado;
	}

	public Object getValorChave() {
		return valorChave;
	}

	public void setValorChave(Object valorChave) {
		this.valorChave = valorChave;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricaoCampo == null) ? 0 : descricaoCampo.hashCode());
		result = prime * result + ((valorCriticado == null) ? 0 : valorCriticado.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ErroCampo other = (ErroCampo) obj;
		if (descricaoCampo == null) {
			if (other.descricaoCampo != null)
				return false;
		} else if (!descricaoCampo.equals(other.descricaoCampo))
			return false;
		if (valorCriticado == null) {
			if (other.valorCriticado != null)
				return false;
		} else if (!valorCriticado.equals(other.valorCriticado))
			return false;
		return true;
	}


}
