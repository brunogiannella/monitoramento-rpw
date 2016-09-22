package br.com.rpw.monitoramento.api.dto;

import java.util.ArrayList;
import java.util.List;

public class ClienteDTO {

	private Long id;
	private String nome;
	private Boolean emailAutomatico;
	private String emailResposavel;
	private List<String> emailsRelatorioDiario = new ArrayList<String>();
	private List<String> emailsRelatorioMensal = new ArrayList<String>();
	private EnderecoDTO endereco = new EnderecoDTO();
	private List<CameraDTO> cameras;
	private List<EquipamentoDTO> equipamentos;
	private List<TipoOcorrenciaDTO> tiposOcorrencia;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getEmailAutomatico() {
		return emailAutomatico;
	}

	public void setEmailAutomatico(Boolean emailAutomatico) {
		this.emailAutomatico = emailAutomatico;
	}

	public String getEmailResposavel() {
		return emailResposavel;
	}

	public void setEmailResposavel(String emailResposavel) {
		this.emailResposavel = emailResposavel;
	}

	public List<String> getEmailsRelatorioDiario() {
		return emailsRelatorioDiario;
	}

	public void setEmailsRelatorioDiario(List<String> emailsRelatorioDiario) {
		this.emailsRelatorioDiario = emailsRelatorioDiario;
	}

	public List<String> getEmailsRelatorioMensal() {
		return emailsRelatorioMensal;
	}

	public void setEmailsRelatorioMensal(List<String> emailsRelatorioMensal) {
		this.emailsRelatorioMensal = emailsRelatorioMensal;
	}

	public EnderecoDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoDTO endereco) {
		this.endereco = endereco;
	}

	public List<CameraDTO> getCameras() {
		return cameras;
	}

	public void setCameras(List<CameraDTO> cameras) {
		this.cameras = cameras;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<TipoOcorrenciaDTO> getTiposOcorrencia() {
		return tiposOcorrencia;
	}

	public void setTiposOcorrencia(List<TipoOcorrenciaDTO> tiposOcorrencia) {
		this.tiposOcorrencia = tiposOcorrencia;
	}

	public List<EquipamentoDTO> getEquipamentos() {
		return equipamentos;
	}

	public void setEquipamentos(List<EquipamentoDTO> equipamentos) {
		this.equipamentos = equipamentos;
	}
	
}
