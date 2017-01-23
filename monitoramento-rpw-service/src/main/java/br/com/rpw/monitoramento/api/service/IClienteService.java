package br.com.rpw.monitoramento.api.service;

import java.math.BigInteger;
import java.util.List;

import br.com.rpw.monitoramento.api.dto.ClienteDTO;
import br.com.rpw.monitoramento.api.dto.TipoOcorrenciaDTO;
import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.TipoOcorrencia;
import br.com.rpw.monitoramento.api.model.TipoOcorrenciaPersonalizada;

public interface IClienteService {

	void cadastrarCliente(ClienteDTO cadastrarClienteRequestDTO);
	List<Cliente> consultarClientes();
	ClienteDTO consultarCliente(Long id);
	void removerCliente(Long id);
	void atualizarCliente(ClienteDTO cadastrarClienteRequestDTO);
	void desassociarTipoOcorrencia(Cliente cliente, TipoOcorrencia tipoOcorrencia);
	BigInteger consultarQuantidadeClientesAtivos();
	List<TipoOcorrenciaDTO> consultarTiposOcorrencia(Cliente cliente);
	void associarTipoOcorrencia(Cliente cliente, List<TipoOcorrencia> tiposOcorrencia);
	void associarTipoOcorrenciaPersonalizada(Cliente cliente, List<TipoOcorrenciaPersonalizada> tiposOcorrencia);
	void desassociarTipoOcorrenciaPersonalizada(Cliente cliente, TipoOcorrenciaPersonalizada tipoOcorrencia);
	
}
