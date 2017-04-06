package br.com.rpw.monitoramento.api.dto;

import java.util.ArrayList;
import java.util.List;

public class QuantidadeOcorrenciasMesDetalhado {

	private String descricaoTipoOcorrencia;
	private List<Integer> quantidadeOcorrenciasDia = new ArrayList<Integer>();
	private Integer total;

	public String getDescricaoTipoOcorrencia() {
		return descricaoTipoOcorrencia;
	}

	public void setDescricaoTipoOcorrencia(String descricaoTipoOcorrencia) {
		this.descricaoTipoOcorrencia = descricaoTipoOcorrencia;
	}

	public List<Integer> getQuantidadeOcorrenciasDia() {
		return quantidadeOcorrenciasDia;
	}

	public void setQuantidadeOcorrenciasDia(List<Integer> quantidadeOcorrenciasDia) {
		this.quantidadeOcorrenciasDia = quantidadeOcorrenciasDia;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

}
