package br.com.rpw.monitoramento.api.constantes;

public enum TipoCampoEnum {
	
	TEXTO("ABERTO"),
	DATA("ENCOBERTO");

	public String descricao;
	
	TipoCampoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
