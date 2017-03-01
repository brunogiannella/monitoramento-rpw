package br.com.rpw.monitoramento.api.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import br.com.rpw.monitoramento.api.model.Camera;
import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.SituacaoCamera;
import br.com.rpw.monitoramento.api.model.Turno;

public interface ISituacaoCameraDao {

    void salvarSituacaoCamera(SituacaoCamera SituacaoCamera);
    List<SituacaoCamera> listarSituacaoCameras(Cliente cliente);
    void deleteSituacaoCamera(Long codigoSituacaoCamera);
    SituacaoCamera consultarSituacaoCamera(Long idSituacaoCamera);
    void atualizarSituacaoCamera(SituacaoCamera SituacaoCamera);
	List<SituacaoCamera> listarSituacaoCamerasEmAberto(Cliente cliente);
	Integer consultarSituacaoCamera(Cliente cliente, Camera camera);
	List<SituacaoCamera> consultarSituacaoCameraTurno(Turno turno);
	List<SituacaoCamera> listarSituacaoCamerasMensal(Cliente cliente, String mes, String ano) throws ParseException;
	List<SituacaoCamera> consultarSituacaoCameraData(Camera camera, Date inicial, Date fim);
	
}
