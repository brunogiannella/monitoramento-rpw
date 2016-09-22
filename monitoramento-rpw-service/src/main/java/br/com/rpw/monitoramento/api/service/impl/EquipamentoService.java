package br.com.rpw.monitoramento.api.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rpw.monitoramento.api.dao.impl.EquipamentoDaoImpl;
import br.com.rpw.monitoramento.api.dto.EquipamentoDTO;
import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.Equipamento;
import br.com.rpw.monitoramento.api.service.IEquipamentoService;

@Service
@Transactional
public class EquipamentoService implements IEquipamentoService {

	@Autowired
	private EquipamentoDaoImpl equipamentoDaoImpl;

	@Override
	public void cadastrarEquipamento(EquipamentoDTO equipamentoDto) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Equipamento Equipamento = converterEquipamentoDTOemEquipamento(equipamentoDto);
		equipamentoDaoImpl.salvarEquipamento(Equipamento);
	}
	
	@Override
	public Equipamento consultarEquipamento(Long idEquipamento) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return equipamentoDaoImpl.consultarEquipamento(idEquipamento);
	}

	@Override
	public List<Equipamento> consultarEquipamentos(Cliente cliente) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return equipamentoDaoImpl.listarEquipamentos(cliente);
	}
	
	private Equipamento converterEquipamentoDTOemEquipamento(EquipamentoDTO equipamentoDto) {
		Equipamento equipamento = new Equipamento();
		
		if(equipamentoDto.getId() != null) {
			equipamento.setId(equipamentoDto.getId());
		}
		
		Cliente cliente = new Cliente();
		cliente.setId(equipamentoDto.getIdCliente());
		equipamento.setCliente(cliente);
		equipamento.setDescricao(equipamentoDto.getDescricao());
		equipamento.setLocalizacao(equipamentoDto.getLocalizacao());
		equipamento.setNumero(equipamentoDto.getNumero());
				
		return equipamento;
	}

}
