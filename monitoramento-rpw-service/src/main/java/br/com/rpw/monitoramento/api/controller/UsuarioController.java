package br.com.rpw.monitoramento.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rpw.monitoramento.api.constantes.TipoUsuarioEnum;
import br.com.rpw.monitoramento.api.dto.CadastrarUsuarioRequestDTO;
import br.com.rpw.monitoramento.api.model.RestObject;
import br.com.rpw.monitoramento.api.model.Usuario;
import br.com.rpw.monitoramento.api.service.impl.UsuarioService;
import br.com.rpw.monitoramento.api.util.TokenUtil;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value="", method = RequestMethod.POST)
	public RestObject cadastrarUsuario(@RequestBody CadastrarUsuarioRequestDTO cadastrarUsuarioRequestDTO) { 
		try {
			usuarioService.cadastrarUsuario(cadastrarUsuarioRequestDTO);
			return new RestObject(200, true, "Cadastro realizado com sucesso", "");
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro no cadastro do usuário: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="", method = RequestMethod.PUT)
	public RestObject atualizarUsuario(@RequestBody CadastrarUsuarioRequestDTO cadastrarUsuarioRequestDTO, @RequestHeader(value="x-acess-token") String token) { 
		try {
			usuarioService.atualizarUsuario(cadastrarUsuarioRequestDTO);
			return new RestObject(200, true, "Update realizado com sucesso", "");
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro no update do usuário: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public RestObject consultarUsuarios(@RequestHeader(value="x-acess-token") String token) { 
		try {
			return new RestObject(200, true, "Consulta realizada com sucesso", usuarioService.consultarUsuarios());
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na consulta de usuários: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public RestObject removerUsuario(@PathVariable Long id, @RequestHeader(value="x-acess-token") String token) { 
		try {
			usuarioService.removerUsuario(id);
			return new RestObject(200, true, "Consulta realizada com sucesso", "");
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na consulta de usuários: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public RestObject consultarUsuario(@PathVariable Long id, @RequestHeader(value="x-acess-token") String token) { 
		try {
			return new RestObject(200, true, "Consulta realizada com sucesso", usuarioService.consultarUsuario(id));
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na consulta de usuários: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="/tipo/{tipo}", method = RequestMethod.GET)
	public RestObject consultarUsuario(@PathVariable String tipo, @RequestHeader(value="x-acess-token") String token) { 
		try {
			
			List<Usuario> usuarios = null;
			if(TipoUsuarioEnum.ADMINISTRADOR.getDescricao().equals(tipo)) {
				usuarios = usuarioService.consultarUsuariosFuncionarios();
			} else if(TipoUsuarioEnum.FUNCIONARIO.getDescricao().equals(tipo)) {
				usuarios = usuarioService.consultarUsuariosAdministradores();
			} else if(TipoUsuarioEnum.SUPERVISOR.getDescricao().equals(tipo)) {
				usuarios = usuarioService.consultarUsuariosSupervisores();
			}
			
			return new RestObject(200, true, "Consulta realizada com sucesso", usuarios);
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na consulta de usuários: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="/tipoUsuario", method = RequestMethod.GET)
	public RestObject consultarTiposUsuario(@RequestHeader(value="x-acess-token") String token) { 
		try {
			
			if(TokenUtil.simpleValidToken(token)) {
				return new RestObject(200, true, "Consulta realizada com sucesso", TipoUsuarioEnum.values());
			} else {
				return new RestObject(401, false, "Token inválido.", null);
			}
			
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na consulta: " + e.getMessage(), null);
		}
	}
	
}
