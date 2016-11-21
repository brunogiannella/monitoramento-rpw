package br.com.rpw.monitoramento.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rpw.monitoramento.api.dto.ChatDTO;
import br.com.rpw.monitoramento.api.dto.MensagemChatDTO;
import br.com.rpw.monitoramento.api.model.RestObject;
import br.com.rpw.monitoramento.api.model.Usuario;
import br.com.rpw.monitoramento.api.service.impl.ChatService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/chat")
public class ChatController {

	@Autowired
	private ChatService chatService;
	
	@RequestMapping(value="", method = RequestMethod.POST)
	public RestObject abrirNovoChat(@RequestBody ChatDTO chatDTO, @RequestHeader(value="x-acess-token") String token) { 
		try {
			chatService.abrirChat(chatDTO);
			return new RestObject(200, true, "Cadastro realizado com sucesso", "");
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro no cadastro do chat: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="/mensagem", method = RequestMethod.POST)
	public RestObject enviarMensagemChat(@RequestBody MensagemChatDTO mensagemChatDTO) { 
		try {
			chatService.enviarMensagem(mensagemChatDTO);
			return new RestObject(200, true, "Mensagem enviada com sucesso", "");
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro no envio da mensagem no chat: " + e.getMessage(), null);
		}
	}
		
	@RequestMapping(value="usuario/{id}", method = RequestMethod.GET)
	public RestObject consultarChatsCliente(@PathVariable Long id, @RequestHeader(value="x-acess-token") String token) { 
		try {
			Usuario usuario = new Usuario();
			usuario.setId(id);
			return new RestObject(200, true, "Consulta realizada com sucesso", chatService.consultarChatsUsuario(usuario));
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na consulta dos chats do usuário: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="/{id}/usuario/{idUsuario}", method = RequestMethod.GET)
	public RestObject consultarChat(@PathVariable Long id, @PathVariable Long idUsuario, @RequestHeader(value="x-acess-token") String token) { 
		try {
			return new RestObject(200, true, "Consulta realizada com sucesso", chatService.consultarChat(id, idUsuario));
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na consulta do chat: " + e.getMessage(), null);
		}
	}
	
}
