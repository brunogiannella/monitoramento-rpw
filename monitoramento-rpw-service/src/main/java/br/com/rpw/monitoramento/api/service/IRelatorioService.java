package br.com.rpw.monitoramento.api.service;

import java.text.ParseException;

import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.RelatorioMensal;

public interface IRelatorioService {
	RelatorioMensal consultarRelatorioMensal(Cliente cliente, String mes, String ano) throws ParseException;
}
