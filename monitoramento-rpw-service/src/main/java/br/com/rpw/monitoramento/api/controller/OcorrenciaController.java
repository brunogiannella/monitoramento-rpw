package br.com.rpw.monitoramento.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rpw.monitoramento.api.dto.OcorrenciaDTO;
import br.com.rpw.monitoramento.api.model.Ocorrencia;
import br.com.rpw.monitoramento.api.model.RestObject;
import br.com.rpw.monitoramento.api.model.Turno;
import br.com.rpw.monitoramento.api.service.impl.OcorrenciaService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/ocorrencia")
public class OcorrenciaController {

	@Autowired
	private OcorrenciaService ocorrenciaService;
	
	@RequestMapping(value="", method = RequestMethod.POST)
	public RestObject iniciarTurno(@RequestBody OcorrenciaDTO ocorrenciaDTO, @RequestHeader(value="x-acess-token") String token) { 
		try {
			ocorrenciaService.cadastrarOcorrencia(ocorrenciaDTO);
			return new RestObject(200, true, "Ocorrencia cadastrado com sucesso", null);
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro no cadastro: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public RestObject consultarTurno(@PathVariable("id") Long idOcorrencia) { 
		try {
			Ocorrencia ocorrencia = ocorrenciaService.consultarOcorrencia(idOcorrencia);
			
			if(ocorrencia == null) {
				return new RestObject(200, true, "A ocorrencia não foi encontrada.", null);
			}
			
			return new RestObject(200, true, "Ocorrencia consultada com sucesso", ocorrencia);
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na consulta: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="/turno/{id}", method = RequestMethod.GET)
	public RestObject consultarTurnosAbertosUsuario(@PathVariable("id") Long idTurno) { 
		try {
			Turno turno = new Turno();
			turno.setId(idTurno);
			
			List<Ocorrencia> ocorrencias = ocorrenciaService.consultarOcorrencias(turno);
			
			if(ocorrencias == null) {
				return new RestObject(200, true, "O turno não possui nenhuma ocorrencia.", null);
			}
			
			return new RestObject(200, true, "Ocorrencias consultadas com sucesso", ocorrencias);
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na consulta: " + e.getMessage(), null);
		}
	}
}
