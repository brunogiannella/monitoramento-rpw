package br.com.rpw.monitoramento.api.dto;

import java.util.ArrayList;
import java.util.List;

public class GrupoEquipamento {

	private String descricaoGrupoEquipamento;
	private List<QuantidadeHorasEquipamentoRelatorio> horasInoperantes = new ArrayList<QuantidadeHorasEquipamentoRelatorio>();

	public String getDescricaoGrupoEquipamento() {
		return descricaoGrupoEquipamento;
	}

	public void setDescricaoGrupoEquipamento(String descricaoGrupoEquipamento) {
		this.descricaoGrupoEquipamento = descricaoGrupoEquipamento;
	}

	public List<QuantidadeHorasEquipamentoRelatorio> getHorasInoperantes() {
		return horasInoperantes;
	}

	public void setHorasInoperantes(List<QuantidadeHorasEquipamentoRelatorio> horasInoperantes) {
		this.horasInoperantes = horasInoperantes;
	}

}
