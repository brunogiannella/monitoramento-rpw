package br.com.rpw.monitoramento.api.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rpw.monitoramento.api.constantes.TipoCameraEnum;
import br.com.rpw.monitoramento.api.dao.impl.CameraDaoImpl;
import br.com.rpw.monitoramento.api.dao.impl.ClienteDaoImpl;
import br.com.rpw.monitoramento.api.dto.CadastrarClienteRequestDTO;
import br.com.rpw.monitoramento.api.dto.CameraDTO;
import br.com.rpw.monitoramento.api.model.Camera;
import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.service.IClienteService;

@Service
@Transactional
public class ClienteService implements IClienteService {

	@Autowired
	private ClienteDaoImpl clienteDaoImpl;
	
	@Autowired
	private CameraDaoImpl cameraDaoImpl;

	@Override
	public void cadastrarCliente(CadastrarClienteRequestDTO cadastrarClienteRequestDTO) {
		Cliente cliente = converterCadastrarClienteRequestDTOemCliente(cadastrarClienteRequestDTO);
		clienteDaoImpl.salvarCliente(cliente);
		
		for(Camera camera : cliente.getCameras()) {
			camera.setCliente(cliente);
			cameraDaoImpl.salvarCamera(camera);
		}
	}

	private Cliente converterCadastrarClienteRequestDTOemCliente(CadastrarClienteRequestDTO cadastrarClienteRequestDTO) {
		Cliente cliente = new Cliente();
		
		cliente.setNome(cadastrarClienteRequestDTO.getNome());
		cliente.setEmailResposavel(cadastrarClienteRequestDTO.getEmailResposavel());
		cliente.setEmailAutomatico(cadastrarClienteRequestDTO.getEmailAutomatico());
		
		String emailDiario = "";
		for(String email : cadastrarClienteRequestDTO.getEmailsRelatorioDiario()) {
			emailDiario = emailDiario + email + ";";
		}
		
		String emailMensal = "";
		for(String email : cadastrarClienteRequestDTO.getEmailsRelatorioMensal()) {
			emailMensal = emailMensal + email + ";";
		}
		
		cliente.setEmailsRelatorioDiario(emailDiario);
		cliente.setEmailsRelatorioMensal(emailMensal);
		
		cliente.setCameras(new ArrayList<Camera>());
		for(CameraDTO cameraDto : cadastrarClienteRequestDTO.getCameras()) {
			Camera camera = new Camera();
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
			
			cliente.getCameras().add(camera);
		}
		
		return cliente;
	}
}
