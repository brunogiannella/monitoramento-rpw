package br.com.rpw.monitoramento.api.dto;

public class SituacaoCameraDTO {

	private Long idSituacaoCamera;
	private String descricaoCamera;
	private Long idCamera;
	private Long idCliente;
	private Long idTurno;
	private Boolean ligada;
	private String dataHora;

	public Long getIdCamera() {
		return idCamera;
	}

	public void setIdCamera(Long idCamera) {
		this.idCamera = idCamera;
	}

	public Long getIdTurno() {
		return idTurno;
	}

	public void setIdTurno(Long idTurno) {
		this.idTurno = idTurno;
	}

	public Boolean getLigada() {
		return ligada;
	}

	public void setLigada(Boolean ligada) {
		this.ligada = ligada;
	}

	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}

	public Long getIdSituacaoCamera() {
		return idSituacaoCamera;
	}

	public void setIdSituacaoCamera(Long idSituacaoCamera) {
		this.idSituacaoCamera = idSituacaoCamera;
	}

	public String getDescricaoCamera() {
		return descricaoCamera;
	}

	public void setDescricaoCamera(String descricaoCamera) {
		this.descricaoCamera = descricaoCamera;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	
}
