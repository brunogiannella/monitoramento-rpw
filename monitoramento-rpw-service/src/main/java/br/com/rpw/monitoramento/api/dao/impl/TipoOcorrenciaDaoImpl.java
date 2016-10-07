package br.com.rpw.monitoramento.api.dao.impl;

import java.math.BigInteger;
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
		tipoOcorrencia.setAtivo(true);
		persist(tipoOcorrencia);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoOcorrencia> listarTipoOcorrencias() {
		Criteria criteria = getSession().createCriteria(TipoOcorrencia.class);
		criteria.add(Restrictions.eq("ativo", true));
        return (List<TipoOcorrencia>) criteria.list();
	}

	@Override
	public BigInteger consultarQuantidadeTipoOcorrenciasAtivas() {
		Query query = getSession().createSQLQuery("SELECT count(*) from TIPO_OCORRENCIA where ATIVO = :ativo");
        query.setBoolean("ativo", true);
        BigInteger quantidadeClientes = (BigInteger) query.uniqueResult();
        
        return quantidadeClientes;
	}
	
	@Override
	public void deleteTipoOcorrencia(Long codigoTipoOcorrencia) {
		TipoOcorrencia tipoOcorrencia = consultarTipoOcorrencia(codigoTipoOcorrencia);
		tipoOcorrencia.setAtivo(false);
		atualizarTipoOcorrencia(tipoOcorrencia);
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
