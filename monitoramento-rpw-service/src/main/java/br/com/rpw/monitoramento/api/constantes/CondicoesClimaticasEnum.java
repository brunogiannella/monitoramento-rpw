package br.com.rpw.monitoramento.api.constantes;

public enum CondicoesClimaticasEnum {
	
	FRIO(1),
	CALOR(2);

	public Integer codigo;
	
	CondicoesClimaticasEnum(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return codigo;
	}
	
}
