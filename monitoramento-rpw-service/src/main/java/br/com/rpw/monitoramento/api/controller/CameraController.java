package br.com.rpw.monitoramento.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rpw.monitoramento.api.constantes.TipoCameraEnum;
import br.com.rpw.monitoramento.api.model.RestObject;

@RestController
@RequestMapping(value="/camera")
public class CameraController {

	@RequestMapping(value="/tipoCamera", method = RequestMethod.GET)
	public RestObject consultarTiposCamera() { 
		try {
			return new RestObject(200, true, "Consulta realizada com sucesso", TipoCameraEnum.values());
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na consulta: " + e.getMessage(), null);
		}
	}
	
}
