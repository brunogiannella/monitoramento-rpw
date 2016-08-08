package br.com.rpw.monitoramento.api.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.rpw.monitoramento.api.constantes.TipoTelefoneEnum;

@Entity
@Table(name="TELEFONE")
public class Telefone {

	private Integer ddd;
	private Long telefone;
	private TipoTelefoneEnum tipoTelefone;
	
}
