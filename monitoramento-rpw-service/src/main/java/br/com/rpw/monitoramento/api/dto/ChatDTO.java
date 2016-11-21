package br.com.rpw.monitoramento.api.dto;

import java.util.ArrayList;
import java.util.List;

public class ChatDTO {

	private Long id;
	private Long codigoUsuarioFrom;
	private String nomeUsuarioFrom;
	private Long codigoUsuarioTo;
	private String nomeUsuarioTo;
	private String dataAbertura;
	private String assunto;
	private boolean naoLida;
	private List<MensagemChatDTO> mensagens = new ArrayList<MensagemChatDTO>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCodigoUsuarioFrom() {
		return codigoUsuarioFrom;
	}

	public void setCodigoUsuarioFrom(Long codigoUsuarioFrom) {
		this.codigoUsuarioFrom = codigoUsuarioFrom;
	}

	public Long getCodigoUsuarioTo() {
		return codigoUsuarioTo;
	}

	public void setCodigoUsuarioTo(Long codigoUsuarioTo) {
		this.codigoUsuarioTo = codigoUsuarioTo;
	}

	public String getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(String dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public List<MensagemChatDTO> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<MensagemChatDTO> mensagens) {
		this.mensagens = mensagens;
	}

	public String getNomeUsuarioFrom() {
		return nomeUsuarioFrom;
	}

	public void setNomeUsuarioFrom(String nomeUsuarioFrom) {
		this.nomeUsuarioFrom = nomeUsuarioFrom;
	}

	public String getNomeUsuarioTo() {
		return nomeUsuarioTo;
	}

	public void setNomeUsuarioTo(String nomeUsuarioTo) {
		this.nomeUsuarioTo = nomeUsuarioTo;
	}

	public boolean isNaoLida() {
		return naoLida;
	}

	public void setNaoLida(boolean naoLida) {
		this.naoLida = naoLida;
	}
	
}
