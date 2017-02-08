package br.com.rpw.monitoramento.api.dto;

public class QuantidadeOcorrencias {

	private String descricaoTipoOcorrencia;
	private Integer quantidadeOcorrencias;

	public String getDescricaoTipoOcorrencia() {
		return descricaoTipoOcorrencia;
	}

	public void setDescricaoTipoOcorrencia(String descricaoTipoOcorrencia) {
		this.descricaoTipoOcorrencia = descricaoTipoOcorrencia;
	}

	public Integer getQuantidadeOcorrencias() {
		return quantidadeOcorrencias;
	}

	public void setQuantidadeOcorrencias(Integer quantidadeOcorrencias) {
		this.quantidadeOcorrencias = quantidadeOcorrencias;
	}

}
