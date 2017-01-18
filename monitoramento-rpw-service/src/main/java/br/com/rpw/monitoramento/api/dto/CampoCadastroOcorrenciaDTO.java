package br.com.rpw.monitoramento.api.dto;

import java.io.Serializable;

public class CampoCadastroOcorrenciaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2449055076078815780L;

	private String descricao;
	private String valor;
	private String tipo;
	private String tamanho;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

}
