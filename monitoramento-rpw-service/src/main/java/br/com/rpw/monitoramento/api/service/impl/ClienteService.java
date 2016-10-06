package br.com.rpw.monitoramento.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rpw.monitoramento.api.constantes.TipoCameraEnum;
import br.com.rpw.monitoramento.api.dao.impl.CameraDaoImpl;
import br.com.rpw.monitoramento.api.dao.impl.CampoOcorrenciaDaoImpl;
import br.com.rpw.monitoramento.api.dao.impl.ClienteDaoImpl;
import br.com.rpw.monitoramento.api.dao.impl.ClienteTipoOcorrenciaDaoImpl;
import br.com.rpw.monitoramento.api.dao.impl.ClienteTipoOcorrenciaPersonalizadaDaoImpl;
import br.com.rpw.monitoramento.api.dao.impl.EnderecoDaoImpl;
import br.com.rpw.monitoramento.api.dao.impl.EquipamentoDaoImpl;
import br.com.rpw.monitoramento.api.dto.CameraDTO;
import br.com.rpw.monitoramento.api.dto.ClienteDTO;
import br.com.rpw.monitoramento.api.dto.EnderecoDTO;
import br.com.rpw.monitoramento.api.dto.EquipamentoDTO;
import br.com.rpw.monitoramento.api.dto.TipoOcorrenciaDTO;
import br.com.rpw.monitoramento.api.model.Camera;
import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.ClienteTipoOcorrencia;
import br.com.rpw.monitoramento.api.model.ClienteTipoOcorrenciaPersonalizada;
import br.com.rpw.monitoramento.api.model.Endereco;
import br.com.rpw.monitoramento.api.model.Equipamento;
import br.com.rpw.monitoramento.api.model.TipoOcorrencia;
import br.com.rpw.monitoramento.api.model.TipoOcorrenciaPersonalizada;
import br.com.rpw.monitoramento.api.service.IClienteService;

@Service
@Transactional
public class ClienteService implements IClienteService {

	@Autowired
	private ClienteDaoImpl clienteDaoImpl;
	
	@Autowired
	private CameraDaoImpl cameraDaoImpl;
	
	@Autowired
	private EquipamentoDaoImpl equipamentoDaoImpl;
	
	@Autowired
	private EnderecoDaoImpl enderecoDaoImpl;
	
	@Autowired
	private ClienteTipoOcorrenciaDaoImpl clienteTipoOcorrenciaDaoImpl;
	
	@Autowired
	private ClienteTipoOcorrenciaPersonalizadaDaoImpl clienteTipoOcorrenciaPersonalizadaDaoImpl;
	
	@Autowired
	private CampoOcorrenciaDaoImpl campoOcorrenciaDaoImpl;

	@Override
	public void cadastrarCliente(ClienteDTO cadastrarClienteRequestDTO) {
		Cliente cliente = converterCadastrarClienteRequestDTOemCliente(cadastrarClienteRequestDTO);
		enderecoDaoImpl.salvarEndereco(cliente.getEndereco());
		clienteDaoImpl.salvarCliente(cliente);
		
		if(cliente.getCameras() != null) {
			for(Camera camera : cliente.getCameras()) {
				camera.setCliente(cliente);
				cameraDaoImpl.salvarCamera(camera);
			}
		}
	}
	
	@Override
	public void atualizarCliente(ClienteDTO cadastrarClienteRequestDTO) {
		Cliente cliente = converterCadastrarClienteRequestDTOemCliente(cadastrarClienteRequestDTO);
		enderecoDaoImpl.atualizarEndereco(cliente.getEndereco());
		clienteDaoImpl.atualizarCliente(cliente);
	}
	
	@Override
	public List<Cliente> consultarClientes() {
		return clienteDaoImpl.consultarClientes();
	}
	
	@Override
	public void associarTipoOcorrencia(Cliente cliente, TipoOcorrencia tipoOcorrencia) {
		clienteTipoOcorrenciaDaoImpl.salvarClienteTipoOcorrencia(tipoOcorrencia, cliente);
	}
	
	@Override
	public void desassociarTipoOcorrencia(Cliente cliente, TipoOcorrencia tipoOcorrencia) {
		clienteTipoOcorrenciaDaoImpl.deleteClienteTipoOcorrencia(tipoOcorrencia, cliente);
	}
	
	@Override
	public void associarTipoOcorrenciaPersonalizada(Cliente cliente, TipoOcorrenciaPersonalizada tipoOcorrencia) {
		clienteTipoOcorrenciaPersonalizadaDaoImpl.salvarClienteTipoOcorrenciaPersonalizada(tipoOcorrencia, cliente);
	}
	
	@Override
	public void desassociarTipoOcorrenciaPersonalizada(Cliente cliente, TipoOcorrenciaPersonalizada tipoOcorrencia) {
		clienteTipoOcorrenciaPersonalizadaDaoImpl.deleteClienteTipoOcorrenciaPersonalizada(tipoOcorrencia, cliente);
	}
	
	@Override
	public ClienteDTO consultarCliente(Long id) {
		Cliente cliente = new Cliente();
		cliente.setId(id);
		List<Camera> camerasCliente = cameraDaoImpl.listarCameras(cliente);
		List<Equipamento> equipamentosCliente = equipamentoDaoImpl.listarEquipamentos(cliente);
		cliente = clienteDaoImpl.consultarCliente(id);
		cliente.setCameras(camerasCliente);
		cliente.setEquipamento(equipamentosCliente);
		
		List<ClienteTipoOcorrencia> tipoOcorrencia = clienteTipoOcorrenciaDaoImpl.listarTipoOcorrencias(cliente);
		if(tipoOcorrencia != null) {
			cliente.setTipoOcorrencias(new ArrayList<TipoOcorrencia>());
			
			for(ClienteTipoOcorrencia clienteTipoOcorrencia : tipoOcorrencia) {
				TipoOcorrencia tipoOcorrenciaAtual = clienteTipoOcorrencia.getTipoOcorrencia();
				tipoOcorrenciaAtual.setCampos(campoOcorrenciaDaoImpl.consultarCamposOcorrencia(tipoOcorrenciaAtual));
				cliente.getTipoOcorrencias().add(tipoOcorrenciaAtual);
			}
		}
		
		List<ClienteTipoOcorrenciaPersonalizada> tipoOcorrenciaPersonalizada = clienteTipoOcorrenciaPersonalizadaDaoImpl.listarTipoOcorrenciaPersonalizadas(cliente);
		if(tipoOcorrenciaPersonalizada != null) {
			cliente.setTipoOcorrenciasPersonalizada(new ArrayList<TipoOcorrenciaPersonalizada>());
			
			for(ClienteTipoOcorrenciaPersonalizada clienteTipoOcorrencia : tipoOcorrenciaPersonalizada) {
				TipoOcorrenciaPersonalizada tipoOcorrenciaAtual = clienteTipoOcorrencia.getTipoOcorrencia();
				cliente.getTipoOcorrenciasPersonalizada().add(tipoOcorrenciaAtual);
			}
		}
		
		return converterClienteEmClienteDTO(cliente);
	}
	
	@Override
	public void removerCliente(Long id) {
		Cliente cliente = new Cliente();
		cliente.setId(id);
		
		//cameraDaoImpl.deleteCamerasByCliente(id);
		//equipamentoDaoImpl.deleteEquipamentosByCliente(id);
		//clienteTipoOcorrenciaDaoImpl.deleteClienteTipoOcorrencia(cliente);
		clienteDaoImpl.deleteCliente(id);
	}

	private ClienteDTO converterClienteEmClienteDTO(Cliente cliente) {
		ClienteDTO clienteDto = new ClienteDTO();
		
		clienteDto.setId(cliente.getId());
		clienteDto.setNome(cliente.getNome());
		clienteDto.setEmailAutomatico(cliente.getEmailAutomatico());
		clienteDto.setEmailResposavel(cliente.getEmailResposavel());
		
		clienteDto.setEmailsRelatorioMensal(new ArrayList<String>());
		String[] emailsM = cliente.getEmailsRelatorioMensal().split(";");
		for(String e : emailsM) {
			clienteDto.getEmailsRelatorioMensal().add(e);
		}
		
		clienteDto.setEmailsRelatorioDiario(new ArrayList<String>());
		String[] emailsD = cliente.getEmailsRelatorioDiario().split(";");
		for(String e : emailsD) {
			clienteDto.getEmailsRelatorioDiario().add(e);
		}
		
		clienteDto.setEndereco(new EnderecoDTO());
		clienteDto.getEndereco().setBairro(cliente.getEndereco().getBairro());
		clienteDto.getEndereco().setCep(cliente.getEndereco().getCep());
		clienteDto.getEndereco().setCidade(cliente.getEndereco().getCidade());
		clienteDto.getEndereco().setEstado(cliente.getEndereco().getEstado());
		clienteDto.getEndereco().setId(cliente.getEndereco().getId());
		clienteDto.getEndereco().setLogradouro(cliente.getEndereco().getLogradouro());
		
		clienteDto.setCameras(new ArrayList<CameraDTO>());
		if(cliente.getCameras() != null) {
			for(Camera camera : cliente.getCameras()) {
				CameraDTO cameraDto = new CameraDTO();
				cameraDto.setDescricaoCamera(camera.getDescricaoCamera());
				cameraDto.setId(camera.getId());
				cameraDto.setIdCliente(camera.getCliente().getId());
				cameraDto.setLocalizacaoCamera(camera.getLocalizacaoCamera());
				cameraDto.setNumeroCamera(camera.getNumeroCamera());
				cameraDto.setTipoCamera(camera.getTipoCamera().getDescricao());
				
				clienteDto.getCameras().add(cameraDto);
			}
		}
		
		clienteDto.setEquipamentos(new ArrayList<EquipamentoDTO>());
		if(cliente.getEquipamento() != null) {
			for(Equipamento equipamento : cliente.getEquipamento()) {
				EquipamentoDTO equipamentoDTO = new EquipamentoDTO();
				equipamentoDTO.setId(equipamento.getId());
				equipamentoDTO.setDescricao(equipamento.getDescricao());
				equipamentoDTO.setIdCliente(equipamento.getCliente().getId());
				equipamentoDTO.setLocalizacao(equipamento.getLocalizacao());
				equipamentoDTO.setNumero(equipamento.getNumero());
				
				if(equipamento.getTipoEquipamento() != null) {
					equipamentoDTO.setTipoEquipamento(equipamento.getTipoEquipamento().getDescricao());
				}
				
				clienteDto.getEquipamentos().add(equipamentoDTO);
			}
		}
		
		clienteDto.setTiposOcorrencia(new ArrayList<TipoOcorrenciaDTO>());
		if(cliente.getTipoOcorrencias() != null) {
			for(TipoOcorrencia tipoOcorrencia : cliente.getTipoOcorrencias()) {
				TipoOcorrenciaDTO tipoOcorrenciaDto = new TipoOcorrenciaDTO();
				tipoOcorrenciaDto.setId(tipoOcorrencia.getId());
				tipoOcorrenciaDto.setDescricao(tipoOcorrencia.getDescricao());
				
				if(tipoOcorrencia.getCampos() != null) {
					tipoOcorrenciaDto.setQuantidadeCampos(tipoOcorrencia.getCampos().size());
				}
				
				clienteDto.getTiposOcorrencia().add(tipoOcorrenciaDto);
			}
		}
		
		clienteDto.setTiposOcorrenciaPersonalizada(new ArrayList<TipoOcorrenciaDTO>());
		if(cliente.getTipoOcorrenciasPersonalizada() != null) {
			for(TipoOcorrenciaPersonalizada tipoOcorrencia : cliente.getTipoOcorrenciasPersonalizada()) {
				TipoOcorrenciaDTO tipoOcorrenciaDto = new TipoOcorrenciaDTO();
				tipoOcorrenciaDto.setId(tipoOcorrencia.getId());
				tipoOcorrenciaDto.setDescricao(tipoOcorrencia.getDescricao());
								
				clienteDto.getTiposOcorrenciaPersonalizada().add(tipoOcorrenciaDto);
			}
		}
		
		return clienteDto;
	}
	
	private Cliente converterCadastrarClienteRequestDTOemCliente(ClienteDTO cadastrarClienteRequestDTO) {
		Cliente cliente = new Cliente();
		
		if(cadastrarClienteRequestDTO.getId() != null) {
			cliente.setId(cadastrarClienteRequestDTO.getId());
		}
		
		cliente.setNome(cadastrarClienteRequestDTO.getNome());
		cliente.setEmailResposavel(cadastrarClienteRequestDTO.getEmailResposavel());
		
		if(cadastrarClienteRequestDTO.getEmailAutomatico() == null) {
			cliente.setEmailAutomatico(false);
		} else {
			cliente.setEmailAutomatico(cadastrarClienteRequestDTO.getEmailAutomatico());
		}
		
		
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
		
		
		cliente.setEndereco(new Endereco());

		if(cadastrarClienteRequestDTO.getEndereco() != null && cadastrarClienteRequestDTO.getEndereco().getId() != null) {
			cliente.getEndereco().setId(cadastrarClienteRequestDTO.getId());
		}
		
		cliente.getEndereco().setBairro(cadastrarClienteRequestDTO.getEndereco().getBairro());
		cliente.getEndereco().setCep(cadastrarClienteRequestDTO.getEndereco().getCep());
		cliente.getEndereco().setCidade(cadastrarClienteRequestDTO.getEndereco().getCidade());
		cliente.getEndereco().setEstado(cadastrarClienteRequestDTO.getEndereco().getEstado());
		cliente.getEndereco().setLogradouro(cadastrarClienteRequestDTO.getEndereco().getLogradouro());
		
		cliente.setCameras(new ArrayList<Camera>());
		
		if(cadastrarClienteRequestDTO.getCameras() != null) {
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
		}
		
		
		return cliente;
	}

}
