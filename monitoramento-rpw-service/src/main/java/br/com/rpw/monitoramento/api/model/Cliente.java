package br.com.rpw.monitoramento.api.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "CLIENTE")
public class Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -199503260103233447L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NOME", nullable = false)
	private String nome;

	@ManyToOne
	@JoinColumn(name = "ID_ENDERECO")
	private Endereco endereco;

	@Column(name = "EMAIL_AUTOMATICO", nullable = false)
	private Boolean emailAutomatico;

	@Column(name = "EMAIL_RESPONSAVEL")
	private String emailResposavel;

	@Column(name = "EMAILS_RELATORIO_DIARIO")
	private String emailsRelatorioDiario;

	@Column(name = "EMAILS_RELATORIO_MENSAL")
	private String emailsRelatorioMensal;
	
	@Column(name = "ATIVO")
	private Boolean ativo = true;

	@Transient
	private List<Camera> cameras;
	
	@Transient
	private List<TipoOcorrencia> tipoOcorrencias;
	
	@Transient
	private List<TipoOcorrenciaPersonalizada> tipoOcorrenciasPersonalizada;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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

	public String getEmailsRelatorioDiario() {
		return emailsRelatorioDiario;
	}

	public void setEmailsRelatorioDiario(String emailsRelatorioDiario) {
		this.emailsRelatorioDiario = emailsRelatorioDiario;
	}

	public String getEmailsRelatorioMensal() {
		return emailsRelatorioMensal;
	}

	public void setEmailsRelatorioMensal(String emailsRelatorioMensal) {
		this.emailsRelatorioMensal = emailsRelatorioMensal;
	}

	public List<Camera> getCameras() {
		return cameras;
	}

	public void setCameras(List<Camera> cameras) {
		this.cameras = cameras;
	}

	public List<TipoOcorrencia> getTipoOcorrencias() {
		return tipoOcorrencias;
	}

	public void setTipoOcorrencias(List<TipoOcorrencia> tipoOcorrencias) {
		this.tipoOcorrencias = tipoOcorrencias;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public List<TipoOcorrenciaPersonalizada> getTipoOcorrenciasPersonalizada() {
		return tipoOcorrenciasPersonalizada;
	}

	public void setTipoOcorrenciasPersonalizada(List<TipoOcorrenciaPersonalizada> tipoOcorrenciasPersonalizada) {
		this.tipoOcorrenciasPersonalizada = tipoOcorrenciasPersonalizada;
	}
	
}
