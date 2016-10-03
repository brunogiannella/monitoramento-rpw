package br.com.rpw.monitoramento.api.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import br.com.rpw.monitoramento.api.dto.EquipamentoDTO;
import br.com.rpw.monitoramento.api.model.Equipamento;
import br.com.rpw.monitoramento.api.model.Cliente;

public interface IEquipamentoService {
	Equipamento consultarEquipamento(Long idEquipamento) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	List<Equipamento> consultarEquipamentos(Cliente cliente) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	void cadastrarEquipamento(EquipamentoDTO EquipamentoDto) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	void atualizarEquipamento(EquipamentoDTO EquipamentoDto) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	void removerEquipamento(Long idEquipamento) throws NoSuchAlgorithmException, UnsupportedEncodingException;
}
