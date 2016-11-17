package br.com.rpw.monitoramento.api.dto;

public class MensagemChatDTO {

	private Long id;
	private Long codigoUsuarioFrom;
	private String nomeUsuarioFrom;
	private String dataEnvio;
	private Long idChat;
	private String mensagem;

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

	public String getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(String dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Long getIdChat() {
		return idChat;
	}

	public void setIdChat(Long idChat) {
		this.idChat = idChat;
	}

	public String getNomeUsuarioFrom() {
		return nomeUsuarioFrom;
	}

	public void setNomeUsuarioFrom(String nomeUsuarioFrom) {
		this.nomeUsuarioFrom = nomeUsuarioFrom;
	}

}
