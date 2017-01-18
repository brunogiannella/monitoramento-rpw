package br.com.rpw.monitoramento.api.dto;

public class CampoOcorrenciaDTO {

	private Long id;
	private String descricao;
	private String tipoCampo;
	private Integer ordem;
	private Integer porcentagemColuna;

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

	public String getTipoCampo() {
		return tipoCampo;
	}

	public void setTipoCampo(String tipoCampo) {
		this.tipoCampo = tipoCampo;
	}

	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	public Integer getPorcentagemColuna() {
		return porcentagemColuna;
	}

	public void setPorcentagemColuna(Integer porcentagemColuna) {
		this.porcentagemColuna = porcentagemColuna;
	}

}
