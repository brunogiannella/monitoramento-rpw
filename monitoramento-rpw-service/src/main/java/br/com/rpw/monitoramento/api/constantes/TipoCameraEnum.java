package br.com.rpw.monitoramento.api.constantes;

public enum TipoCameraEnum {
	
	FIXA("FIXA"),
	DOME("SPEED DOME"),
	CFTV("CFTV"),
	PP("PP"),
	CA("CA"),
	ALARME("ALARME"),
	INFORMATICA("INFORMÁTICA"),
	CONTROLE_DE_ACESSO("CONTROLE DE ACESSO"),
	PERIMETRAL("PERIMETRAL DE ACESSO"),
	OUTROS("OUTROS");

	public String descricao;
	
	TipoCameraEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
