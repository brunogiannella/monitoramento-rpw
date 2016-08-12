package br.com.rpw.monitoramento.api.dto;

public class IniciarTurnoRequestDTO {

	private String dataInicio;
	private String dataFim;
	private Long idUsuario;
	private Long idCliente;
	private String periodo;
	private String condicaoClimatica;
	private String tempo;

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getCondicaoClimatica() {
		return condicaoClimatica;
	}

	public void setCondicaoClimatica(String condicaoClimatica) {
		this.condicaoClimatica = condicaoClimatica;
	}

	public String getTempo() {
		return tempo;
	}

	public void setTempo(String tempo) {
		this.tempo = tempo;
	}

}
