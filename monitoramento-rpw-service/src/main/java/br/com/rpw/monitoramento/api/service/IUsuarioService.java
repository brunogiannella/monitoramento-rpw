package br.com.rpw.monitoramento.api.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import br.com.rpw.monitoramento.api.dto.CadastrarUsuarioRequestDTO;

public interface IUsuarioService {
	void cadastrarUsuario(CadastrarUsuarioRequestDTO cadastrarUsuarioRequestDto) throws NoSuchAlgorithmException, UnsupportedEncodingException;
}
