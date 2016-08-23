package br.com.rpw.monitoramento.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rpw.monitoramento.api.constantes.StatusTurnoEnum;
import br.com.rpw.monitoramento.api.dto.TurnoDTO;
import br.com.rpw.monitoramento.api.model.RestObject;
import br.com.rpw.monitoramento.api.model.Turno;
import br.com.rpw.monitoramento.api.model.Usuario;
import br.com.rpw.monitoramento.api.service.impl.TurnoService;
import br.com.rpw.monitoramento.api.service.impl.UsuarioService;
import br.com.rpw.monitoramento.api.util.TokenUtil;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/turno")
public class TurnoController {

	@Autowired
	private TurnoService turnoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value="", method = RequestMethod.POST)
	public RestObject iniciarTurno(@RequestBody TurnoDTO iniciarTurnoRequestDTO, @RequestHeader(value="x-acess-token") String token) { 
		try {
			Usuario usuario = usuarioService.consultarUsuario(iniciarTurnoRequestDTO.getIdUsuario());
			
			if(TokenUtil.validToken(token, usuario)) {
				Long idTurno = turnoService.iniciarTurno(iniciarTurnoRequestDTO);
				
				if(idTurno == null) {
					return new RestObject(200, true, "Ocorreu um problema ao cadastrar o turno", null);
				}
				
				return new RestObject(200, true, "Turno cadastrado com sucesso", idTurno);
			} else {
				return new RestObject(401, false, "Token inválido.", null);
			}
			
			
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro no cadastro: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="/{id}/finalizar", method = RequestMethod.POST)
	public RestObject fecharTurno(@PathVariable("id") Long idturno) { 
		try {
			Boolean sucesso = turnoService.finalizarTurno(idturno);
			
			if(!sucesso) {
				return new RestObject(200, true, "Ocorreu um problema ao finalizar o turno", null);
			}
			
			return new RestObject(200, true, "Turno finalizado com sucesso", null);
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na operação: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public RestObject consultarTurno(@PathVariable("id") Long idturno, @RequestHeader(value="x-acess-token") String token) { 
		try {
			
			if(TokenUtil.simpleValidToken(token)) {
				Turno turno = turnoService.consultarTurno(idturno);
				
				if(turno == null) {
					return new RestObject(200, true, "O turno não foi encontrado.", null);
				}
				
				return new RestObject(200, true, "Turno consultado com sucesso", TurnoService.converterTurnoEmTurnoDTO(turno));
			} else {
				return new RestObject(401, false, "Token inválido.", null);
			}
			
			
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na consulta: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="/usuario/{id}", method = RequestMethod.GET)
	public RestObject consultarTurnosAbertosUsuario(@PathVariable("id") Long isUsuario, @RequestHeader(value="x-acess-token") String token) { 
		try {
			
			if(TokenUtil.simpleValidToken(token)) {
				List<Turno> turnos = turnoService.consultarTurnos(isUsuario, StatusTurnoEnum.EM_ANDAMENTO);
				
				if(turnos == null) {
					return new RestObject(200, true, "O usuário não possui nenhum turno.", null);
				}
				
				List<TurnoDTO> turnosDto = new ArrayList<TurnoDTO>();
				for(Turno turno : turnos) {
					turnosDto.add(TurnoService.converterTurnoEmTurnoDTO(turno));
				}
				
				return new RestObject(200, true, "Turnos consultados com sucesso", turnosDto);
			} else {
				return new RestObject(401, false, "Token inválido.", null);
			}
			
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na consulta: " + e.getMessage(), null);
		}
	}
}
