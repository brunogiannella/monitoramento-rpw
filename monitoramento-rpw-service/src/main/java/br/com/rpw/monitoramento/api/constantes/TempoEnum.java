package br.com.rpw.monitoramento.api.constantes;

public enum TempoEnum {
	
	ABERTO(1),
	ENCOBERTO(2),
	CHUVA(2);

	public Integer codigo;
	
	TempoEnum(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return codigo;
	}
	
}
