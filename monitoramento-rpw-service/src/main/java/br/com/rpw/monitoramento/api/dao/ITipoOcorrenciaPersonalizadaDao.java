package br.com.rpw.monitoramento.api.dao;

import java.util.List;

import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.TipoOcorrenciaPersonalizada;

public interface ITipoOcorrenciaPersonalizadaDao {

    void salvarTipoOcorrencia(TipoOcorrenciaPersonalizada tipoOcorrencia);
    List<TipoOcorrenciaPersonalizada> listarTipoOcorrencias();
    List<TipoOcorrenciaPersonalizada> listarTipoOcorrencias(Cliente cliente);
    void deleteTipoOcorrencia(Long codigoTipoOcorrencia);
    TipoOcorrenciaPersonalizada consultarTipoOcorrencia(Long idTipoOcorrencia);
    void atualizarTipoOcorrencia(TipoOcorrenciaPersonalizada tipoOcorrencia);
	
}
