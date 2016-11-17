package br.com.rpw.monitoramento.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rpw.monitoramento.api.dao.impl.ChatDaoImpl;
import br.com.rpw.monitoramento.api.dao.impl.MensagemChatDaoImpl;
import br.com.rpw.monitoramento.api.dto.ChatDTO;
import br.com.rpw.monitoramento.api.dto.MensagemChatDTO;
import br.com.rpw.monitoramento.api.model.Usuario;
import br.com.rpw.monitoramento.api.service.IChatService;

@Service
@Transactional
public class ChatService implements IChatService {

	@Autowired
	private ChatDaoImpl chatDaoImpl;
	
	@Autowired
	private MensagemChatDaoImpl mensagemChatDaoImpl;
	
	@Override
	public void abrirChat(ChatDTO chatDto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enviarMensagem(MensagemChatDTO mensagemChatDto) {
		// TODO Auto-generated method stub

	}

	@Override
	public ChatDTO consultarChat(Long idChat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChatDTO> consultarChatsUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

}
