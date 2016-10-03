package br.com.rpw.monitoramento.api.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import br.com.rpw.monitoramento.api.dao.AbstractDao;
import br.com.rpw.monitoramento.api.dao.IEquipamentoDao;
import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.Equipamento;

@Component
public class EquipamentoDaoImpl extends AbstractDao implements IEquipamentoDao {

	@Override
	public void salvarEquipamento(Equipamento equipamento) {
		equipamento.setAtivo(true);
		persist(equipamento);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Equipamento> listarEquipamentos(Cliente cliente) {
		Criteria criteria = getSession().createCriteria(Equipamento.class);
		criteria.add(Restrictions.eq("cliente.id", cliente.getId()));
		criteria.add(Restrictions.eq("ativo", true));
        return (List<Equipamento>) criteria.list();
	}
	
	@Override
	public void deleteEquipamentosByCliente(Long codigoCliente) {
		Query query = getSession().createSQLQuery("delete from EQUIPAMENTO where ID_CLIENTE = :id");
        query.setLong("id", codigoCliente);
        query.executeUpdate();
	}

	@Override
	public void deleteEquipamento(Long codigoEquipamento) {
		Equipamento equipamento = consultarEquipamento(codigoEquipamento);
		equipamento.setAtivo(false);
		atualizarEquipamento(equipamento);
	}

	@Override
	public Equipamento consultarEquipamento(Long idEquipamento) {
		Criteria criteria = getSession().createCriteria(Equipamento.class);
        criteria.add(Restrictions.eq("id",idEquipamento));
        criteria.add(Restrictions.eq("ativo", true));
        return (Equipamento) criteria.uniqueResult();
	}

	@Override
	public void atualizarEquipamento(Equipamento equipamento) {
		getSession().update(equipamento);
	}

}
