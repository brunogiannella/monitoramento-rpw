package br.com.rpw.monitoramento.api.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.rpw.monitoramento.api.constantes.CondicoesClimaticasEnum;
import br.com.rpw.monitoramento.api.constantes.PeriodoEnum;
import br.com.rpw.monitoramento.api.constantes.StatusTurnoEnum;
import br.com.rpw.monitoramento.api.constantes.TempoEnum;

@Entity
@Table(name = "TURNO")
public class Turno implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6017553201802992180L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_INICIO")
	private Date dataInicio;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_FIM")
	private Date dataFim;

	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "ID_CLIENTE")
	private Cliente cliente;

	@Column(name = "PERIODO", nullable = false)
	@Enumerated(EnumType.STRING)
	private PeriodoEnum periodo;

	@Column(name = "CONDICAO_CLIMATICA", nullable = false)
	@Enumerated(EnumType.STRING)
	private CondicoesClimaticasEnum condicaoClimatica;

	@Column(name = "TEMPO", nullable = false)
	@Enumerated(EnumType.STRING)
	private TempoEnum tempo;

	@Column(name = "STATUS_TURNO", nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusTurnoEnum status;

	@OneToMany(mappedBy = "turno", targetEntity = Ocorrencia.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Ocorrencia> ocorrencias;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public PeriodoEnum getPeriodo() {
		return periodo;
	}

	public void setPeriodo(PeriodoEnum periodo) {
		this.periodo = periodo;
	}

	public CondicoesClimaticasEnum getCondicaoClimatica() {
		return condicaoClimatica;
	}

	public void setCondicaoClimatica(CondicoesClimaticasEnum condicaoClimatica) {
		this.condicaoClimatica = condicaoClimatica;
	}

	public TempoEnum getTempo() {
		return tempo;
	}

	public void setTempo(TempoEnum tempo) {
		this.tempo = tempo;
	}

	public StatusTurnoEnum getStatus() {
		return status;
	}

	public void setStatus(StatusTurnoEnum status) {
		this.status = status;
	}

	public List<Ocorrencia> getOcorrencias() {
		return ocorrencias;
	}

	public void setOcorrencias(List<Ocorrencia> ocorrencias) {
		this.ocorrencias = ocorrencias;
	}

}
