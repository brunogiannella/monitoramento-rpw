package br.com.rpw.monitoramento.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rpw.monitoramento.api.constantes.CondicoesClimaticasEnum;
import br.com.rpw.monitoramento.api.model.RestObject;

@RestController
@RequestMapping(value="/condicoesClimaticas")
public class CondicoesClimaticasController {

	@RequestMapping(value="", method = RequestMethod.GET)
	public RestObject consultarOpcoesCondicoesClimaticas() { 
		try {
			return new RestObject(200, true, "Consulta realizada com sucesso", CondicoesClimaticasEnum.values());
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na consulta: " + e.getMessage(), null);
		}
	}
	
}
