package br.com.rpw.monitoramento.api.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rpw.monitoramento.api.constantes.CondicoesClimaticasEnum;
import br.com.rpw.monitoramento.api.constantes.PeriodoEnum;
import br.com.rpw.monitoramento.api.constantes.StatusTurnoEnum;
import br.com.rpw.monitoramento.api.constantes.TempoEnum;
import br.com.rpw.monitoramento.api.dao.impl.TurnoDaoImpl;
import br.com.rpw.monitoramento.api.dto.TurnoDTO;
import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.Turno;
import br.com.rpw.monitoramento.api.model.Usuario;
import br.com.rpw.monitoramento.api.service.ITurnoService;

@Service
@Transactional
public class TurnoService implements ITurnoService {

	@Autowired
	private TurnoDaoImpl turnoDaoImpl;

	@Override
	public Long iniciarTurno(TurnoDTO iniciarTurnoRequestDTO)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, ParseException {
		Turno turno = converterIniciarTurnoRequestDTOEmTurno(iniciarTurnoRequestDTO);
		turnoDaoImpl.salvarTurno(turno);
		return turno.getId();
	}

	@Override
	public boolean finalizarTurno(Long idTurno) throws NoSuchAlgorithmException, UnsupportedEncodingException {
	
		try {
			Turno turno = turnoDaoImpl.consultarTurno(idTurno);
			turno.setStatus(StatusTurnoEnum.AGUARDANDO_VALIDACAO);
			turnoDaoImpl.atualizarTurno(turno);
			
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	@Override
	public Turno consultarTurno(Long idTurno) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return turnoDaoImpl.consultarTurno(idTurno);
	}

	@Override
	public List<Turno> consultarTurnos(Long idUsuario, StatusTurnoEnum status) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Usuario usuario = new Usuario();
		usuario.setId(idUsuario);
		return turnoDaoImpl.listarTurnos(usuario, status);
	}
	
	private Turno converterIniciarTurnoRequestDTOEmTurno(TurnoDTO iniciarTurnoRequestDTO) throws ParseException {
		Turno turno = new Turno();
		
		Cliente cliente = new Cliente();
		cliente.setId(iniciarTurnoRequestDTO.getIdCliente());
		turno.setCliente(cliente);
		
		Usuario usuario = new Usuario();
		usuario.setId(iniciarTurnoRequestDTO.getIdUsuario());
		turno.setUsuario(usuario);
		
		CondicoesClimaticasEnum condicoesClimaticasEnum = null;
		for(CondicoesClimaticasEnum tipo : CondicoesClimaticasEnum.values()) {
			if(tipo.getDescricao().equals(iniciarTurnoRequestDTO.getCondicaoClimatica())) {
				condicoesClimaticasEnum = tipo;
				break;
			}
		}
		turno.setCondicaoClimatica(condicoesClimaticasEnum);
		
		PeriodoEnum periodoEnum = null;
		for(PeriodoEnum tipo : PeriodoEnum.values()) {
			if(tipo.getDescricao().equals(iniciarTurnoRequestDTO.getPeriodo())) {
				periodoEnum = tipo;
				break;
			}
		}
		turno.setPeriodo(periodoEnum);
		
		TempoEnum tempoEnum = null;
		for(TempoEnum tipo : TempoEnum.values()) {
			if(tipo.getDescricao().equals(iniciarTurnoRequestDTO.getTempo())) {
				tempoEnum = tipo;
				break;
			}
		}
		turno.setTempo(tempoEnum);
		turno.setStatus(StatusTurnoEnum.EM_ANDAMENTO);
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		turno.setDataInicio(formato.parse(iniciarTurnoRequestDTO.getDataInicio().replace("T", " ")));
		turno.setDataFim(formato.parse(iniciarTurnoRequestDTO.getDataFim().replace("T", " ")));
		
		return turno;
	}

	public static TurnoDTO converterTurnoEmTurnoDTO(Turno turno) {
		TurnoDTO turnoDTO = new TurnoDTO();
		turnoDTO.setId(turno.getId());
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		turnoDTO.setDataInicio(formato.format(turno.getDataInicio()));
		turnoDTO.setDataFim(formato.format(turno.getDataFim()));
		
		
		turnoDTO.setCondicaoClimatica(turno.getCondicaoClimatica().getDescricao());
		turnoDTO.setIdCliente(turno.getCliente().getId());
		turnoDTO.setIdUsuario(turno.getUsuario().getId());
		turnoDTO.setPeriodo(turno.getPeriodo().getDescricao());
		turnoDTO.setStatus(turno.getStatus().getDescricao());
		turnoDTO.setTempo(turno.getTempo().getDescricao());
		turnoDTO.setNomeCliente(turno.getCliente().getNome());
		turnoDTO.setNomeUsuario(turno.getUsuario().getNome());
		
		return turnoDTO;
	}
	
}
