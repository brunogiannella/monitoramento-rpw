package br.com.rpw.monitoramento.api.dao.impl;

import org.springframework.stereotype.Component;

import br.com.rpw.monitoramento.api.dao.AbstractDao;
import br.com.rpw.monitoramento.api.dao.ITelefoneDao;
import br.com.rpw.monitoramento.api.model.Telefone;

@Component
public class TelefoneDaoImpl extends AbstractDao implements ITelefoneDao {

	@Override
	public void salvarTelefone(Telefone telefone) {
		persist(telefone);
	}

	@Override
	public void atualizarTelefone(Telefone telefone) {
		getSession().update(telefone);
	}
 
 
}
