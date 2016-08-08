package br.com.rpw.monitoramento.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.rpw.monitoramento.api.constantes.TipoTelefoneEnum;
import br.com.rpw.monitoramento.api.model.pk.TelefonePK;

@Entity
@Table(name = "TELEFONE")
@IdClass(value=TelefonePK.class)
public class Telefone implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 881295858930258074L;

	@Id
	@Column(name = "DDD", nullable = false)
	private Integer ddd;

	@Id
	@Column(name = "TELEFONE", nullable = false)
	private Long telefone;

	@Column(name = "TIPO_TELEFONE", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoTelefoneEnum tipoTelefone;

	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;

	public Integer getDdd() {
		return ddd;
	}

	public void setDdd(Integer ddd) {
		this.ddd = ddd;
	}

	public Long getTelefone() {
		return telefone;
	}

	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}

	public TipoTelefoneEnum getTipoTelefone() {
		return tipoTelefone;
	}

	public void setTipoTelefone(TipoTelefoneEnum tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
