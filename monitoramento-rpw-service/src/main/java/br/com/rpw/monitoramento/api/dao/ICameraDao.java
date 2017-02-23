package br.com.rpw.monitoramento.api.dao;

import java.util.List;

import br.com.rpw.monitoramento.api.model.Camera;
import br.com.rpw.monitoramento.api.model.Cliente;

public interface ICameraDao {

    void salvarCamera(Camera employee);
    List<Camera> listarCameras(Cliente cliente);
    void deleteCamera(Long codigoCamera);
    Camera consultarCamera(Long idCamera);
    void atualizarCamera(Camera Camera);
	void deleteCamerasByCliente(Long codigoCliente);
	List<String> listarCamerasGroupNumeroCamera(Cliente cliente);
	List<Camera> listarCamerasPorClienteENumero(Cliente cliente, String numeroCamera);
	
}
