package br.com.rpw.monitoramento.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rpw.monitoramento.api.dto.AssociarTipoOcorrenciaDTO;
import br.com.rpw.monitoramento.api.dto.ClienteDTO;
import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.RestObject;
import br.com.rpw.monitoramento.api.model.TipoOcorrencia;
import br.com.rpw.monitoramento.api.service.impl.ClienteService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value="", method = RequestMethod.POST)
	public RestObject cadastrarCliente(@RequestBody ClienteDTO cadastrarClienteRequestDTO) { 
		try {
			clienteService.cadastrarCliente(cadastrarClienteRequestDTO);
			return new RestObject(200, true, "Cadastro realizado com sucesso", "");
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro no cadastro do cliente: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="", method = RequestMethod.PUT)
	public RestObject atualizarCliente(@RequestBody ClienteDTO cadastrarClienteRequestDTO) { 
		try {
			clienteService.atualizarCliente(cadastrarClienteRequestDTO);
			return new RestObject(200, true, "Alteração realizada com sucesso", "");
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na alteração do cliente: " + e.getMessage(), null);
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
	
	@RequestMapping(value="/associar-tipo-ocorrencia", method = RequestMethod.POST)
	public RestObject associarTipoOcorrencia(@RequestBody AssociarTipoOcorrenciaDTO associarTipoOcorrenciaDTO, @RequestHeader(value="x-acess-token") String token) { 
		try {
			Cliente cliente = new Cliente();
			cliente.setId(associarTipoOcorrenciaDTO.getIdCliente());
			
			TipoOcorrencia tipoOcorrencia = new TipoOcorrencia();
			tipoOcorrencia.setId(associarTipoOcorrenciaDTO.getIdTipoOcorrencia());
			
			clienteService.associarTipoOcorrencia(cliente, tipoOcorrencia);
			return new RestObject(200, true, "Tipo de ocorrência associada com sucesso", null);
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro ao associar tipo de ocorrência: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public RestObject removerCliente(@PathVariable Long id, @RequestHeader(value="x-acess-token") String token) { 
		try {
			clienteService.removerCliente(id);
			return new RestObject(200, true, "Cadastro realizado com sucesso", "");
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro no cadastro do cliente: " + e.getMessage(), null);
		}
	}
	
}
