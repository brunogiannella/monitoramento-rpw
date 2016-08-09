package br.com.rpw.monitoramento.api.constantes;

public enum TipoUsuarioEnum {
	
	ADMINISTRADOR("ADMINISTRADOR"),
	SUPERVISOR("SUPERVISOR"),
	FUNCIONARIO("FUNCIONARIO"),
	CLIENTE("CLIENTE");

	public String descricao;
	
	TipoUsuarioEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
