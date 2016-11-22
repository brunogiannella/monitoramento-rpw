package br.com.rpw.monitoramento.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rpw.monitoramento.api.dto.SituacaoCameraDTO;
import br.com.rpw.monitoramento.api.model.Camera;
import br.com.rpw.monitoramento.api.model.Cliente;
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
			
			if(situacaoCameraDTO.getIdSituacaoCamera() == null) {
				Cliente cliente = new Cliente();
				cliente.setId(situacaoCameraDTO.getIdCliente());
				
				Camera camera = new Camera();
				camera.setId(situacaoCameraDTO.getIdCamera());
				
				Integer quantidadeRegistros = situacaoCameraService.consultarSituacaoCamera(cliente, camera);
				
				if(quantidadeRegistros > 0) {
					return new RestObject(200, false, "A câmera já está inativa, por favor primeiro reative a mesma.", null);
				}
			}
			
			SituacaoCamera situacaoCamera = situacaoCameraService.inserirAvaliacao(situacaoCameraDTO);			
			if(situacaoCamera.getId() == null) {
				return new RestObject(200, true, "Ocorreu um problema ao cadastrar a situação da camera", null);
			}
			
			return new RestObject(200, true, "Situação da camera cadastrada com sucesso", situacaoCamera.getId());
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro no cadastro: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="/cliente/{idCliente}", method = RequestMethod.GET)
	public RestObject consultarClientes(@PathVariable Long idCliente, @RequestHeader(value="x-acess-token") String token) { 
		try {
			Cliente cliente = new Cliente();
			cliente.setId(idCliente);
			
			return new RestObject(200, true, "Cadastro realizado com sucesso", situacaoCameraService.consultarSituacoesCameraAberto(cliente));
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro no cadastro do cliente: " + e.getMessage(), null);
		}
	}
}
