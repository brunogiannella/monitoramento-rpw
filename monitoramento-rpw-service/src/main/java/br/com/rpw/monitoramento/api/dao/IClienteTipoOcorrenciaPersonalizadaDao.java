package br.com.rpw.monitoramento.api.dao;

import java.util.List;

import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.ClienteTipoOcorrenciaPersonalizada;
import br.com.rpw.monitoramento.api.model.TipoOcorrenciaPersonalizada;

public interface IClienteTipoOcorrenciaPersonalizadaDao {

    void salvarClienteTipoOcorrenciaPersonalizada(TipoOcorrenciaPersonalizada tipoOcorrencia, Cliente cliente);
    List<ClienteTipoOcorrenciaPersonalizada> listarTipoOcorrenciaPersonalizadas(Cliente cliente);
    void deleteClienteTipoOcorrenciaPersonalizada(TipoOcorrenciaPersonalizada tipoOcorrencia, Cliente cliente);
	void deleteClienteTipoOcorrenciaPersonalizada(Cliente cliente);
	
}
