package br.com.rpw.monitoramento.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rpw.monitoramento.api.dao.impl.ClienteDaoImpl;
import br.com.rpw.monitoramento.api.service.IClienteService;

@Service
@Transactional
public class ClienteService implements IClienteService {

	@Autowired
	private ClienteDaoImpl clienteDaoImpl;

}
