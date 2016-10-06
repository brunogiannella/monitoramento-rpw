package br.com.rpw.monitoramento.api.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import br.com.rpw.monitoramento.api.dao.AbstractDao;
import br.com.rpw.monitoramento.api.dao.IClienteTipoOcorrenciaDao;
import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.ClienteTipoOcorrencia;
import br.com.rpw.monitoramento.api.model.TipoOcorrencia;

@Component
public class ClienteTipoOcorrenciaDaoImpl extends AbstractDao implements IClienteTipoOcorrenciaDao {

	@Override
	public void salvarClienteTipoOcorrencia(TipoOcorrencia tipoOcorrencia, Cliente cliente) {
		ClienteTipoOcorrencia clienteTipoOcorrencia = new ClienteTipoOcorrencia();
		clienteTipoOcorrencia.setCliente(cliente);
		clienteTipoOcorrencia.setTipoOcorrencia(tipoOcorrencia);
		
		persist(clienteTipoOcorrencia);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClienteTipoOcorrencia> listarTipoOcorrencias(Cliente cliente) {
		Criteria criteria = getSession().createCriteria(ClienteTipoOcorrencia.class);
		criteria.add(Restrictions.eq("cliente.id",cliente.getId()));
        return (List<ClienteTipoOcorrencia>) criteria.list();
	}

	@Override
	public void deleteClienteTipoOcorrencia(TipoOcorrencia tipoOcorrencia, Cliente cliente) {
		Query query = getSession().createSQLQuery("delete from CLIENTE_OCORRENCIA where ID_CLIENTE = :idCliente AND ID_TPO_OCOR= :idTipoOcorrencia");
        query.setLong("idCliente", cliente.getId());
        query.setLong("idTipoOcorrencia", tipoOcorrencia.getId());
        query.executeUpdate();
	}
	
	@Override
	public void deleteClienteTipoOcorrencia(Cliente cliente) {
		Query query = getSession().createSQLQuery("delete from CLIENTE_OCORRENCIA where ID_CLIENTE = :idCliente");
        query.setLong("idCliente", cliente.getId());
        query.executeUpdate();
	}

}
