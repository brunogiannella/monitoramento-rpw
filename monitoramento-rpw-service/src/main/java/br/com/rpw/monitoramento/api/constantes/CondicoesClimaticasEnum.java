package br.com.rpw.monitoramento.api.constantes;

public enum CondicoesClimaticasEnum {
	
	FRIO("FRIO"),
	CALOR("CALOR");

	public String descricao;
	
	CondicoesClimaticasEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
