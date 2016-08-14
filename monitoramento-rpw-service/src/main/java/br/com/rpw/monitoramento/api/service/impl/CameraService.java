package br.com.rpw.monitoramento.api.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rpw.monitoramento.api.dao.impl.CameraDaoImpl;
import br.com.rpw.monitoramento.api.model.Camera;
import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.service.ICameraService;

@Service
@Transactional
public class CameraService implements ICameraService {

	@Autowired
	private CameraDaoImpl cameraDaoImpl;

	@Override
	public Camera consultarCamera(Long idCamera) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return cameraDaoImpl.consultarCamera(idCamera);
	}

	@Override
	public List<Camera> consultarCameras(Cliente cliente) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return cameraDaoImpl.listarCameras(cliente);
	}

}
