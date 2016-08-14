package br.com.rpw.monitoramento.api.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rpw.monitoramento.api.dao.impl.SituacaoCameraDaoImpl;
import br.com.rpw.monitoramento.api.dto.SituacaoCameraDTO;
import br.com.rpw.monitoramento.api.model.Camera;
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
		SituacaoCamera situacaoCamera = converterSituacaoCameraDTOEmSituacaoCamera(situacaoCameraDTO);
		situacaoCameraDaoImpl.salvarSituacaoCamera(situacaoCamera);
		return situacaoCamera;
	}

	private SituacaoCamera converterSituacaoCameraDTOEmSituacaoCamera(SituacaoCameraDTO situacaoCameraDTO) throws ParseException {
		SituacaoCamera situacaoCamera = new SituacaoCamera();
		
		Camera camera = new Camera();
		camera.setId(situacaoCameraDTO.getIdCamera());
		situacaoCamera.setCamera(camera);
		
		Turno turno = new Turno();
		turno.setId(situacaoCameraDTO.getIdTurno());
		situacaoCamera.setTurno(turno);
		
		situacaoCamera.setLigada(situacaoCameraDTO.getLigada());
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		situacaoCamera.setDataHora(formato.parse(situacaoCameraDTO.getDataHora()));
		
		return situacaoCamera;
	}
}
