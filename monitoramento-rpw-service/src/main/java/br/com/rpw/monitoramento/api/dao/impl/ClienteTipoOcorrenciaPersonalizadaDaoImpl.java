package br.com.rpw.monitoramento.api.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import br.com.rpw.monitoramento.api.dao.AbstractDao;
import br.com.rpw.monitoramento.api.dao.IClienteTipoOcorrenciaPersonalizadaDao;
import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.ClienteTipoOcorrenciaPersonalizada;
import br.com.rpw.monitoramento.api.model.TipoOcorrenciaPersonalizada;

@Component
public class ClienteTipoOcorrenciaPersonalizadaDaoImpl extends AbstractDao implements IClienteTipoOcorrenciaPersonalizadaDao {

	@Override
	public void salvarClienteTipoOcorrenciaPersonalizada(TipoOcorrenciaPersonalizada tipoOcorrencia, Cliente cliente) {
		ClienteTipoOcorrenciaPersonalizada clienteTipoOcorrenciaPersonalizada = new ClienteTipoOcorrenciaPersonalizada();
		clienteTipoOcorrenciaPersonalizada.setCliente(cliente);
		clienteTipoOcorrenciaPersonalizada.setTipoOcorrencia(tipoOcorrencia);
		
		persist(clienteTipoOcorrenciaPersonalizada);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClienteTipoOcorrenciaPersonalizada> listarTipoOcorrenciaPersonalizadas(Cliente cliente) {
		Criteria criteria = getSession().createCriteria(ClienteTipoOcorrenciaPersonalizada.class);
		criteria.add(Restrictions.eq("cliente.id",cliente.getId()));
        return (List<ClienteTipoOcorrenciaPersonalizada>) criteria.list();
	}

	@Override
	public void deleteClienteTipoOcorrenciaPersonalizada(TipoOcorrenciaPersonalizada tipoOcorrencia, Cliente cliente) {
		Query query = getSession().createSQLQuery("delete from CLIENTE_OCORRENCIA_PERSONALIZADA where ID_CLIENTE = :idCliente AND ID_TPO_OCOR = :idTipoOcorrenciaPersonalizada");
        query.setLong("idCliente", cliente.getId());
        query.setLong("idTipoOcorrenciaPersonalizada", tipoOcorrencia.getId());
        query.executeUpdate();
	}
	
	@Override
	public void deleteClienteTipoOcorrenciaPersonalizada(Cliente cliente) {
		Query query = getSession().createSQLQuery("delete from CLIENTE_OCORRENCIA_PERSONALIZADA where ID_CLIENTE = :idCliente");
        query.setLong("idCliente", cliente.getId());
        query.executeUpdate();
	}

}
