package br.com.rpw.monitoramento.api.dao;

import br.com.rpw.monitoramento.api.model.Telefone;

public interface ITelefoneDao {

	void salvarTelefone(Telefone telefone);
	void atualizarTelefone(Telefone telefone);

}
