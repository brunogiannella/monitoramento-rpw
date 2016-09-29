package br.com.rpw.monitoramento.api.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import br.com.rpw.monitoramento.api.dao.AbstractDao;
import br.com.rpw.monitoramento.api.dao.ICampoOcorrenciaDao;

@Component
public class CampoOcorrenciaDaoImpl extends AbstractDao implements ICampoOcorrenciaDao {

	@Override
	public void deleteCamposOcorrencia(Long idTipoOcorrencia) {
		Query query = getSession().createSQLQuery("delete from CAMPO_OCORRENCIA where ID_TIPO_OCORRENCIA = :id");
        query.setLong("id", idTipoOcorrencia);
        query.executeUpdate();
	}
     
}
