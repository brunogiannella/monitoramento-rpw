package br.com.rpw.monitoramento.api.constantes;

public enum TipoTelefoneEnum {
	
	RESIDENCIAL("RESIDENCIAL"),
	COMERCIAL("COMERCIAL"),
	CELULAR("CELULAR");

	public String descricao;
	
	TipoTelefoneEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
