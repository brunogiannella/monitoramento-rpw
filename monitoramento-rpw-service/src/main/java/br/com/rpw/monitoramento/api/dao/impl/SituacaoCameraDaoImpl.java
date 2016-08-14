package br.com.rpw.monitoramento.api.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import br.com.rpw.monitoramento.api.dao.AbstractDao;
import br.com.rpw.monitoramento.api.dao.ISituacaoCameraDao;
import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.SituacaoCamera;

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
	public void atualizarSituacaoCamera(SituacaoCamera situacaoCamera) {
		getSession().update(situacaoCamera);
	}

}
