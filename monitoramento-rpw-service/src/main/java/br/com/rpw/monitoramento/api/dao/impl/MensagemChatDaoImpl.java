package br.com.rpw.monitoramento.api.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import br.com.rpw.monitoramento.api.dao.AbstractDao;
import br.com.rpw.monitoramento.api.dao.IMensagemChatDao;
import br.com.rpw.monitoramento.api.model.Chat;
import br.com.rpw.monitoramento.api.model.MensagemChat;

@Component
public class MensagemChatDaoImpl extends AbstractDao implements IMensagemChatDao {

	@Override
	public void salvarMensagemChat(MensagemChat mensagemChat) {
		persist(mensagemChat);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MensagemChat> listarMensagensChat(Chat chat) {
		Criteria criteria = getSession().createCriteria(MensagemChat.class);
		criteria.add(Restrictions.eq("chat.id", chat.getId()));
        return (List<MensagemChat>) criteria.list();
	}

	
}
