package br.com.rpw.monitoramento.api.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import br.com.rpw.monitoramento.api.constantes.StatusTurnoEnum;
import br.com.rpw.monitoramento.api.dao.AbstractDao;
import br.com.rpw.monitoramento.api.dao.ITurnoDao;
import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.Turno;
import br.com.rpw.monitoramento.api.model.Usuario;

@Component
public class TurnoDaoImpl extends AbstractDao implements ITurnoDao {

	@Override
	public void salvarTurno(Turno turno) {
		persist(turno);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Turno> listarTurnos() {
		Criteria criteria = getSession().createCriteria(Turno.class);
        return (List<Turno>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Turno> consultarTurnoAnterior(Cliente cliente) {
		Criteria criteria = getSession().createCriteria(Turno.class);
		criteria.add(Restrictions.eq("cliente.id",cliente.getId()));
		criteria.addOrder(Order.desc("id"));
		criteria.setMaxResults(2);
		return (List<Turno>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Turno> listarTurnos(Cliente cliente) {
		Criteria criteria = getSession().createCriteria(Turno.class);
		criteria.add(Restrictions.eq("cliente.id",cliente.getId()));
        return (List<Turno>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Turno> listarUltimosDezTurnosCliente(Cliente cliente) {
		Criteria criteria = getSession().createCriteria(Turno.class);
		criteria.add(Restrictions.eq("cliente.id",cliente.getId()));
		criteria.addOrder(Order.desc("id"));
		criteria.setMaxResults(10);
        return (List<Turno>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Turno> listarTurnos(Usuario usuario, StatusTurnoEnum status) {
		Criteria criteria = getSession().createCriteria(Turno.class);
		criteria.add(Restrictions.eq("usuario.id",usuario.getId()));
		
		if(status != null) {
			criteria.add(Restrictions.eq("status",status));
		}
		
        return (List<Turno>) criteria.list();
	}

	@Override
	public void deleteTurno(Long codigoTurno) {
		Query query = getSession().createSQLQuery("delete from TURNO where id = :id");
        query.setLong("id", codigoTurno);
        query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Turno> consultarTurno(StatusTurnoEnum status) {
		Criteria criteria = getSession().createCriteria(Turno.class);
        criteria.add(Restrictions.eq("status",status));
        return (List<Turno>) criteria.list();
	}

	@Override
	public Turno consultarTurno(Long idTurno) {
		Criteria criteria = getSession().createCriteria(Turno.class);
        criteria.add(Restrictions.eq("id",idTurno));
        return (Turno) criteria.uniqueResult();
	}
	
	@Override
	public BigInteger consultarQuantidadeTurnosPendentes() {
		Query query = getSession().createSQLQuery("SELECT count(*) from TURNO where STATUS_TURNO LIKE 'EM_ANDAMENTO' OR STATUS_TURNO LIKE 'AGUARDANDO_VALIDACAO' OR STATUS_TURNO LIKE 'APROVADO'");
        BigInteger quantidadeClientes = (BigInteger) query.uniqueResult();
        
        return quantidadeClientes;
	}
	
	@Override
	public BigInteger consultarQuantidadeTurnosClienteData(Cliente cliente, Integer mes, Integer ano) {
		Query query = getSession().createSQLQuery("SELECT count(*) from TURNO where ID_CLIENTE = " + cliente.getId() + " AND MONTH(DATA_INICIO) = " + mes + " AND YEAR(DATA_INICIO) = " + ano + ";");
        BigInteger quantidadeClientes = (BigInteger) query.uniqueResult();
        
        return quantidadeClientes;
	}

	@Override
	public void atualizarTurno(Turno Turno) {
		getSession().update(Turno);
	}

}
