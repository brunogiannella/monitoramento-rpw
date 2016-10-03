package br.com.rpw.monitoramento.api.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import br.com.rpw.monitoramento.api.dao.AbstractDao;
import br.com.rpw.monitoramento.api.dao.ICampoOcorrenciaDao;
import br.com.rpw.monitoramento.api.model.CampoOcorrencia;
import br.com.rpw.monitoramento.api.model.TipoOcorrencia;

@Component
public class CampoOcorrenciaDaoImpl extends AbstractDao implements ICampoOcorrenciaDao {

	@Override
	public void deleteCamposOcorrencia(Long idTipoOcorrencia) {
		Query query = getSession().createSQLQuery("delete from CAMPO_OCORRENCIA where ID_TIPO_OCORRENCIA = :id");
        query.setLong("id", idTipoOcorrencia);
        query.executeUpdate();
	}

	@Override
	public void salvarCampoOcorrencia(CampoOcorrencia campoOcorrencia) {
		persist(campoOcorrencia);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CampoOcorrencia> consultarCamposOcorrencia(TipoOcorrencia tipoOcorrencia) {
		Criteria criteria = getSession().createCriteria(CampoOcorrencia.class);
        criteria.add(Restrictions.eq("tipoOcorrencia.id",tipoOcorrencia.getId()));
        return (List<CampoOcorrencia>) criteria.list();
	}
     
}
