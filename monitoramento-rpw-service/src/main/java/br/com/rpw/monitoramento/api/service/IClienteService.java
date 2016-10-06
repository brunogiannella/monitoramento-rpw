package br.com.rpw.monitoramento.api.service;

import java.util.List;

import br.com.rpw.monitoramento.api.dto.ClienteDTO;
import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.TipoOcorrencia;
import br.com.rpw.monitoramento.api.model.TipoOcorrenciaPersonalizada;

public interface IClienteService {

	void cadastrarCliente(ClienteDTO cadastrarClienteRequestDTO);
	List<Cliente> consultarClientes();
	ClienteDTO consultarCliente(Long id);
	void removerCliente(Long id);
	void atualizarCliente(ClienteDTO cadastrarClienteRequestDTO);
	void associarTipoOcorrencia(Cliente cliente, TipoOcorrencia tipoOcorrencia);
	void associarTipoOcorrenciaPersonalizada(Cliente cliente, TipoOcorrenciaPersonalizada tipoOcorrencia);
	void desassociarTipoOcorrencia(Cliente cliente, TipoOcorrencia tipoOcorrencia);
	void desassociarTipoOcorrenciaPersonalizada(Cliente cliente, TipoOcorrenciaPersonalizada tipoOcorrencia);
	
}
