package br.com.rpw.monitoramento.api.service;

import java.util.List;

import br.com.rpw.monitoramento.api.dto.CadastrarClienteRequestDTO;
import br.com.rpw.monitoramento.api.model.Cliente;

public interface IClienteService {

	void cadastrarCliente(CadastrarClienteRequestDTO cadastrarClienteRequestDTO);
	List<Cliente> consultarClientes();
	Cliente consultarCliente(Long id);
	void removerCliente(Long id);
	
}
