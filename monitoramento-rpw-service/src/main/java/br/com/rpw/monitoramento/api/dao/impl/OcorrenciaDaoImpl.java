package br.com.rpw.monitoramento.api.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import br.com.rpw.monitoramento.api.dao.AbstractDao;
import br.com.rpw.monitoramento.api.dao.IOcorrenciaDao;
import br.com.rpw.monitoramento.api.model.Ocorrencia;
import br.com.rpw.monitoramento.api.model.Turno;

@Component
public class OcorrenciaDaoImpl extends AbstractDao implements IOcorrenciaDao {

	@Override
	public void salvarOcorrencia(Ocorrencia ocorrencia) {
		persist(ocorrencia);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ocorrencia> listarOcorrencias(Turno turno) {
		Criteria criteria = getSession().createCriteria(Ocorrencia.class);
		criteria.add(Restrictions.eq("turno.id",turno.getId()));
        return (List<Ocorrencia>) criteria.list();
	}

	@Override
	public void deleteOcorrenciaById(Long codigoOcorrencia) {
		Query query = getSession().createSQLQuery("delete from OCORRENCIA where id = :id");
        query.setLong("id", codigoOcorrencia);
        query.executeUpdate();
	}

	@Override
	public Ocorrencia consultarOcorrencia(Ocorrencia ocorrencia) {
		Criteria criteria = getSession().createCriteria(Ocorrencia.class);
        criteria.add(Restrictions.eq("id",ocorrencia.getId()));
        return (Ocorrencia) criteria.uniqueResult();
	}

	@Override
	public void atualizarOcorrencia(Ocorrencia ocorrencia) {
		getSession().update(ocorrencia);
	}
 
   
     
}
