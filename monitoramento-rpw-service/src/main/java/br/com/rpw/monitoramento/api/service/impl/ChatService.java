package br.com.rpw.monitoramento.api.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rpw.monitoramento.api.dao.impl.ChatDaoImpl;
import br.com.rpw.monitoramento.api.dao.impl.MensagemChatDaoImpl;
import br.com.rpw.monitoramento.api.dto.ChatDTO;
import br.com.rpw.monitoramento.api.dto.MensagemChatDTO;
import br.com.rpw.monitoramento.api.model.Chat;
import br.com.rpw.monitoramento.api.model.MensagemChat;
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
		Chat chat = converterChatDTOEmChat(chatDto);
		chatDaoImpl.salvarChat(chat);
	}

	@Override
	public void enviarMensagem(MensagemChatDTO mensagemChatDto) {
		MensagemChat mensagemChat = converterMensagemDTOEmMensagem(mensagemChatDto);
		mensagemChatDaoImpl.salvarMensagemChat(mensagemChat);
	}

	@Override
	public ChatDTO consultarChat(Long idChat) {
		Chat chat = chatDaoImpl.consultarChat(idChat);
		chat.setMensagens(mensagemChatDaoImpl.listarMensagensChat(chat));
		return converterChatEmChatDTO(chat, true);
	}

	@Override
	public List<ChatDTO> consultarChatsUsuario(Usuario usuario) {
		List<Chat> chats = chatDaoImpl.listarChatsUsuario(usuario);
		
		List<ChatDTO> chatsDto = new ArrayList<ChatDTO>();
		for(Chat chatAtual : chats) {
			chatsDto.add(converterChatEmChatDTO(chatAtual, false));
		}
		
		return chatsDto;
	}

	private ChatDTO converterChatEmChatDTO(Chat chat, boolean completo) {
		ChatDTO chatDto = new ChatDTO();
		
		chatDto.setAssunto(chat.getAssunto());
		chatDto.setCodigoUsuarioFrom(chat.getUsuarioFrom().getId());
		chatDto.setCodigoUsuarioTo(chat.getUsuarioTo().getId());
		chatDto.setNomeUsuarioFrom(chat.getUsuarioFrom().getNome());
		chatDto.setNomeUsuarioTo(chat.getUsuarioTo().getNome());
		chatDto.setDataAbertura("");
		chatDto.setId(chat.getId());
		
		if(completo) {
			if(chat.getMensagens() != null) {
				List<MensagemChatDTO> mensagensDto = new ArrayList<MensagemChatDTO>();
				for(MensagemChat mensagem : chat.getMensagens()) {
					mensagensDto.add(converterMensagemEmMensagemDTO(mensagem));
				}
				
				chatDto.setMensagens(mensagensDto);
			}
		}
		
		return chatDto;
	}
	
	private Chat converterChatDTOEmChat(ChatDTO chatDto) {
		Chat chat = new Chat();
		
		if(chatDto.getId() != null) {
			chat.setId(chatDto.getId());
		}
		
		chat.setAssunto(chatDto.getAssunto());
		chat.setDataAbertura(new Date());
		chat.setUsuarioFrom(new Usuario());
		chat.getUsuarioFrom().setId(chatDto.getCodigoUsuarioFrom());
		chat.setUsuarioTo(new Usuario());
		chat.getUsuarioTo().setId(chatDto.getCodigoUsuarioTo());
		
		return chat;
	}
	
	private MensagemChat converterMensagemDTOEmMensagem(MensagemChatDTO mensagemDto) {
		MensagemChat mensagemChat = new MensagemChat();
		
		if(mensagemDto.getId() != null) {
			mensagemChat.setId(mensagemDto.getId());
		}
		
		mensagemChat.setMensagem(mensagemDto.getMensagem());
		mensagemChat.setDataEnvio(new Date());
		mensagemChat.setUsuarioFrom(new Usuario());
		mensagemChat.getUsuarioFrom().setId(mensagemDto.getCodigoUsuarioFrom());
		mensagemChat.setChat(new Chat());
		mensagemChat.getChat().setId(mensagemDto.getIdChat());
		
		return mensagemChat;
	}
	
	private MensagemChatDTO converterMensagemEmMensagemDTO(MensagemChat mensagem) {
		MensagemChatDTO mensagemChatDto = new MensagemChatDTO();
		
		mensagemChatDto.setId(mensagem.getId());
		mensagemChatDto.setMensagem(mensagem.getMensagem());
		mensagemChatDto.setCodigoUsuarioFrom(mensagem.getUsuarioFrom().getId());
		mensagemChatDto.setIdChat(mensagem.getChat().getId());
		mensagemChatDto.setNomeUsuarioFrom(mensagem.getUsuarioFrom().getNome());
		mensagemChatDto.setDataEnvio("");
		
		return mensagemChatDto;
	}
	
}
