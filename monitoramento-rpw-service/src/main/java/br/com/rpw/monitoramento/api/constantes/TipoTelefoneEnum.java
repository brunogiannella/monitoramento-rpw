package br.com.rpw.monitoramento.api.constantes;

public enum TipoTelefoneEnum {
	
	RESIDENCIAL("residencial"),
	COMERCIAL("comercial"),
	CELULAR("celular");

	public String descricao;
	
	TipoTelefoneEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
