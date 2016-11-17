package br.com.rpw.monitoramento.api.dao;

import java.util.List;

import br.com.rpw.monitoramento.api.model.Chat;
import br.com.rpw.monitoramento.api.model.Usuario;

public interface IChatDao {

    void salvarChat(Chat chat);
    List<Chat> listarChatsUsuario(Usuario usuario);
    void removerChat(Usuario usuarioSolicitante, Chat chat) throws Exception;
	
}
