package br.com.rpw.monitoramento.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rpw.monitoramento.api.dto.TipoOcorrenciaDTO;
import br.com.rpw.monitoramento.api.model.RestObject;
import br.com.rpw.monitoramento.api.model.TipoOcorrencia;
import br.com.rpw.monitoramento.api.service.impl.TipoOcorrenciaService;
import br.com.rpw.monitoramento.api.util.TokenUtil;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/tipoOcorrencia")
public class TipoOcorrenciaController {

	@Autowired
	private TipoOcorrenciaService tipoOcorrenciaService;
	
	@RequestMapping(value="", method = RequestMethod.POST)
	public RestObject cadastrarTipoOcorrencia(@RequestBody TipoOcorrenciaDTO tipoOcorrenciaDTO, @RequestHeader(value="x-acess-token") String token) { 
		try {			
			if(TokenUtil.simpleValidToken(token)) {
				Long idTipoOcorrencia = tipoOcorrenciaService.cadastrarTipoOcorrencia(tipoOcorrenciaDTO);
				
				if(idTipoOcorrencia == null) {
					return new RestObject(200, true, "Ocorreu um problema ao cadastrar o tipo de ocorrência", null);
				}
				
				return new RestObject(200, true, "Tipo de ocorrência cadastrada com sucesso", idTipoOcorrencia);
			} else {
				return new RestObject(401, false, "Token inválido.", null);
			}
			
			
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro no cadastro: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.GET)
	public RestObject consultartipoOcorrencia(@PathVariable("id") Long idTipoOcorrencia) { 
		try {
			TipoOcorrencia tipoOcorrencia = tipoOcorrenciaService.consultarTipoOcorrencia(idTipoOcorrencia);
			return new RestObject(200, true, "Consulta realizada com sucesso", tipoOcorrencia);
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na consulta: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public RestObject listarTiposOcorrencia() { 
		try {
			return new RestObject(200, true, "Consulta realizada com sucesso", tipoOcorrenciaService.listarTiposOcorrencia());
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na consulta: " + e.getMessage(), null);
		}
	}
	
}
