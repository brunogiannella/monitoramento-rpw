package br.com.rpw.monitoramento.api.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rpw.monitoramento.api.dao.impl.OcorrenciaDaoImpl;
import br.com.rpw.monitoramento.api.dto.InserirOcorrenciaDTO;
import br.com.rpw.monitoramento.api.model.Ocorrencia;
import br.com.rpw.monitoramento.api.model.Turno;
import br.com.rpw.monitoramento.api.service.IOcorrenciaService;

@Service
@Transactional
public class OcorrenciaService implements IOcorrenciaService {

	@Autowired
	private OcorrenciaDaoImpl ocorrenciaDaoImpl;

	@Override
	public void cadastrarOcorrencia(InserirOcorrenciaDTO inserirOcorrenciaDTO)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, ParseException {
		ocorrenciaDaoImpl.salvarOcorrencia(converterInserirOcorrenciaDTOEmOcorrencia(inserirOcorrenciaDTO));
	}

	@Override
	public Ocorrencia consultarOcorrencia(Long idOcorrencia)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Ocorrencia ocorrencia = new Ocorrencia();
		ocorrencia.setId(idOcorrencia);
		
		return ocorrenciaDaoImpl.consultarOcorrencia(ocorrencia);
	}

	@Override
	public List<Ocorrencia> consultarOcorrencias(Turno turno)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return ocorrenciaDaoImpl.listarOcorrencias(turno);
	}
	
	private Ocorrencia converterInserirOcorrenciaDTOEmOcorrencia(InserirOcorrenciaDTO inserirOcorrenciaDTO) {
		Ocorrencia ocorrencia = new Ocorrencia();
		
		return ocorrencia;
	}

}
