package br.com.rpw.monitoramento.api.model;

import java.io.Serializable;
import java.util.List;

import br.com.rpw.monitoramento.api.dto.ClienteDTO;
import br.com.rpw.monitoramento.api.dto.GrupoOcorrenciasDto;
import br.com.rpw.monitoramento.api.dto.ImagemCameraDTO;

public class RelatorioMensal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2484642518846104918L;
	
	private List<GrupoOcorrenciasDto> grupoOcorrencias;
	private List<ImagemCameraDTO> imagemCamera;
	private ClienteDTO cliente;
	private String ano;
	private String mes;

	public List<GrupoOcorrenciasDto> getGrupoOcorrencias() {
		return grupoOcorrencias;
	}

	public void setGrupoOcorrencias(List<GrupoOcorrenciasDto> grupoOcorrencias) {
		this.grupoOcorrencias = grupoOcorrencias;
	}

	public List<ImagemCameraDTO> getImagemCamera() {
		return imagemCamera;
	}

	public void setImagemCamera(List<ImagemCameraDTO> imagemCamera) {
		this.imagemCamera = imagemCamera;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

}
