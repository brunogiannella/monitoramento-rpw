package br.com.rpw.monitoramento.api.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;

import br.com.rpw.monitoramento.api.dto.SituacaoCameraDTO;
import br.com.rpw.monitoramento.api.model.Camera;
import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.SituacaoCamera;

public interface ISituacaoCameraService {
	SituacaoCamera inserirAvaliacao(SituacaoCameraDTO situacaoCameraDTO)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, ParseException;
	
	List<SituacaoCameraDTO> consultarSituacoesCameraAberto(Cliente cliente);
	Integer consultarSituacaoCamera(Cliente cliente, Camera camera);
}
