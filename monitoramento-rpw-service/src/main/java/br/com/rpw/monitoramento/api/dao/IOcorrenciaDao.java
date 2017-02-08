package br.com.rpw.monitoramento.api.dao;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.List;

import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.Ocorrencia;
import br.com.rpw.monitoramento.api.model.TipoOcorrencia;
import br.com.rpw.monitoramento.api.model.Turno;

public interface IOcorrenciaDao {

    void salvarOcorrencia(Ocorrencia employee);
    List<Ocorrencia> listarOcorrencias(Turno turno);
    void deleteOcorrenciaById(Long codigoOcorrencia);
    Ocorrencia consultarOcorrencia(Ocorrencia ocorrencia);
    void atualizarOcorrencia(Ocorrencia ocorrencia);
	List<Ocorrencia> listarOcorrencias(Cliente cliente, String mes, String ano) throws ParseException;
	BigInteger consultarQuantidadeOcorrenciasClienteData(Cliente cliente, Integer mes, Integer ano);
	BigInteger consultarQuantidadeOcorrenciasClienteTipoOcorrencia(Cliente cliente, TipoOcorrencia tipo);
	
}
