package br.com.rpw.monitoramento.api.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.rpw.monitoramento.api.dto.ocorrenciasPersonalizadas.chamadaPosto.ChamadaPostoDTO;

public class TurnoDTO {

	private Long id;
	private String dataInicio;
	private String dataFim;
	private String nomeUsuario;
	private Long idUsuario;
	private String nomeCliente;
	private String liderSeguranca;
	private Long idCliente;
	private List<String> emailsRelatorioDiario = new ArrayList<String>();
	private String periodo;
	private String status;
	private List<String> operadores = new ArrayList<String>();
	private List<GrupoOcorrenciasDto> ocorrenciasDto = new ArrayList<GrupoOcorrenciasDto>();
	private List<GrupoOcorrenciasDto> ocorrenciasPersonalizadasDto = new ArrayList<GrupoOcorrenciasDto>();
	private List<ImagemCameraDTO> imagemCameraDto = new ArrayList<ImagemCameraDTO>();
	private List<DetalheInoperanciaCameraDTO> detalhesInoperanciaCamera = new ArrayList<DetalheInoperanciaCameraDTO>();
	private ChamadaPostoDTO chamadaPostoDTO;

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

	public List<GrupoOcorrenciasDto> getOcorrenciasDto() {
		return ocorrenciasDto;
	}

	public void setOcorrenciasDto(List<GrupoOcorrenciasDto> ocorrenciasDto) {
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

	public List<ImagemCameraDTO> getImagemCameraDto() {
		return imagemCameraDto;
	}

	public void setImagemCameraDto(List<ImagemCameraDTO> imagemCameraDto) {
		this.imagemCameraDto = imagemCameraDto;
	}

	public List<DetalheInoperanciaCameraDTO> getDetalhesInoperanciaCamera() {
		return detalhesInoperanciaCamera;
	}

	public void setDetalhesInoperanciaCamera(List<DetalheInoperanciaCameraDTO> detalhesInoperanciaCamera) {
		this.detalhesInoperanciaCamera = detalhesInoperanciaCamera;
	}

	public ChamadaPostoDTO getChamadaPostoDTO() {
		return chamadaPostoDTO;
	}

	public void setChamadaPostoDTO(ChamadaPostoDTO chamadaPostoDTO) {
		this.chamadaPostoDTO = chamadaPostoDTO;
	}

	public List<GrupoOcorrenciasDto> getOcorrenciasPersonalizadasDto() {
		return ocorrenciasPersonalizadasDto;
	}

	public void setOcorrenciasPersonalizadasDto(List<GrupoOcorrenciasDto> ocorrenciasPersonalizadasDto) {
		this.ocorrenciasPersonalizadasDto = ocorrenciasPersonalizadasDto;
	}

	public List<String> getEmailsRelatorioDiario() {
		return emailsRelatorioDiario;
	}

	public void setEmailsRelatorioDiario(List<String> emailsRelatorioDiario) {
		this.emailsRelatorioDiario = emailsRelatorioDiario;
	}

}
