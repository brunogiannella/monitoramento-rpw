package br.com.rpw.monitoramento.api.dao;

import java.math.BigInteger;
import java.util.List;

import br.com.rpw.monitoramento.api.constantes.StatusTurnoEnum;
import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.Turno;
import br.com.rpw.monitoramento.api.model.Usuario;

public interface ITurnoDao {

    void salvarTurno(Turno employee);
    List<Turno> listarTurnos();
    List<Turno> listarTurnos(Cliente cliente);
    void deleteTurno(Long codigoTurno);
    List<Turno> consultarTurno(StatusTurnoEnum status, Boolean enviado);
    Turno consultarTurno(Long idTurno);
    void atualizarTurno(Turno Turno);
	List<Turno> listarTurnos(Usuario usuario, StatusTurnoEnum status);
	List<Turno> consultarTurnoAnterior(Cliente cliente);
	BigInteger consultarQuantidadeTurnosPendentes();
	List<Turno> listarUltimosDezTurnosCliente(Cliente cliente);
	BigInteger consultarQuantidadeTurnosClienteData(Cliente cliente, Integer mes, Integer ano);
	BigInteger consultarQuantidadeTurnosAndamento();
	List<Turno> consultarTurnosCliente(StatusTurnoEnum emAndamento, Long idCliente);
	
}
