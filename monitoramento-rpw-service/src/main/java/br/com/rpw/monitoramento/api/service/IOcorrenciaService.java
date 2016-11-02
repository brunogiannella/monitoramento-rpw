package br.com.rpw.monitoramento.api.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;

import br.com.rpw.monitoramento.api.dto.OcorrenciaDTO;
import br.com.rpw.monitoramento.api.model.Ocorrencia;
import br.com.rpw.monitoramento.api.model.Turno;

public interface IOcorrenciaService {
	void cadastrarOcorrencia(OcorrenciaDTO ocorrenciaDto) throws NoSuchAlgorithmException, UnsupportedEncodingException, ParseException;
	Ocorrencia consultarOcorrencia(Long idOcorrencia) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	List<Ocorrencia> consultarOcorrencias(Turno turno) throws NoSuchAlgorithmException, UnsupportedEncodingException;
}
