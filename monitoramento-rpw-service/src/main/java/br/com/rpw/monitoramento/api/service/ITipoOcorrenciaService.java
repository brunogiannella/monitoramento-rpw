package br.com.rpw.monitoramento.api.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;

import br.com.rpw.monitoramento.api.dto.TipoOcorrenciaDTO;
import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.TipoOcorrencia;

public interface ITipoOcorrenciaService {
	Long cadastrarTipoOcorrencia(TipoOcorrenciaDTO tipoOcorrenciaDTO) throws NoSuchAlgorithmException, UnsupportedEncodingException, ParseException;
	TipoOcorrencia consultarTipoOcorrencia(Long idTipoOcorrencia) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	List<TipoOcorrencia> listarTiposOcorrencia() throws NoSuchAlgorithmException, UnsupportedEncodingException;
	List<TipoOcorrencia> listarTiposOcorrencia(Cliente cliente) throws NoSuchAlgorithmException, UnsupportedEncodingException;
}