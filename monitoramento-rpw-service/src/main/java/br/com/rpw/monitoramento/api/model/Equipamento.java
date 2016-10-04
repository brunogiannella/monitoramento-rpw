package br.com.rpw.monitoramento.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.rpw.monitoramento.api.constantes.TipoEquipamentoEnum;

@Entity
@Table(name = "EQUIPAMENTO")
public class Equipamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1162801318514465854L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NUMERO")
	private Integer numero;

	@Column(name = "DESCRICAO")
	private String descricao;

	@Column(name = "LOCALIZACAO")
	private String localizacao;
	
	@Column(name = "ATIVO")
	private Boolean ativo;
	
	@Column(name = "TIPO_EQUIPAMENTO")
	@Enumerated(EnumType.STRING)
	private TipoEquipamentoEnum tipoEquipamento;
	
	@ManyToOne
	@JoinColumn(name = "ID_CLIENTE")
	private Cliente cliente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public TipoEquipamentoEnum getTipoEquipamento() {
		return tipoEquipamento;
	}

	public void setTipoEquipamento(TipoEquipamentoEnum tipoEquipamento) {
		this.tipoEquipamento = tipoEquipamento;
	}
	
}
