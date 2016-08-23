package br.com.rpw.monitoramento.api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rpw.monitoramento.api.constantes.PeriodoEnum;
import br.com.rpw.monitoramento.api.model.RestObject;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/periodo")
public class PeriodoController {

	@RequestMapping(value="", method = RequestMethod.GET)
	public RestObject consultarOpcoesPeriodo() { 
		try {
			return new RestObject(200, true, "Consulta realizada com sucesso", PeriodoEnum.values());
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na consulta: " + e.getMessage(), null);
		}
	}
	
}
