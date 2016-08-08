package br.com.rpw.monitoramento.api.constantes;

public enum PeriodoEnum {
	
	DIURNO("diurno"),
	NOTURNO("noturno");

	public String descricao;
	
	PeriodoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
