package br.com.rpw.monitoramento.api.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import br.com.rpw.monitoramento.api.dto.CadastrarUsuarioRequestDTO;
import br.com.rpw.monitoramento.api.model.Usuario;

public interface IUsuarioService {
	void cadastrarUsuario(CadastrarUsuarioRequestDTO cadastrarUsuarioRequestDto) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	Usuario consultarUsuario(Long id);
}
