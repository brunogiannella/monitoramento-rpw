package br.com.rpw.monitoramento.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rpw.monitoramento.api.constantes.TipoTelefoneEnum;
import br.com.rpw.monitoramento.api.model.RestObject;

@RestController
@RequestMapping(value="/telefone")
public class TelefoneController {

	@RequestMapping(value="/tipoTelefone", method = RequestMethod.GET)
	public RestObject consultarTiposTelefone() { 
		try {
			return new RestObject(200, true, "Consulta realizada com sucesso", TipoTelefoneEnum.values());
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na consulta: " + e.getMessage(), null);
		}
	}
	
}
