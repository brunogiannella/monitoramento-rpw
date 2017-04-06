package br.com.rpw.monitoramento.api.dao.impl;

import org.springframework.stereotype.Component;

import br.com.rpw.monitoramento.api.dao.AbstractDao;
import br.com.rpw.monitoramento.api.dao.IRastreabilidadeDao;
import br.com.rpw.monitoramento.api.model.Rastreabilidade;

@Component
public class RastreabilidadeDaoImpl extends AbstractDao implements IRastreabilidadeDao {

	@Override
	public void salvarRastreabilidade(Rastreabilidade rastreabilidade) {
		persist(rastreabilidade);
	}
 
}
