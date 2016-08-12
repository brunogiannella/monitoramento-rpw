package br.com.rpw.monitoramento.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rpw.monitoramento.api.constantes.StatusTurnoEnum;
import br.com.rpw.monitoramento.api.dto.IniciarTurnoRequestDTO;
import br.com.rpw.monitoramento.api.model.RestObject;
import br.com.rpw.monitoramento.api.model.Turno;
import br.com.rpw.monitoramento.api.service.impl.TurnoService;

@RestController
@RequestMapping(value="/turno")
public class TurnoController {

	@Autowired
	private TurnoService turnoService;
	
	@RequestMapping(value="", method = RequestMethod.POST)
	public RestObject iniciarTurno(@RequestBody IniciarTurnoRequestDTO iniciarTurnoRequestDTO) { 
		try {
			Long idTurno = turnoService.iniciarTurno(iniciarTurnoRequestDTO);
			
			if(idTurno == null) {
				return new RestObject(200, true, "Ocorreu um problema ao cadastrar o turno", null);
			}
			
			return new RestObject(200, true, "Turno cadastrado com sucesso", idTurno);
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
			return new RestObject(500, false, "Ocorreu um erro na opera��o: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public RestObject consultarTurno(@PathVariable("id") Long idturno) { 
		try {
			Turno turno = turnoService.consultarTurno(idturno);
			
			if(turno == null) {
				return new RestObject(200, true, "O turno n�o foi encontrado.", null);
			}
			
			return new RestObject(200, true, "Turno consultado com sucesso", turno);
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na consulta: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="/usuario/{id}", method = RequestMethod.GET)
	public RestObject consultarTurnosAbertosUsuario(@PathVariable("id") Long isUsuario) { 
		try {
			List<Turno> turnos = turnoService.consultarTurnos(isUsuario, StatusTurnoEnum.EM_ANDAMENTO);
			
			if(turnos == null) {
				return new RestObject(200, true, "O usu�rio n�o possui nenhum turno.", null);
			}
			
			return new RestObject(200, true, "Turnos consultados com sucesso", turnos);
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na consulta: " + e.getMessage(), null);
		}
	}
}