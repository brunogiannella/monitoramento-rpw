package br.com.rpw.monitoramento.api.constantes;

public enum TipoCameraEnum {
	
	FIXA("FIXA"),
	DOME("DOME"),
	CFTV("CFTV"),
	PP("PP"),
	CA("CA");

	public String descricao;
	
	TipoCameraEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
