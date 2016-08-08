package br.com.rpw.monitoramento.api.constantes;

public enum TipoUsuarioEnum {
	
	ADMINISTRADOR(1),
	SUPERVISOR(2),
	FUNCIONARIO(3),
	CLIENTE(4);

	public Integer codigo;
	
	TipoUsuarioEnum(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return codigo;
	}
	
}
