package br.com.rpw.monitoramento.api.constantes;

public enum StatusTurnoEnum {
	
	EM_ANDAMENTO("EM_ANDAMENTO"),
	AGUARDANDO_VALIDACAO("AGUARDANDO_VALIDACAO"),
	APROVADO("APROVADO"),
	ENVIADO("ENVIADO");

	public String descricao;
	
	StatusTurnoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
