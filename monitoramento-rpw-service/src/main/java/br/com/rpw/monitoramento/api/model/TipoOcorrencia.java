package br.com.rpw.monitoramento.api.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TIPO_OCORRENCIA")
public class TipoOcorrencia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -17585988385706089L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "DESCRICAO", nullable = false)
	private String descricao;
	
	@OneToMany(mappedBy = "tipoOcorrencia", targetEntity = CampoOcorrencia.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<CampoOcorrencia> campos;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "tiposOcorrencia")
	private Set<Cliente> clientes = new HashSet<Cliente>(0);
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Set<CampoOcorrencia> getCampos() {
		return campos;
	}

	public void setCampos(Set<CampoOcorrencia> campos) {
		this.campos = campos;
	}

	public Set<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(Set<Cliente> clientes) {
		this.clientes = clientes;
	}
	
}
