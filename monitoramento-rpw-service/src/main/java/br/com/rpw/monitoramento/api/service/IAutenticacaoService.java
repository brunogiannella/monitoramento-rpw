package br.com.rpw.monitoramento.api.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import br.com.rpw.monitoramento.api.dto.AutenticacaoResponseDTO;

public interface IAutenticacaoService {

	AutenticacaoResponseDTO autenticarUsuario(String usuario, String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	
}
