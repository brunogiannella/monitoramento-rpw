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

import br.com.rpw.monitoramento.api.constantes.TipoCampoEnum;

@Entity
@Table(name = "CAMPO_OCORRENCIA")
public class CampoOcorrencia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5808745048048530854L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ID_TIPO_OCORRENCIA")
	private TipoOcorrencia tipoOcorrencia;
	
	@Column(name = "DESCRICAO", nullable = false)
	private String descricao;
	
	@Column(name = "TIPO_CAMPO", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoCampoEnum tipoCampo;
	
	@Column(name = "ORDEM", nullable = false)
	private Integer ordem;
	
	@Column(name = "TAMANHO_COLUNA", nullable = false)
	private Integer porcentagemColuna;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoOcorrencia getTipoOcorrencia() {
		return tipoOcorrencia;
	}

	public void setTipoOcorrencia(TipoOcorrencia tipoOcorrencia) {
		this.tipoOcorrencia = tipoOcorrencia;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoCampoEnum getTipoCampo() {
		return tipoCampo;
	}

	public void setTipoCampo(TipoCampoEnum tipoCampo) {
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
