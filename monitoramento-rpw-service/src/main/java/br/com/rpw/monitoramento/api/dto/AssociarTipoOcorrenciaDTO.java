package br.com.rpw.monitoramento.api.dto;

import java.util.List;

public class AssociarTipoOcorrenciaDTO {

	private Long idCliente;
	private List<Long> idTipoOcorrencia;

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public List<Long> getIdTipoOcorrencia() {
		return idTipoOcorrencia;
	}

	public void setIdTipoOcorrencia(List<Long> idTipoOcorrencia) {
		this.idTipoOcorrencia = idTipoOcorrencia;
	}

}
