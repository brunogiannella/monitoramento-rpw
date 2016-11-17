package br.com.rpw.monitoramento.api.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "CHAT")
public class Chat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO_FROM")
	private Usuario usuarioFrom;
	
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO_TO")
	private Usuario usuarioTo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_ABERTURA")
	private Date dataAbertura;
	
	@Column(name = "ASSUNTO", nullable = false)
	private String assunto;
	
	@Column(name = "ATIVO")
	private Boolean ativo = true;
	
	@Transient
	private List<MensagemChat> mensagens = new ArrayList<MensagemChat>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuarioFrom() {
		return usuarioFrom;
	}

	public void setUsuarioFrom(Usuario usuarioFrom) {
		this.usuarioFrom = usuarioFrom;
	}

	public Usuario getUsuarioTo() {
		return usuarioTo;
	}

	public void setUsuarioTo(Usuario usuarioTo) {
		this.usuarioTo = usuarioTo;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public List<MensagemChat> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<MensagemChat> mensagens) {
		this.mensagens = mensagens;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
}
