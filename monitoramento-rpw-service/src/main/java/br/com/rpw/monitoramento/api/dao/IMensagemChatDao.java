package br.com.rpw.monitoramento.api.dao;

import java.util.List;

import br.com.rpw.monitoramento.api.model.Chat;
import br.com.rpw.monitoramento.api.model.MensagemChat;

public interface IMensagemChatDao {

    void salvarMensagemChat(MensagemChat mensagemChat);
    List<MensagemChat> listarMensagensChat(Chat chat);
	
}
