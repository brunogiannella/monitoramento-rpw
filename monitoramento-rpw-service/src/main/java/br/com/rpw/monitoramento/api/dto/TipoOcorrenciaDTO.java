package br.com.rpw.monitoramento.api.dto;

import java.util.ArrayList;
import java.util.List;

public class TipoOcorrenciaDTO {

	private Long id;
	private String descricao;
	private Integer quantidadeCampos;
	private List<CampoOcorrenciaDTO> campos = new ArrayList<CampoOcorrenciaDTO>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<CampoOcorrenciaDTO> getCampos() {
		return campos;
	}

	public void setCampos(List<CampoOcorrenciaDTO> campos) {
		this.campos = campos;
	}

	public Integer getQuantidadeCampos() {
		return quantidadeCampos;
	}

	public void setQuantidadeCampos(Integer quantidadeCampos) {
		this.quantidadeCampos = quantidadeCampos;
	}
	
}
