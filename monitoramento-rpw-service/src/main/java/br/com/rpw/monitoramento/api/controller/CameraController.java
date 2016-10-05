package br.com.rpw.monitoramento.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rpw.monitoramento.api.constantes.TipoCameraEnum;
import br.com.rpw.monitoramento.api.dto.CameraDTO;
import br.com.rpw.monitoramento.api.model.Camera;
import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.RestObject;
import br.com.rpw.monitoramento.api.service.impl.CameraService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/camera")
public class CameraController {

	@Autowired
	private CameraService cameraService;
	
	@RequestMapping(value="", method = RequestMethod.POST)
	public RestObject cadastrarCamera(@RequestBody CameraDTO cameraDto) { 
		try {
			cameraService.cadastrarCamera(cameraDto);
			return new RestObject(200, true, "Cadastro realizado com sucesso", "");
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro no cadastro da camera: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="", method = RequestMethod.PUT)
	public RestObject atualizarCamera(@RequestBody CameraDTO cameraDto) { 
		try {
			cameraService.atualizarCamera(cameraDto);
			return new RestObject(200, true, "Alteração realizado com sucesso", "");
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na alteração da camera: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="/tipoCamera", method = RequestMethod.GET)
	public RestObject consultarTiposCamera() { 
		try {
			return new RestObject(200, true, "Consulta realizada com sucesso", TipoCameraEnum.values());
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na consulta: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.GET)
	public RestObject consultarCamera(@PathVariable("id") Long idCamera) { 
		try {
			Camera camera = cameraService.consultarCamera(idCamera);
			return new RestObject(200, true, "Consulta realizada com sucesso", camera);
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na consulta: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public RestObject removerCamera(@PathVariable("id") Long idCamera) { 
		try {
			cameraService.removerCamera(idCamera);
			return new RestObject(200, true, "Operação realizada com sucesso", "");
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na operação: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="/cliente/{id}", method = RequestMethod.GET)
	public RestObject consultarCamerasCliente(@PathVariable("id") Long idCliente) { 
		try {
			
			Cliente cliente = new Cliente();
			cliente.setId(idCliente);;
			
			List<Camera> cameras = cameraService.consultarCameras(cliente);
			
			return new RestObject(200, true, "Consulta realizada com sucesso", cameras);
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na consulta: " + e.getMessage(), null);
		}
	}
	
}
