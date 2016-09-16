package br.com.rpw.monitoramento.api.dao.impl;

import org.springframework.stereotype.Component;

import br.com.rpw.monitoramento.api.dao.AbstractDao;
import br.com.rpw.monitoramento.api.dao.IEnderecoDao;
import br.com.rpw.monitoramento.api.model.Endereco;

@Component
public class EnderecoDaoImpl extends AbstractDao implements IEnderecoDao {
 
    public void salvarEndereco(Endereco endereco) {
        persist(endereco);
    }
 
}
