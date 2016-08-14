package br.com.rpw.monitoramento.api.dao;

import java.util.List;

import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.SituacaoCamera;

public interface ISituacaoCameraDao {

    void salvarSituacaoCamera(SituacaoCamera SituacaoCamera);
    List<SituacaoCamera> listarSituacaoCameras(Cliente cliente);
    void deleteSituacaoCamera(Long codigoSituacaoCamera);
    SituacaoCamera consultarSituacaoCamera(Long idSituacaoCamera);
    void atualizarSituacaoCamera(SituacaoCamera SituacaoCamera);
	
}
