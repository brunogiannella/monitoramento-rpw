package br.com.rpw.monitoramento.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rpw.monitoramento.api.dto.CadastrarUsuarioRequestDTO;
import br.com.rpw.monitoramento.api.model.RestObject;
import br.com.rpw.monitoramento.api.service.impl.UsuarioService;

@RestController
@RequestMapping(value="/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value="/", method = RequestMethod.POST)
	public RestObject cadastrarUsuario(@RequestBody CadastrarUsuarioRequestDTO cadastrarUsuarioRequestDTO) { 
		try {
			usuarioService.cadastrarUsuario(cadastrarUsuarioRequestDTO);
			return new RestObject(200, true, "Cadastro realizado com sucesso", "");
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro no cadastro do usuário: " + e.getMessage(), null);
		}
	}
	
}
