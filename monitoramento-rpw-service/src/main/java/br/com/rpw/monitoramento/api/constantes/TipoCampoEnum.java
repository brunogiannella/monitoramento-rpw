package br.com.rpw.monitoramento.api.constantes;

public enum TipoCampoEnum {
	
	TEXTO("TEXTO"),
	EQUIPAMENTOS("EQUIPAMENTOS"),
	DATA("DATA");

	public String descricao;
	
	TipoCampoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
