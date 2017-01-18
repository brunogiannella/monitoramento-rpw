package br.com.rpw.monitoramento.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.RestObject;
import br.com.rpw.monitoramento.api.service.impl.ClienteService;
import br.com.rpw.monitoramento.api.service.impl.OcorrenciaService;
import br.com.rpw.monitoramento.api.service.impl.TipoOcorrenciaService;
import br.com.rpw.monitoramento.api.service.impl.TurnoService;
import br.com.rpw.monitoramento.api.service.impl.UsuarioService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/indicador")
public class IndicadoresController {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private TipoOcorrenciaService tipoOcorrenciaService;
	
	@Autowired
	private TurnoService turnoService;
	
	@Autowired
	private OcorrenciaService ocorrenciaService;
	
	@RequestMapping(value="/quantidadeClientes", method = RequestMethod.GET)
	public RestObject consultaIndicadoresClientes() { 
		try {
			return new RestObject(200, true, "Consulta realizada com sucesso", clienteService.consultarQuantidadeClientesAtivos());
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na consulta: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="/quantidadeUsuarios", method = RequestMethod.GET)
	public RestObject consultaIndicadoresUsuarios() { 
		try {
			return new RestObject(200, true, "Consulta realizada com sucesso", usuarioService.consultarQuantidadeUsuariosAtivos());
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na consulta: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="/quantidadeTiposOcorrencia", method = RequestMethod.GET)
	public RestObject consultaIndicadoresTiposOcorrencia() { 
		try {
			return new RestObject(200, true, "Consulta realizada com sucesso", tipoOcorrenciaService.consultarQuantidadeTipoOcorrenciasAtivas());
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na consulta: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="/quantidadeTiposOcorrenciaPersonalizadas", method = RequestMethod.GET)
	public RestObject consultaIndicadoresTiposOcorrenciaPersonalizada() { 
		try {
			return new RestObject(200, true, "Consulta realizada com sucesso", tipoOcorrenciaService.consultarQuantidadeTipoOcorrenciasPersonalizadasAtivas());
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na consulta: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="/quantidadeTurnosPendentes", method = RequestMethod.GET)
	public RestObject consultaIndicadoresTurnosPendentes() { 
		try {
			return new RestObject(200, true, "Consulta realizada com sucesso", turnoService.consultarQuantidadeTurnosPendentes());
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na consulta: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="/quantidadeTurnosClienteMes/cliente/{idCliente}/{mes}/{ano}", method = RequestMethod.GET)
	public RestObject consultaIndicadoresTurnosClientesMes(@PathVariable("idCliente") Long idCliente, @PathVariable("mes") Integer mes, @PathVariable("ano") Integer ano) { 
		try {
			
			Cliente cliente = new Cliente();
			cliente.setId(idCliente);
			
			return new RestObject(200, true, "Consulta realizada com sucesso", turnoService.consultarQuantidadeTurnosClienteData(cliente, mes, ano));
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na consulta: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="/quantidadeOcorrenciasClienteMes/cliente/{idCliente}/{mes}/{ano}", method = RequestMethod.GET)
	public RestObject consultaOcorrenciasTurnosClientesMes(@PathVariable("idCliente") Long idCliente, @PathVariable("mes") Integer mes, @PathVariable("ano") Integer ano) { 
		try {
			
			Cliente cliente = new Cliente();
			cliente.setId(idCliente);
			
			return new RestObject(200, true, "Consulta realizada com sucesso", ocorrenciaService.consultarQuantidadeOcorrenciasClienteData(cliente, mes, ano));
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na consulta: " + e.getMessage(), null);
		}
	}
	
}
