package br.com.rpw.monitoramento.api.model;

import java.util.Date;

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

@Entity
@Table(name = "MENSAGEM_CHAT")
public class MensagemChat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ID_CHAT")
	private Chat chat;
	
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuarioFrom;
	
	@Column(name = "MENSAGEM", nullable = false)
	private String mensagem;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_ENVIO")
	private Date dataEnvio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	public Usuario getUsuarioFrom() {
		return usuarioFrom;
	}

	public void setUsuarioFrom(Usuario usuarioFrom) {
		this.usuarioFrom = usuarioFrom;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Date getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

}
