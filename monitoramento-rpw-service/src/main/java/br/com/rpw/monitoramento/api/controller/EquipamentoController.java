package br.com.rpw.monitoramento.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rpw.monitoramento.api.constantes.TipoEquipamentoEnum;
import br.com.rpw.monitoramento.api.dto.EquipamentoDTO;
import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.Equipamento;
import br.com.rpw.monitoramento.api.model.RestObject;
import br.com.rpw.monitoramento.api.service.impl.EquipamentoService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/equipamento")
public class EquipamentoController {

	@Autowired
	private EquipamentoService equipamentoService;
	
	@RequestMapping(value="", method = RequestMethod.POST)
	public RestObject cadastrarEquipamento(@RequestBody EquipamentoDTO equipamentoDto) { 
		try {
			equipamentoService.cadastrarEquipamento(equipamentoDto);
			return new RestObject(200, true, "Cadastro realizado com sucesso", "");
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro no cadastro da equipamento: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="/tipoEquipamento", method = RequestMethod.GET)
	public RestObject consultarTiposEquipamento() { 
		try {
			List<String> tiposEquipamento = new ArrayList<String>();
			TipoEquipamentoEnum[] enums = TipoEquipamentoEnum.values();
			
			for(TipoEquipamentoEnum tpEquip : enums) {
				tiposEquipamento.add(tpEquip.getDescricao());
			}
			
			return new RestObject(200, true, "Consulta realizada com sucesso", tiposEquipamento);
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na consulta: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="", method = RequestMethod.PUT)
	public RestObject atualizarEquipamento(@RequestBody EquipamentoDTO equipamentoDto) { 
		try {
			equipamentoService.atualizarEquipamento(equipamentoDto);
			return new RestObject(200, true, "Altera��o realizado com sucesso", "");
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na altera��o do equipamento: " + e.getMessage(), null);
		}
	}
		
	@RequestMapping(value="{id}", method = RequestMethod.GET)
	public RestObject consultarEquipamento(@PathVariable("id") Long idEquipamento) { 
		try {
			Equipamento equipamento = equipamentoService.consultarEquipamento(idEquipamento);
			return new RestObject(200, true, "Consulta realizada com sucesso", equipamento);
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na consulta: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public RestObject removerEquipamento(@PathVariable("id") Long idEquipamento) { 
		try {
			equipamentoService.removerEquipamento(idEquipamento);
			return new RestObject(200, true, "Equipamento removido com sucesso", "");
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na remo��o: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="/cliente/{id}", method = RequestMethod.GET)
	public RestObject consultarEquipamentosCliente(@PathVariable("id") Long idCliente) { 
		try {
			
			Cliente cliente = new Cliente();
			cliente.setId(idCliente);;
			
			List<Equipamento> equipamentos = equipamentoService.consultarEquipamentos(cliente);
			
			return new RestObject(200, true, "Consulta realizada com sucesso", equipamentos);
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na consulta: " + e.getMessage(), null);
		}
	}
	
}
