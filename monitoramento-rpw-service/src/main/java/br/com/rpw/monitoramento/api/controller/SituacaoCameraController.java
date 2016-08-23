package br.com.rpw.monitoramento.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rpw.monitoramento.api.dto.SituacaoCameraDTO;
import br.com.rpw.monitoramento.api.model.RestObject;
import br.com.rpw.monitoramento.api.model.SituacaoCamera;
import br.com.rpw.monitoramento.api.service.impl.SituacaoCameraService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/situacaocamera")
public class SituacaoCameraController {

	@Autowired
	private SituacaoCameraService situacaoCameraService;
	
	@RequestMapping(value="", method = RequestMethod.POST)
	public RestObject inserirAvaliacaoCamera(@RequestBody SituacaoCameraDTO situacaoCameraDTO) { 
		try {
			SituacaoCamera situacaoCamera = situacaoCameraService.inserirAvaliacao(situacaoCameraDTO);			
			if(situacaoCamera.getId() == null) {
				return new RestObject(200, true, "Ocorreu um problema ao cadastrar a situação da camera", null);
			}
			
			return new RestObject(200, true, "Situação da camera cadastrada com sucesso", situacaoCamera.getId());
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro no cadastro: " + e.getMessage(), null);
		}
	}
}
