package br.com.rpw.monitoramento.api.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import br.com.rpw.monitoramento.api.dao.AbstractDao;
import br.com.rpw.monitoramento.api.dao.ITipoOcorrenciaDao;
import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.TipoOcorrencia;

@Component
public class TipoOcorrenciaDaoImpl extends AbstractDao implements ITipoOcorrenciaDao {

	@Override
	public void salvarTipoOcorrencia(TipoOcorrencia tipoOcorrencia) {
		persist(tipoOcorrencia);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoOcorrencia> listarTipoOcorrencias() {
		Criteria criteria = getSession().createCriteria(TipoOcorrencia.class);
        return (List<TipoOcorrencia>) criteria.list();
	}

	@Override
	public void deleteTipoOcorrencia(Long codigoTipoOcorrencia) {
		Query query = getSession().createSQLQuery("delete from TIPO_OCORRENCIA where id = :id");
        query.setLong("id", codigoTipoOcorrencia);
        query.executeUpdate();
	}

	@Override
	public TipoOcorrencia consultarTipoOcorrencia(Long idTipoOcorrencia) {
		Criteria criteria = getSession().createCriteria(TipoOcorrencia.class);
        criteria.add(Restrictions.eq("id",idTipoOcorrencia));
        return (TipoOcorrencia) criteria.uniqueResult();
	}

	@Override
	public void atualizarTipoOcorrencia(TipoOcorrencia tipoOcorrencia) {
		getSession().update(tipoOcorrencia);
	}

	@Override
	public List<TipoOcorrencia> listarTipoOcorrencias(Cliente cliente) {
		return null;
	}

}
