package br.com.rpw.monitoramento.api.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ENDERECO")
public class Endereco {

	private String estado;
	private String cidade;
	private String bairro;
	private String logradouro;
	private Long cep;
	
}
