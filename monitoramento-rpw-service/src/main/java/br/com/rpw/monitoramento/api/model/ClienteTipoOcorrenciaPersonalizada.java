package br.com.rpw.monitoramento.api.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CLIENTE_OCORRENCIA_PERSONALIZADA")
public class ClienteTipoOcorrenciaPersonalizada implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1904558142036641028L;

	@Id
	@ManyToOne
	@JoinColumn(name = "ID_CLIENTE", nullable = false)
	private Cliente cliente;

	@Id
	@ManyToOne
	@JoinColumn(name = "ID_TPO_OCOR", nullable = false)
	private TipoOcorrenciaPersonalizada tipoOcorrencia;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public TipoOcorrenciaPersonalizada getTipoOcorrencia() {
		return tipoOcorrencia;
	}

	public void setTipoOcorrencia(TipoOcorrenciaPersonalizada tipoOcorrencia) {
		this.tipoOcorrencia = tipoOcorrencia;
	}

}
