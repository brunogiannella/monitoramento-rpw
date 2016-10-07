package br.com.rpw.monitoramento.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rpw.monitoramento.api.model.RestObject;
import br.com.rpw.monitoramento.api.service.impl.ClienteService;
import br.com.rpw.monitoramento.api.service.impl.TipoOcorrenciaService;
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
	
}
