package br.com.rpw.monitoramento.api.constantes;

public enum InformanteOcorrenciaEnum {
	
	SEGURANCA("SEGURANCA"),
	MONITORAMENTO("MONITORAMENTO");

	public String descricao;
	
	InformanteOcorrenciaEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
