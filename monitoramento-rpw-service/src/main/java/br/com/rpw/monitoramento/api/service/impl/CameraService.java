package br.com.rpw.monitoramento.api.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rpw.monitoramento.api.constantes.TipoCameraEnum;
import br.com.rpw.monitoramento.api.dao.impl.CameraDaoImpl;
import br.com.rpw.monitoramento.api.dto.CameraDTO;
import br.com.rpw.monitoramento.api.model.Camera;
import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.service.ICameraService;

@Service
@Transactional
public class CameraService implements ICameraService {

	@Autowired
	private CameraDaoImpl cameraDaoImpl;

	@Override
	public void cadastrarCamera(CameraDTO cameraDto) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Camera camera = converterCameraDTOemCamera(cameraDto);
		cameraDaoImpl.salvarCamera(camera);
	}
	
	@Override
	public Camera consultarCamera(Long idCamera) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return cameraDaoImpl.consultarCamera(idCamera);
	}

	@Override
	public List<Camera> consultarCameras(Cliente cliente) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return cameraDaoImpl.listarCameras(cliente);
	}
	
	private Camera converterCameraDTOemCamera(CameraDTO cameraDto) {
		Camera camera = new Camera();
		
		if(cameraDto.getId() != null) {
			camera.setId(cameraDto.getId());
		}
		
		Cliente cliente = new Cliente();
		cliente.setId(cameraDto.getIdCliente());
		camera.setCliente(cliente);
		camera.setDescricaoCamera(cameraDto.getDescricaoCamera());
		camera.setLocalizacaoCamera(cameraDto.getLocalizacaoCamera());
		camera.setNumeroCamera(cameraDto.getNumeroCamera());
		
		TipoCameraEnum tipoCamera = null;
		for(TipoCameraEnum tipo : TipoCameraEnum.values()) {
			if(tipo.getDescricao().equals(cameraDto.getTipoCamera())) {
				tipoCamera = tipo;
				break;
			}
		}
		
		camera.setTipoCamera(tipoCamera);
		
		return camera;
	}

}
