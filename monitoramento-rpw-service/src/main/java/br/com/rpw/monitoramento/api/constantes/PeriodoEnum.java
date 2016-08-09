package br.com.rpw.monitoramento.api.constantes;

public enum PeriodoEnum {
	
	DIURNO("DIURNO"),
	NOTURNO("NOTURNO");

	public String descricao;
	
	PeriodoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
