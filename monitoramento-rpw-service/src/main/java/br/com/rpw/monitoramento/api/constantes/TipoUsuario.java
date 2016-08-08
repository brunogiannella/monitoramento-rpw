package br.com.rpw.monitoramento.api.constantes;

public enum TipoUsuario {
	
	ADMINISTRADOR(1),
	SUPERVISOR(2),
	FUNCIONARIO(3),
	CLIENTE(4);

	public Integer codigo;
	
	TipoUsuario(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return codigo;
	}
	
}
