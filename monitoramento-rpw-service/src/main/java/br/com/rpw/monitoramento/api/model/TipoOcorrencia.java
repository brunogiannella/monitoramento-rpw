package br.com.rpw.monitoramento.api.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	
	@Column(name = "ATIVO", nullable = false)
	private Boolean ativo;
	
	@Transient
	private List<CampoOcorrencia> campos;
	
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

	public List<CampoOcorrencia> getCampos() {
		return campos;
	}

	public void setCampos(List<CampoOcorrencia> campos) {
		this.campos = campos;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}
