package br.com.rpw.monitoramento.api.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;

import br.com.rpw.monitoramento.api.constantes.StatusTurnoEnum;
import br.com.rpw.monitoramento.api.dto.IniciarTurnoRequestDTO;
import br.com.rpw.monitoramento.api.model.Turno;

public interface ITurnoService {
	Long iniciarTurno(IniciarTurnoRequestDTO iniciarTurnoRequestDTO) throws NoSuchAlgorithmException, UnsupportedEncodingException, ParseException;
	boolean finalizarTurno(Long idTurno) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	Turno consultarTurno(Long idTurno) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	List<Turno> consultarTurnos(Long idUsuario, StatusTurnoEnum emAndamento) throws NoSuchAlgorithmException, UnsupportedEncodingException;
}