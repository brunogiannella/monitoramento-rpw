package br.com.rpw.monitoramento.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rpw.monitoramento.api.dto.AutenticacaoRequestDTO;
import br.com.rpw.monitoramento.api.dto.AutenticacaoResponseDTO;
import br.com.rpw.monitoramento.api.model.RestObject;
import br.com.rpw.monitoramento.api.model.Usuario;
import br.com.rpw.monitoramento.api.service.impl.AutenticacaoService;

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
				return new RestObject(200, true, "Nenhum usuário foi encontrado", null);
			}
			
			return new RestObject(200, true, "Consulta realizada com sucesso", autenticacaoResponse);
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na consulta: " + e.getMessage(), null);
		}
	}
	
}
