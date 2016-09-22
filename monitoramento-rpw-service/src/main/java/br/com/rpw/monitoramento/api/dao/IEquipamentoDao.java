package br.com.rpw.monitoramento.api.dao;

import java.util.List;

import br.com.rpw.monitoramento.api.model.Equipamento;
import br.com.rpw.monitoramento.api.model.Cliente;

public interface IEquipamentoDao {

    void salvarEquipamento(Equipamento equipamento);
    List<Equipamento> listarEquipamentos(Cliente cliente);
    void deleteEquipamento(Long codigoEquipamento);
    Equipamento consultarEquipamento(Long idEquipamento);
    void atualizarEquipamento(Equipamento equipamento);
	void deleteEquipamentosByCliente(Long codigoCliente);
	
}
