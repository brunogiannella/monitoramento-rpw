package br.com.rpw.monitoramento.api.dto;

import java.util.ArrayList;
import java.util.List;

public class GrupoOcorrenciasDto {
	private String descricao;
	private Boolean personalizada = false;
	private List<OcorrenciaDTO> ocorrenciasDto = new ArrayList<OcorrenciaDTO>();

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<OcorrenciaDTO> getOcorrenciasDto() {
		return ocorrenciasDto;
	}

	public void setOcorrenciasDto(List<OcorrenciaDTO> ocorrenciasDto) {
		this.ocorrenciasDto = ocorrenciasDto;
	}

	public Boolean getPersonalizada() {
		return personalizada;
	}

	public void setPersonalizada(Boolean personalizada) {
		this.personalizada = personalizada;
	}
	
}
