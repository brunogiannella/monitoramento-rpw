package br.com.rpw.monitoramento.api.constantes;

public enum TipoUsuarioEnum {
	
	ADMINISTRADOR("admin"),
	SUPERVISOR("supervisor"),
	FUNCIONARIO("funcionario"),
	CLIENTE("cliente");

	public String descricao;
	
	TipoUsuarioEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
