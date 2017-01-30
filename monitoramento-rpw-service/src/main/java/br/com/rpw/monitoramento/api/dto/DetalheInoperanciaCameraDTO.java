package br.com.rpw.monitoramento.api.dto;

import java.io.Serializable;

public class DetalheInoperanciaCameraDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7856811094273640592L;
	
	private String descricaoCamera;
	private String inicio;
	private String fim;

	public String getDescricaoCamera() {
		return descricaoCamera;
	}

	public void setDescricaoCamera(String descricaoCamera) {
		this.descricaoCamera = descricaoCamera;
	}

	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public String getFim() {
		return fim;
	}

	public void setFim(String fim) {
		this.fim = fim;
	}
	
}
