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

import br.com.rpw.monitoramento.api.constantes.InformanteOcorrenciaEnum;
import br.com.rpw.monitoramento.api.dto.OcorrenciaDTO;
import br.com.rpw.monitoramento.api.model.Ocorrencia;
import br.com.rpw.monitoramento.api.model.RestObject;
import br.com.rpw.monitoramento.api.model.Turno;
import br.com.rpw.monitoramento.api.service.impl.OcorrenciaService;
import br.com.rpw.monitoramento.api.util.TokenUtil;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/ocorrencia")
public class OcorrenciaController {

	@Autowired
	private OcorrenciaService ocorrenciaService;
	
	@RequestMapping(value="", method = RequestMethod.POST)
	public RestObject cadastrarOcorrencia(@RequestBody OcorrenciaDTO ocorrenciaDTO, @RequestHeader(value="x-acess-token") String token) { 
		try {
			ocorrenciaService.cadastrarOcorrencia(ocorrenciaDTO);
			return new RestObject(200, true, "Ocorrencia cadastrado com sucesso", null);
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro no cadastro: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public RestObject consultarOcorrencia(@PathVariable("id") Long idOcorrencia) { 
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
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public RestObject removerOcorrencia(@PathVariable("id") Long idOcorrencia) { 
		try {
			Ocorrencia ocorrencia = new Ocorrencia();
			ocorrencia.setId(idOcorrencia);
			ocorrenciaService.removerOcorrencia(ocorrencia);
			
			return new RestObject(200, true, "Ocorrencia removida com sucesso", null);
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na remoção: " + e.getMessage(), null);
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
	
	@RequestMapping(value="/informantesOcorrencias", method = RequestMethod.GET)
	public RestObject consultarInformantesOcorrencia(@RequestHeader(value="x-acess-token") String token) { 
		try {
			
			if(TokenUtil.simpleValidToken(token)) {
				return new RestObject(200, true, "Consulta realizada com sucesso", InformanteOcorrenciaEnum.values());
			} else {
				return new RestObject(401, false, "Token inválido.", null);
			}
			
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na consulta: " + e.getMessage(), null);
		}
	}
}
