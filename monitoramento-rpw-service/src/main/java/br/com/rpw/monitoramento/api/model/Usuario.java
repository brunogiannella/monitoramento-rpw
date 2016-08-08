package br.com.rpw.monitoramento.api.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.rpw.monitoramento.api.constantes.TipoUsuarioEnum;

@Entity
@Table(name = "USUARIO")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "USUARIO", nullable = false)
	private String usuario;
	
	@Column(name = "SENHA", nullable = false)
	private String senha;
	
	@Column(name = "EMAIL")
	private String email;
	
	private TipoUsuarioEnum tipoUsuario;
	private List<Telefone> telefones;
	private Endereco endereco;

}
