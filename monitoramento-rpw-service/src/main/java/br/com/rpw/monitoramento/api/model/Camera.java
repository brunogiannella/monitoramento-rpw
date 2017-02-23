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

import br.com.rpw.monitoramento.api.constantes.TipoCameraEnum;

@Entity
@Table(name = "CAMERA")
public class Camera implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1162801318514465854L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NUMERO_CAMERA")
	private String numeroCamera;

	@Column(name = "DESCRICAO_CAMERA")
	private String descricaoCamera;

	@Column(name = "LOCALIZACAO_CAMERA")
	private String localizacaoCamera;

	@Column(name = "TIPO_CAMERA")
	@Enumerated(EnumType.STRING)
	private TipoCameraEnum tipoCamera;
	
	@Column(name = "ATIVO")
	private Boolean ativo;
	
	@ManyToOne
	@JoinColumn(name = "ID_CLIENTE")
	private Cliente cliente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroCamera() {
		return numeroCamera;
	}

	public void setNumeroCamera(String numeroCamera) {
		this.numeroCamera = numeroCamera;
	}

	public String getDescricaoCamera() {
		return descricaoCamera;
	}

	public void setDescricaoCamera(String descricaoCamera) {
		this.descricaoCamera = descricaoCamera;
	}

	public String getLocalizacaoCamera() {
		return localizacaoCamera;
	}

	public void setLocalizacaoCamera(String localizacaoCamera) {
		this.localizacaoCamera = localizacaoCamera;
	}

	public TipoCameraEnum getTipoCamera() {
		return tipoCamera;
	}

	public void setTipoCamera(TipoCameraEnum tipoCamera) {
		this.tipoCamera = tipoCamera;
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
	
}
