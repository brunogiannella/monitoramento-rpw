package br.com.rpw.monitoramento.api.dao;

import java.util.List;

import br.com.rpw.monitoramento.api.model.CampoOcorrencia;
import br.com.rpw.monitoramento.api.model.TipoOcorrencia;

public interface ICampoOcorrenciaDao {

	void salvarCampoOcorrencia(CampoOcorrencia campoOcorrencia);
	void deleteCamposOcorrencia(Long idTipoOcorrencia);
	List<CampoOcorrencia> consultarCamposOcorrencia(TipoOcorrencia tipoOcorrencia);
	
}
