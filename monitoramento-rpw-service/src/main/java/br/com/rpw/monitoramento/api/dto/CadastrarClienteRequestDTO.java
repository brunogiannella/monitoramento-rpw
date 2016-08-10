package br.com.rpw.monitoramento.api.dto;

import java.util.List;

public class CadastrarClienteRequestDTO {

	private String nome;
	private Boolean emailAutomatico;
	private String emailResposavel;
	private List<String> emailsRelatorioDiario;
	private List<String> emailsRelatorioMensal;
	private EnderecoDTO endereco;
	private List<CameraDTO> cameras;

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

}
