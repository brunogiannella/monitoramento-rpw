package br.com.rpw.monitoramento.api.service;

import java.util.List;

import br.com.rpw.monitoramento.api.dto.ChatDTO;
import br.com.rpw.monitoramento.api.dto.MensagemChatDTO;
import br.com.rpw.monitoramento.api.model.Usuario;

public interface IChatService {

	void abrirChat(ChatDTO chatDto);
	void enviarMensagem(MensagemChatDTO mensagemChatDto);
	ChatDTO consultarChat(Long idChat);
	List<ChatDTO> consultarChatsUsuario(Usuario usuario);	
	
}
