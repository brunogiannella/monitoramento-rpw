package br.com.rpw.monitoramento.api.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rpw.monitoramento.api.dao.impl.UsuarioDaoImpl;
import br.com.rpw.monitoramento.api.dto.AutenticacaoResponseDTO;
import br.com.rpw.monitoramento.api.model.Usuario;
import br.com.rpw.monitoramento.api.service.IAutenticacaoService;
import br.com.rpw.monitoramento.api.util.TokenUtil;

@Service
@Transactional
public class AutenticacaoService implements IAutenticacaoService {

	@Autowired
	private UsuarioDaoImpl usuarioDaoImpl;

	@Override
	public AutenticacaoResponseDTO autenticarUsuario(String usuario, String senha)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {

		MessageDigest algorithm = MessageDigest.getInstance("MD5");
		byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));

		StringBuilder hexString = new StringBuilder();
		for (byte b : messageDigest) {
			hexString.append(String.format("%02X", 0xFF & b));
		}

		senha = hexString.toString();

		Usuario usuarioConsultado = usuarioDaoImpl.autenticarUsuario(usuario, senha);

		if (usuarioConsultado == null) {
			return null;
		}

		AutenticacaoResponseDTO autenticacaoUsuario = new AutenticacaoResponseDTO();
		autenticacaoUsuario.setAutenticado(true);
		autenticacaoUsuario.setIdUsuario(usuarioConsultado.getId());
		autenticacaoUsuario.setToken(TokenUtil.createJWT(usuarioConsultado.getId().toString(), usuarioConsultado.getTipoUsuario().getDescricao(), "Autenticação RPW", 3600000));
		autenticacaoUsuario.setNomeUsuario(usuarioConsultado.getNome());
		autenticacaoUsuario.setEmailUsuario(usuarioConsultado.getEmail());
		autenticacaoUsuario.setTipoUsuario(usuarioConsultado.getTipoUsuario().getDescricao());
		autenticacaoUsuario.setUsuario(usuarioConsultado.getUsuario());
		
		return autenticacaoUsuario;
	}

}
