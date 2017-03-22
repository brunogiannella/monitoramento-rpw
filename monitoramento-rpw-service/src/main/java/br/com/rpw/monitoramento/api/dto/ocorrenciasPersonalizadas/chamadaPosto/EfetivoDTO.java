package br.com.rpw.monitoramento.api.dto.ocorrenciasPersonalizadas.chamadaPosto;

import java.util.ArrayList;
import java.util.List;

public class EfetivoDTO {

	private String nomeEfetivo;
	private String funcao;
	private List<VerificacaoEfetivoDTO> verificacoesEfetivo = new ArrayList<VerificacaoEfetivoDTO>();

	public String getNomeEfetivo() {
		return nomeEfetivo;
	}

	public void setNomeEfetivo(String nomeEfetivo) {
		this.nomeEfetivo = nomeEfetivo;
	}

	public List<VerificacaoEfetivoDTO> getVerificacoesEfetivo() {
		return verificacoesEfetivo;
	}

	public void setVerificacoesEfetivo(List<VerificacaoEfetivoDTO> verificacoesEfetivo) {
		this.verificacoesEfetivo = verificacoesEfetivo;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	

}
