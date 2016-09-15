package br.com.rpw.monitoramento.api.dao;

import java.util.List;

import br.com.rpw.monitoramento.api.model.Cliente;

public interface IClienteDao {

    void salvarCliente(Cliente employee);
    List<Cliente> listarClientes();
    void deleteCliente(Long codigoCliente);
    List<Cliente> consultarCliente(String nomeCliente);
    Cliente consultarCliente(Long idCliente);
    void atualizarCliente(Cliente Cliente);
	List<Cliente> consultarClientes();
	
}
