package br.com.rpw.monitoramento.api.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import br.com.rpw.monitoramento.api.dao.AbstractDao;
import br.com.rpw.monitoramento.api.dao.ITipoOcorrenciaPersonalizadaDao;
import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.TipoOcorrenciaPersonalizada;

@Component
public class TipoOcorrenciaPersonalizadaDaoImpl extends AbstractDao implements ITipoOcorrenciaPersonalizadaDao {

	@Override
	public void salvarTipoOcorrencia(TipoOcorrenciaPersonalizada tipoOcorrencia) {
		tipoOcorrencia.setAtivo(true);
		persist(tipoOcorrencia);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoOcorrenciaPersonalizada> listarTipoOcorrencias() {
		Criteria criteria = getSession().createCriteria(TipoOcorrenciaPersonalizada.class);
		criteria.add(Restrictions.eq("ativo", true));
        return (List<TipoOcorrenciaPersonalizada>) criteria.list();
	}

	@Override
	public BigInteger consultarQuantidadeTipoOcorrenciaPersonalizadassAtivas() {
		Query query = getSession().createSQLQuery("SELECT count(*) from TIPO_OCORRENCIA_PERSONALIZADA where ATIVO = :ativo");
        query.setBoolean("ativo", true);
        BigInteger quantidadeClientes = (BigInteger) query.uniqueResult();
        
        return quantidadeClientes;
	}
	
	@Override
	public void deleteTipoOcorrencia(Long codigoTipoOcorrencia) {
		TipoOcorrenciaPersonalizada tipoOcorrencia = consultarTipoOcorrencia(codigoTipoOcorrencia);
		tipoOcorrencia.setAtivo(false);
		atualizarTipoOcorrencia(tipoOcorrencia);
	}

	@Override
	public TipoOcorrenciaPersonalizada consultarTipoOcorrencia(Long idTipoOcorrencia) {
		Criteria criteria = getSession().createCriteria(TipoOcorrenciaPersonalizada.class);
        criteria.add(Restrictions.eq("id",idTipoOcorrencia));
        return (TipoOcorrenciaPersonalizada) criteria.uniqueResult();
	}

	@Override
	public void atualizarTipoOcorrencia(TipoOcorrenciaPersonalizada tipoOcorrencia) {
		getSession().update(tipoOcorrencia);
	}

	@Override
	public List<TipoOcorrenciaPersonalizada> listarTipoOcorrencias(Cliente cliente) {
		return null;
	}

}
