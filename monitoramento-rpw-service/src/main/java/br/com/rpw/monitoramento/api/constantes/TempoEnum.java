package br.com.rpw.monitoramento.api.constantes;

public enum TempoEnum {
	
	ABERTO("ABERTO"),
	ENCOBERTO("ENCOBERTO"),
	CHUVA("CHUVA");

	public String descricao;
	
	TempoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
