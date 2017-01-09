package br.com.rpw.monitoramento.api.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import br.com.rpw.monitoramento.api.dao.AbstractDao;
import br.com.rpw.monitoramento.api.dao.ISituacaoCameraDao;
import br.com.rpw.monitoramento.api.model.Camera;
import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.SituacaoCamera;
import br.com.rpw.monitoramento.api.model.Turno;

@Component
public class SituacaoCameraDaoImpl extends AbstractDao implements ISituacaoCameraDao {

	@Override
	public void salvarSituacaoCamera(SituacaoCamera situacaoCamera) {
		persist(situacaoCamera);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SituacaoCamera> listarSituacaoCameras(Cliente cliente) {
		Criteria criteria = getSession().createCriteria(SituacaoCamera.class);
		criteria.add(Restrictions.eq("cliente.id",cliente.getId()));
        return (List<SituacaoCamera>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SituacaoCamera> listarSituacaoCamerasEmAberto(Cliente cliente) {
		Criteria criteria = getSession().createCriteria(SituacaoCamera.class);
		criteria.add(Restrictions.eq("cliente.id",cliente.getId()));
		criteria.add(Restrictions.eq("desligada", true));
		criteria.add(Restrictions.eq("ligada", false));
        return (List<SituacaoCamera>) criteria.list();
	}

	@Override
	public void deleteSituacaoCamera(Long codigoSituacaoCamera) {
		Query query = getSession().createSQLQuery("delete from SITUACAO_CAMERA where id = :id");
        query.setLong("id", codigoSituacaoCamera);
        query.executeUpdate();
	}

	@Override
	public SituacaoCamera consultarSituacaoCamera(Long idSituacaoCamera) {
		Criteria criteria = getSession().createCriteria(SituacaoCamera.class);
        criteria.add(Restrictions.eq("id",idSituacaoCamera));
        return (SituacaoCamera) criteria.uniqueResult();
	}
	
	@Override
	public Integer consultarSituacaoCamera(Cliente cliente, Camera camera) {
		Criteria criteria = getSession().createCriteria(SituacaoCamera.class);
		criteria.add(Restrictions.eq("cliente.id",cliente.getId()));
		criteria.add(Restrictions.eq("camera.id",camera.getId()));
		criteria.add(Restrictions.eq("desligada", true));
		criteria.add(Restrictions.eq("ligada", false));
        return criteria.list().size();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SituacaoCamera> consultarSituacaoCameraTurno(Turno turno) {
		Criteria criteria = getSession().createCriteria(SituacaoCamera.class);
		criteria.add(Restrictions.eq("turno.id",turno.getId()));
        return criteria.list();
	}

	@Override
	public void atualizarSituacaoCamera(SituacaoCamera situacaoCamera) {
		getSession().update(situacaoCamera);
	}

}
