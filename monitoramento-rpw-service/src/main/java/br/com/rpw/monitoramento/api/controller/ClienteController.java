package br.com.rpw.monitoramento.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rpw.monitoramento.api.dto.CadastrarClienteRequestDTO;
import br.com.rpw.monitoramento.api.model.RestObject;
import br.com.rpw.monitoramento.api.service.impl.ClienteService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value="", method = RequestMethod.POST)
	public RestObject cadastrarCliente(@RequestBody CadastrarClienteRequestDTO cadastrarClienteRequestDTO) { 
		try {
			clienteService.cadastrarCliente(cadastrarClienteRequestDTO);
			return new RestObject(200, true, "Cadastro realizado com sucesso", "");
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro no cadastro do cliente: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public RestObject consultarClientes(@RequestHeader(value="x-acess-token") String token) { 
		try {
			return new RestObject(200, true, "Cadastro realizado com sucesso", clienteService.consultarClientes());
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro no cadastro do cliente: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public RestObject consultarCliente(@PathVariable Long id, @RequestHeader(value="x-acess-token") String token) { 
		try {
			return new RestObject(200, true, "Cadastro realizado com sucesso", clienteService.consultarCliente(id));
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro no cadastro do cliente: " + e.getMessage(), null);
		}
	}
	
}
