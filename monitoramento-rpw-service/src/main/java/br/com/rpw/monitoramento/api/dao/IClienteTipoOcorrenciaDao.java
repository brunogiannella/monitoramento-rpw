package br.com.rpw.monitoramento.api.dao;

import java.util.List;

import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.ClienteTipoOcorrencia;
import br.com.rpw.monitoramento.api.model.TipoOcorrencia;

public interface IClienteTipoOcorrenciaDao {

    void salvarClienteTipoOcorrencia(TipoOcorrencia tipoOcorrencia, Cliente cliente);
    List<ClienteTipoOcorrencia> listarTipoOcorrencias(Cliente cliente);
    void deleteClienteTipoOcorrencia(TipoOcorrencia tipoOcorrencia, Cliente cliente);
	void deleteClienteTipoOcorrencia(Cliente cliente);
	
}
