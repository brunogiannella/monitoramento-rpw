package br.com.rpw.monitoramento.api.dto;

import java.util.ArrayList;
import java.util.List;

public class QuantidadeHorasEquipamentoRelatorio {

	private String descricaoEquipamento;
	private Long quantidadeHorasMes = 0L;
	private List<Long> quantidadeHorasDia = new ArrayList<Long>();

	public String getDescricaoEquipamento() {
		return descricaoEquipamento;
	}

	public void setDescricaoEquipamento(String descricaoEquipamento) {
		this.descricaoEquipamento = descricaoEquipamento;
	}

	public List<Long> getQuantidadeHorasDia() {
		return quantidadeHorasDia;
	}

	public void setQuantidadeHorasDia(List<Long> quantidadeHorasDia) {
		this.quantidadeHorasDia = quantidadeHorasDia;
	}

	public Long getQuantidadeHorasMes() {
		return quantidadeHorasMes;
	}

	public void setQuantidadeHorasMes(Long quantidadeHorasMes) {
		this.quantidadeHorasMes = quantidadeHorasMes;
	}

}
