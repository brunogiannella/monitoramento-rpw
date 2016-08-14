package br.com.rpw.monitoramento.api.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rpw.monitoramento.api.constantes.TipoUsuarioEnum;
import br.com.rpw.monitoramento.api.dao.impl.UsuarioDaoImpl;
import br.com.rpw.monitoramento.api.dto.CadastrarUsuarioRequestDTO;
import br.com.rpw.monitoramento.api.model.Usuario;
import br.com.rpw.monitoramento.api.service.IUsuarioService;

@Service
@Transactional
public class UsuarioService implements IUsuarioService {

	@Autowired
	private UsuarioDaoImpl usuarioDaoImpl;

	@Override
	public void cadastrarUsuario(CadastrarUsuarioRequestDTO cadastrarUsuarioRequestDto) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		usuarioDaoImpl.salvarUsuario(converterCadastrarUsuarioRequestDTOemUsuario(cadastrarUsuarioRequestDto));
	}
	
	@Override
	public Usuario consultarUsuario(Long id) {
		return usuarioDaoImpl.consultarUsuario(id);
	}
	
	private Usuario converterCadastrarUsuarioRequestDTOemUsuario(CadastrarUsuarioRequestDTO cadastrarUsuarioRequestDto) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Usuario usuario = new Usuario();
		usuario.setUsuario(cadastrarUsuarioRequestDto.getUsuario());
		
		MessageDigest algorithm = MessageDigest.getInstance("MD5");
		byte messageDigest[] = algorithm.digest(cadastrarUsuarioRequestDto.getSenha().getBytes("UTF-8"));

		StringBuilder hexString = new StringBuilder();
		for (byte b : messageDigest) {
			hexString.append(String.format("%02X", 0xFF & b));
		}

		usuario.setSenha(hexString.toString());
		usuario.setEmail(cadastrarUsuarioRequestDto.getEmail());
		
		TipoUsuarioEnum tipoUsuario = null;
		
		for(TipoUsuarioEnum tipo : TipoUsuarioEnum.values()) {
			if(tipo.getDescricao().equals(cadastrarUsuarioRequestDto.getTipoUsuario())) {
				tipoUsuario = tipo;
				break;
			}
		}
		
		usuario.setTipoUsuario(tipoUsuario);
		
		return usuario;
	}
}
