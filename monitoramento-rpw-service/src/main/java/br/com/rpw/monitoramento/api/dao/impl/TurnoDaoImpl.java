package br.com.rpw.monitoramento.api.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import br.com.rpw.monitoramento.api.constantes.StatusTurnoEnum;
import br.com.rpw.monitoramento.api.dao.AbstractDao;
import br.com.rpw.monitoramento.api.dao.ITurnoDao;
import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.Turno;

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
	public List<Turno> listarTurnos(Cliente cliente) {
		Criteria criteria = getSession().createCriteria(Turno.class);
		criteria.add(Restrictions.eq("cliente.id",cliente.getId()));
        return (List<Turno>) criteria.list();
	}

	@Override
	public void deleteTurno(Long codigoTurno) {
		Query query = getSession().createSQLQuery("delete from TURNO where id = :codigoTurno");
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
	public void atualizarTurno(Turno Turno) {
		getSession().update(Turno);
	}
}
