package br.com.rpw.monitoramento.api.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import br.com.rpw.monitoramento.api.dao.AbstractDao;
import br.com.rpw.monitoramento.api.dao.IClienteDao;
import br.com.rpw.monitoramento.api.model.Cliente;

@Component
public class ClienteDaoImpl extends AbstractDao implements IClienteDao{

	@Override
	public void salvarCliente(Cliente cliente) {
		persist(cliente);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> listarClientes() {
		Criteria criteria = getSession().createCriteria(Cliente.class);
        return (List<Cliente>) criteria.list();
	}

	@Override
	public void deleteCliente(Long codigoCliente) {
		Query query = getSession().createSQLQuery("delete from CLIENTE where id = :id");
        query.setLong("id", codigoCliente);
        query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> consultarClientes() {
		Criteria criteria = getSession().createCriteria(Cliente.class);
        //criteria.add(Restrictions.eq("ATIVO", true));
        return (List<Cliente>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> consultarCliente(String nomeCliente) {
		Criteria criteria = getSession().createCriteria(Cliente.class);
        criteria.add(Restrictions.like("NOME", nomeCliente));
        return (List<Cliente>) criteria.list();
	}

	@Override
	public Cliente consultarCliente(Long idCliente) {
		Criteria criteria = getSession().createCriteria(Cliente.class);
        criteria.add(Restrictions.eq("id",idCliente));
        return (Cliente) criteria.uniqueResult();
	}

	@Override
	public void atualizarCliente(Cliente Cliente) {
		getSession().update(Cliente);
	}
 
}
