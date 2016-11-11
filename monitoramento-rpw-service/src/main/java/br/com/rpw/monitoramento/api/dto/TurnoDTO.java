package br.com.rpw.monitoramento.api.dto;

import java.util.ArrayList;
import java.util.List;

public class TurnoDTO {

	private Long id;
	private String dataInicio;
	private String dataFim;
	private String nomeUsuario;
	private Long idUsuario;
	private String nomeCliente;
	private String liderSeguranca;
	private Long idCliente;
	private String periodo;
	private String condicaoClimatica;
	private String tempo;
	private String status;
	private List<String> operadores = new ArrayList<String>();
	private List<OcorrenciaDTO> ocorrenciasDto = new ArrayList<OcorrenciaDTO>();

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public List<OcorrenciaDTO> getOcorrenciasDto() {
		return ocorrenciasDto;
	}

	public void setOcorrenciasDto(List<OcorrenciaDTO> ocorrenciasDto) {
		this.ocorrenciasDto = ocorrenciasDto;
	}

	public List<String> getOperadores() {
		return operadores;
	}

	public void setOperadores(List<String> operadores) {
		this.operadores = operadores;
	}

	public String getLiderSeguranca() {
		return liderSeguranca;
	}

	public void setLiderSeguranca(String liderSeguranca) {
		this.liderSeguranca = liderSeguranca;
	}

}
