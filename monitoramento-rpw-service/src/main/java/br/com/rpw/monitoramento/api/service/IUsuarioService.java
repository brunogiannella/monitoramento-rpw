package br.com.rpw.monitoramento.api.service;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import br.com.rpw.monitoramento.api.dto.CadastrarUsuarioRequestDTO;
import br.com.rpw.monitoramento.api.model.Usuario;

public interface IUsuarioService {
	void cadastrarUsuario(CadastrarUsuarioRequestDTO cadastrarUsuarioRequestDto) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	Usuario consultarUsuario(Long id);
	List<Usuario> consultarUsuarios();
	void removerUsuario(Long id);
	void atualizarUsuario(CadastrarUsuarioRequestDTO cadastrarUsuarioRequestDto)
			throws NoSuchAlgorithmException, UnsupportedEncodingException;
	BigInteger consultarQuantidadeUsuariosAtivos();
}
