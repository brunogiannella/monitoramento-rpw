package br.com.rpw.monitoramento.api.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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

	@OneToMany(mappedBy = "cliente", targetEntity = Camera.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Camera> cameras;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "cliente_ocorrencias", catalog="monitoramentorpw", joinColumns = {
			@JoinColumn(name = "TIPO_CAMERA_ID", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "CLIENTE_ID",
					nullable = false, updatable = false) })
	private Set<TipoOcorrencia> tiposOcorrencia = new HashSet<TipoOcorrencia>(0);

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

	public Set<Camera> getCameras() {
		return cameras;
	}

	public void setCameras(Set<Camera> cameras) {
		this.cameras = cameras;
	}

	public Set<TipoOcorrencia> getTiposOcorrencia() {
		return tiposOcorrencia;
	}

	public void setTiposOcorrencia(Set<TipoOcorrencia> tiposOcorrencia) {
		this.tiposOcorrencia = tiposOcorrencia;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
}
