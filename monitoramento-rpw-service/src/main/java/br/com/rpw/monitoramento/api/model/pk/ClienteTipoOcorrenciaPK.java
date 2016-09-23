package br.com.rpw.monitoramento.api.model.pk;

import java.io.Serializable;

import javax.persistence.Id;

import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.TipoOcorrencia;

public class ClienteTipoOcorrenciaPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2965979166820566632L;

	@Id
	private Cliente cliente;

	@Id
	private TipoOcorrencia tipoOcorrencia;

	public ClienteTipoOcorrenciaPK() {
		// Your class must have a no-arq constructor
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ClienteTipoOcorrenciaPK) {
			ClienteTipoOcorrenciaPK carPk = (ClienteTipoOcorrenciaPK) obj;

			if (!carPk.getCliente().getId().equals(cliente.getId()) || !carPk.getTipoOcorrencia().getId().equals(tipoOcorrencia.getId())) {
				return false;
			}

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return cliente.getId().hashCode() + tipoOcorrencia.getId().hashCode();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public TipoOcorrencia getTipoOcorrencia() {
		return tipoOcorrencia;
	}

	public void setTipoOcorrencia(TipoOcorrencia tipoOcorrencia) {
		this.tipoOcorrencia = tipoOcorrencia;
	}

}
