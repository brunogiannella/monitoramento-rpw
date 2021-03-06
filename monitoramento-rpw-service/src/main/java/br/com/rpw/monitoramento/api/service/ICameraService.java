package br.com.rpw.monitoramento.api.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import br.com.rpw.monitoramento.api.dto.CameraDTO;
import br.com.rpw.monitoramento.api.model.Camera;
import br.com.rpw.monitoramento.api.model.Cliente;

public interface ICameraService {
	Camera consultarCamera(Long idCamera) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	List<Camera> consultarCameras(Cliente cliente) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	void cadastrarCamera(CameraDTO cameraDto) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	void removerCamera(Long idCamera) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	void atualizarCamera(CameraDTO cameraDto) throws NoSuchAlgorithmException, UnsupportedEncodingException;
}
