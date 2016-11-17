package br.com.rpw.monitoramento.api.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import br.com.rpw.monitoramento.api.dao.AbstractDao;
import br.com.rpw.monitoramento.api.dao.IChatDao;
import br.com.rpw.monitoramento.api.model.Chat;
import br.com.rpw.monitoramento.api.model.Usuario;

@Component
public class ChatDaoImpl extends AbstractDao implements IChatDao{

	@Override
	public void salvarChat(Chat chat) {
		persist(chat);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chat> listarChatsUsuario(Usuario usuario) {
		Criteria criteria = getSession().createCriteria(Chat.class);
		criteria.add(Restrictions.or(Restrictions.eq("usuarioTo.id", usuario.getId()), Restrictions.eq("usuarioFrom.id", usuario.getId())));
        return (List<Chat>) criteria.list();
	}

	@Override
	public void removerChat(Usuario usuarioSolicitante, Chat chat) throws Exception {
		Criteria criteria = getSession().createCriteria(Chat.class);
		criteria.add(Restrictions.eq("id", chat.getId()));
		Chat chatConsultado = (Chat) criteria.uniqueResult();
		
		if(chatConsultado != null) {
			if(chatConsultado.getUsuarioFrom().getId().equals(usuarioSolicitante.getId())) {
				chatConsultado.setAtivo(false);
				getSession().update(chatConsultado);
			} else {
				throw new Exception("O usuário informado não é o mesmo que abriu o chat!");
			}
		} else {
			throw new Exception("Ocorreu um problema ao remover o chat!");
		}
	}

	
	
}
