package br.com.rpw.monitoramento.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rpw.monitoramento.api.dto.AutenticacaoRequestDTO;
import br.com.rpw.monitoramento.api.dto.AutenticacaoResponseDTO;
import br.com.rpw.monitoramento.api.model.RestObject;
import br.com.rpw.monitoramento.api.service.impl.AutenticacaoService;

@CrossOrigin(origins = "http://localhost:9080", maxAge = 3600)
@RestController
@RequestMapping(value="/autenticacao")
public class AutenticacaoController {

	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public RestObject consultaIndicadores(@RequestBody AutenticacaoRequestDTO autenticacaoRequest) { 
		try {
			AutenticacaoResponseDTO autenticacaoResponse = autenticacaoService.autenticarUsuario(autenticacaoRequest.getUsuario(), autenticacaoRequest.getSenha());
			
			if(autenticacaoResponse == null) {
				return new RestObject(200, true, "Nenhum usu�rio foi encontrado", null);
			}
			
			return new RestObject(200, true, "Autentica��o realizada com sucesso", autenticacaoResponse);
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na autentica��o: " + e.getMessage(), null);
		}
	}
	
}
