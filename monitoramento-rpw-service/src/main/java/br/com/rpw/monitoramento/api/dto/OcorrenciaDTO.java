package br.com.rpw.monitoramento.api.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OcorrenciaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6333813086923359967L;

	private Long idOcorrencia;
	private Long idTipoOcorrencia;
	private String descTipoOcorrencia;
	private Long idCliente;
	private Long idTurno;
	private String nomeUsuario;
	private Long codigoUsuario;
	private List<CampoCadastroOcorrenciaDTO> campos = new ArrayList<CampoCadastroOcorrenciaDTO>();

	public Long getIdOcorrencia() {
		return idOcorrencia;
	}

	public void setIdOcorrencia(Long idOcorrencia) {
		this.idOcorrencia = idOcorrencia;
	}

	public Long getIdTipoOcorrencia() {
		return idTipoOcorrencia;
	}

	public void setIdTipoOcorrencia(Long idTipoOcorrencia) {
		this.idTipoOcorrencia = idTipoOcorrencia;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Long getIdTurno() {
		return idTurno;
	}

	public void setIdTurno(Long idTurno) {
		this.idTurno = idTurno;
	}

	public Long getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(Long codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public List<CampoCadastroOcorrenciaDTO> getCampos() {
		return campos;
	}

	public void setCampos(List<CampoCadastroOcorrenciaDTO> campos) {
		this.campos = campos;
	}

	public String getDescTipoOcorrencia() {
		return descTipoOcorrencia;
	}

	public void setDescTipoOcorrencia(String descTipoOcorrencia) {
		this.descTipoOcorrencia = descTipoOcorrencia;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
}
