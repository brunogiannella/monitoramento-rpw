package br.com.rpw.monitoramento.api.dto;

import java.util.ArrayList;
import java.util.List;

public class QuantidadeHorasEquipamentoRelatorio {

	private String descricaoEquipamento;
	private List<Integer> quantidadeHorasDia = new ArrayList<Integer>();

	public String getDescricaoEquipamento() {
		return descricaoEquipamento;
	}

	public void setDescricaoEquipamento(String descricaoEquipamento) {
		this.descricaoEquipamento = descricaoEquipamento;
	}

	public List<Integer> getQuantidadeHorasDia() {
		return quantidadeHorasDia;
	}

	public void setQuantidadeHorasDia(List<Integer> quantidadeHorasDia) {
		this.quantidadeHorasDia = quantidadeHorasDia;
	}

}
