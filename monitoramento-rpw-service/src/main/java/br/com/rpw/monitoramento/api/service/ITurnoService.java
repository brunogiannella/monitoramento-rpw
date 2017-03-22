package br.com.rpw.monitoramento.api.service;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;

import br.com.rpw.monitoramento.api.constantes.StatusTurnoEnum;
import br.com.rpw.monitoramento.api.dto.TurnoDTO;
import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.Turno;

public interface ITurnoService {
	Long iniciarTurno(TurnoDTO iniciarTurnoRequestDTO) throws NoSuchAlgorithmException, UnsupportedEncodingException, ParseException;
	boolean finalizarTurno(TurnoDTO turnoDTO) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	Turno consultarTurno(Long idTurno) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	List<Turno> consultarTurnos(Long idUsuario, StatusTurnoEnum emAndamento) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	TurnoDTO consultarTurnoDetalhado(Long idTurno, Boolean isRelatorio);
	List<Turno> consultarTurnoAnterior(Cliente cliente);
	List<Turno> consultarTurnosPendentes();
	Object consultarQuantidadeTurnosPendentes();
	Boolean aprovarTurno(Long idturno);
	List<Turno> listarUltimosDezTurnosCliente(Cliente cliente) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	BigInteger consultarQuantidadeTurnosClienteData(Cliente cliente, Integer mes, Integer ano);
	List<Turno> consultarTurnosAndamento();
	Object consultarQuantidadeTurnosAndamento();
	List<Turno> consultarTurnosAndamentoCliente(Long idCliente);
}
