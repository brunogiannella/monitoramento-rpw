package br.com.rpw.monitoramento.api.dto;

import java.util.ArrayList;
import java.util.List;

public class ChatDTO {

	private Long id;
	private Long codigoUsuarioFrom;
	private Long codigoUsuarioTo;
	private String dataAbertura;
	private String assunto;
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

}
