package br.com.rpw.monitoramento.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rpw.monitoramento.api.model.RestObject;
import br.com.rpw.monitoramento.api.service.impl.ClienteService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/indicador")
public class IndicadoresController {

	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value="/quantidadeClientes", method = RequestMethod.GET)
	public RestObject consultaIndicadores() { 
		try {
			return new RestObject(200, true, "Consulta realizada com sucesso", clienteService.consultarQuantidadeClientesAtivos());
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na consulta: " + e.getMessage(), null);
		}
	}
	
}
