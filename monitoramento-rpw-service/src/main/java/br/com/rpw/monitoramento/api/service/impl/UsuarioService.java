package br.com.rpw.monitoramento.api.service.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rpw.monitoramento.api.constantes.TipoUsuarioEnum;
import br.com.rpw.monitoramento.api.dao.impl.EnderecoDaoImpl;
import br.com.rpw.monitoramento.api.dao.impl.TelefoneDaoImpl;
import br.com.rpw.monitoramento.api.dao.impl.UsuarioDaoImpl;
import br.com.rpw.monitoramento.api.dto.CadastrarUsuarioRequestDTO;
import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.Endereco;
import br.com.rpw.monitoramento.api.model.Telefone;
import br.com.rpw.monitoramento.api.model.Usuario;
import br.com.rpw.monitoramento.api.service.IUsuarioService;

@Service
@Transactional
public class UsuarioService implements IUsuarioService {

	@Autowired
	private UsuarioDaoImpl usuarioDaoImpl;
	
	@Autowired
	private EnderecoDaoImpl enderecoDaoImpl;
	
	@Autowired
	private TelefoneDaoImpl telefoneDaoImpl;
	
	@Override
	public void cadastrarUsuario(CadastrarUsuarioRequestDTO cadastrarUsuarioRequestDto) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Usuario usuario = converterCadastrarUsuarioRequestDTOemUsuario(cadastrarUsuarioRequestDto);
		enderecoDaoImpl.salvarEndereco(usuario.getEndereco());
		telefoneDaoImpl.salvarTelefone(usuario.getTelefone());
		usuarioDaoImpl.salvarUsuario(usuario);
	}
	
	@Override
	public void atualizarUsuario(CadastrarUsuarioRequestDTO cadastrarUsuarioRequestDto) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Usuario usuarioPrincipal = consultarUsuario(cadastrarUsuarioRequestDto.getId());
		Usuario usuario = converterCadastrarUsuarioRequestDTOemUsuario(cadastrarUsuarioRequestDto);
		usuario.setSenha(usuarioPrincipal.getSenha());
		usuario.setAtivo(true);
		enderecoDaoImpl.atualizarEndereco(usuario.getEndereco());
		telefoneDaoImpl.atualizarTelefone(usuario.getTelefone());
		usuarioDaoImpl.atualizarUsuario(usuario);
	}
	
	@Override
	public Usuario consultarUsuario(Long id) {
		return usuarioDaoImpl.consultarUsuario(id);
	}
	
	@Override
	public void removerUsuario(Long id) {
		usuarioDaoImpl.deleteEmployeeById(id);
	}
	
	@Override
	public List<Usuario> consultarUsuarios() {
		return usuarioDaoImpl.listarUsuarios();
	}
	
	private Usuario converterCadastrarUsuarioRequestDTOemUsuario(CadastrarUsuarioRequestDTO cadastrarUsuarioRequestDto) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Usuario usuario = new Usuario();
		
		if(cadastrarUsuarioRequestDto.getId() != null) {
			usuario.setId(cadastrarUsuarioRequestDto.getId());
		}
		
		usuario.setEndereco(new Endereco());
		usuario.setTelefone(new Telefone());
		usuario.setUsuario(cadastrarUsuarioRequestDto.getUsuario());
		
		MessageDigest algorithm = MessageDigest.getInstance("MD5");
		byte messageDigest[] = algorithm.digest(cadastrarUsuarioRequestDto.getSenha().getBytes("UTF-8"));

		StringBuilder hexString = new StringBuilder();
		for (byte b : messageDigest) {
			hexString.append(String.format("%02X", 0xFF & b));
		}

		if(cadastrarUsuarioRequestDto.getTelefone().getId() != null) {
			usuario.getTelefone().setId(cadastrarUsuarioRequestDto.getTelefone().getId());
		}
		usuario.getTelefone().setDdd(cadastrarUsuarioRequestDto.getTelefone().getDdd());
		usuario.getTelefone().setTelefone(cadastrarUsuarioRequestDto.getTelefone().getTelefone());
		
		if(cadastrarUsuarioRequestDto.getEndereco().getId() != null) {
			usuario.getEndereco().setId(cadastrarUsuarioRequestDto.getEndereco().getId());
		}
		usuario.getEndereco().setBairro(cadastrarUsuarioRequestDto.getEndereco().getBairro());
		usuario.getEndereco().setCep(cadastrarUsuarioRequestDto.getEndereco().getCep());
		usuario.getEndereco().setCidade(cadastrarUsuarioRequestDto.getEndereco().getCidade());
		usuario.getEndereco().setEstado(cadastrarUsuarioRequestDto.getEndereco().getEstado());
		usuario.getEndereco().setLogradouro(cadastrarUsuarioRequestDto.getEndereco().getLogradouro());
		
		usuario.setSenha(hexString.toString());
		usuario.setEmail(cadastrarUsuarioRequestDto.getEmail());
		usuario.setNome(cadastrarUsuarioRequestDto.getNome());
		
		TipoUsuarioEnum tipoUsuario = null;
		
		for(TipoUsuarioEnum tipo : TipoUsuarioEnum.values()) {
			if(tipo.getDescricao().equals(cadastrarUsuarioRequestDto.getTipoUsuario())) {
				tipoUsuario = tipo;
				break;
			}
		}
		
		usuario.setTipoUsuario(tipoUsuario);
		
		if(cadastrarUsuarioRequestDto.getIdCliente() != null) {
			Cliente cliente = new Cliente();
			cliente.setId(cadastrarUsuarioRequestDto.getIdCliente());
			usuario.setCliente(cliente);
		}
		
		
		return usuario;
	}

	@Override
	public BigInteger consultarQuantidadeUsuariosAtivos() {
		return usuarioDaoImpl.consultarQuantidadeUsuariosAtivos();
	}
}
