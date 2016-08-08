package br.com.rpw.monitoramento.api.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CLIENTE")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private List<Endereco> endereco;
	private List<Telefone> telefone;
	private Boolean emailAutomatico;
	private List<String> emailResposavel;
	private List<String> emailsRelatorioDiario;
	private List<String> emailsRelatorioMensal;
	private List<Camera> cameras;

}
