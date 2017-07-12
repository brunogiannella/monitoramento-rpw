package br.com.rpw.monitoramento.api.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rpw.monitoramento.api.dao.impl.SituacaoCameraDaoImpl;
import br.com.rpw.monitoramento.api.dto.SituacaoCameraDTO;
import br.com.rpw.monitoramento.api.model.Camera;
import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.SituacaoCamera;
import br.com.rpw.monitoramento.api.model.Turno;
import br.com.rpw.monitoramento.api.service.ISituacaoCameraService;

@Service
@Transactional
public class SituacaoCameraService implements ISituacaoCameraService {

	@Autowired
	private SituacaoCameraDaoImpl situacaoCameraDaoImpl;

	@Override
	public SituacaoCamera inserirAvaliacao(SituacaoCameraDTO situacaoCameraDTO)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, ParseException {
		
		SituacaoCamera situacaoCamera = null;
		if(situacaoCameraDTO.getIdSituacaoCamera() == null) {
			situacaoCamera = converterSituacaoCameraDTOEmSituacaoCamera(situacaoCameraDTO);
			situacaoCameraDaoImpl.salvarSituacaoCamera(situacaoCamera);
		} else {
			situacaoCamera = situacaoCameraDaoImpl.consultarSituacaoCamera(situacaoCameraDTO.getIdSituacaoCamera());
			situacaoCamera.setLigada(true);
			situacaoCamera.setDesligada(true);
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			situacaoCamera.setDataHoraLigada(formato.parse(situacaoCameraDTO.getDataHora().replace("T", " ")));
			situacaoCameraDaoImpl.atualizarSituacaoCamera(situacaoCamera);
		}
		
		return situacaoCamera;
	}
	
	@Override
	public List<SituacaoCameraDTO> consultarSituacoesCameraAberto(Cliente cliente) {
		List<SituacaoCamera> lista = situacaoCameraDaoImpl.listarSituacaoCamerasEmAberto(cliente);
		
		List<SituacaoCameraDTO> listaDto = new ArrayList<SituacaoCameraDTO>();
		for(SituacaoCamera sc : lista) {
			listaDto.add(converterSituacaoCameraEmSituacaoCameraDTO(sc));
		}
		
		return listaDto;
	}
	
	@Override
	public List<SituacaoCamera> consultarSituacoesCameraTurno(Turno turno) {
		return situacaoCameraDaoImpl.consultarSituacaoCameraTurno(turno);		
	}

	private SituacaoCameraDTO converterSituacaoCameraEmSituacaoCameraDTO(SituacaoCamera situacaoCamera) {
		SituacaoCameraDTO situacaoCameraDto = new SituacaoCameraDTO();
		
		situacaoCameraDto.setIdCamera(situacaoCamera.getCamera().getId());
		situacaoCameraDto.setDescricaoCamera(situacaoCamera.getCamera().getDescricaoCamera());
		situacaoCameraDto.setIdSituacaoCamera(situacaoCamera.getId());
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		situacaoCameraDto.setDataHoraDesligada(formato.format(situacaoCamera.getDataHoraDesligada()));
		
		return situacaoCameraDto;
	}
	
	private SituacaoCamera converterSituacaoCameraDTOEmSituacaoCamera(SituacaoCameraDTO situacaoCameraDTO) throws ParseException {
		SituacaoCamera situacaoCamera = new SituacaoCamera();
		
		Cliente cliente = new Cliente();
		cliente.setId(situacaoCameraDTO.getIdCliente());
		situacaoCamera.setCliente(cliente);
		
		Camera camera = new Camera();
		camera.setId(situacaoCameraDTO.getIdCamera());
		situacaoCamera.setCamera(camera);
		
		Turno turno = new Turno();
		turno.setId(situacaoCameraDTO.getIdTurno());
		situacaoCamera.setTurno(turno);
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		situacaoCamera.setLigada(false);
		situacaoCamera.setDesligada(true);
		situacaoCamera.setDataHoraDesligada(formato.parse(situacaoCameraDTO.getDataHora().replace("T", " ")));
		
		return situacaoCamera;
	}

	@Override
	public Integer consultarSituacaoCamera(Cliente cliente, Camera camera) {
		return this.situacaoCameraDaoImpl.consultarSituacaoCamera(cliente, camera);
	}

}
