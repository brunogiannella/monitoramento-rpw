package br.com.rpw.monitoramento.api.constantes;

public enum TipoEquipamentoEnum {
	
	FIXA("FIXA"),
	DOME("SPEED DOME"),
	CFTV("CFTV"),
	ALARME("ALARME"),
	INFORMATICA("INFORMÁTICA"),
	CONTROLE_DE_ACESSO("CONTROLE DE ACESSO"),
	PERIMETRAL("PERIMETRAL DE ACESSO"),
	OUTROS("OUTROS");

	public String descricao;
	
	TipoEquipamentoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
