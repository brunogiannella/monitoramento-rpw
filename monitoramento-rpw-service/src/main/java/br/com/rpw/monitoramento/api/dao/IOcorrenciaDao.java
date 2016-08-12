package br.com.rpw.monitoramento.api.dao;

import java.util.List;

import br.com.rpw.monitoramento.api.model.Ocorrencia;
import br.com.rpw.monitoramento.api.model.Turno;

public interface IOcorrenciaDao {

    void salvarOcorrencia(Ocorrencia employee);
    List<Ocorrencia> listarOcorrencias(Turno turno);
    void deleteOcorrenciaById(Long codigoOcorrencia);
    Ocorrencia consultarOcorrencia(Ocorrencia ocorrencia);
    void atualizarOcorrencia(Ocorrencia ocorrencia);
	
}
