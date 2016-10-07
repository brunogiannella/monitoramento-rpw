package br.com.rpw.monitoramento.api.dao;

import java.math.BigInteger;
import java.util.List;

import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.TipoOcorrencia;

public interface ITipoOcorrenciaDao {

    void salvarTipoOcorrencia(TipoOcorrencia tipoOcorrencia);
    List<TipoOcorrencia> listarTipoOcorrencias();
    List<TipoOcorrencia> listarTipoOcorrencias(Cliente cliente);
    void deleteTipoOcorrencia(Long codigoTipoOcorrencia);
    TipoOcorrencia consultarTipoOcorrencia(Long idTipoOcorrencia);
    void atualizarTipoOcorrencia(TipoOcorrencia tipoOcorrencia);
	BigInteger consultarQuantidadeTipoOcorrenciasAtivas();
	
}
