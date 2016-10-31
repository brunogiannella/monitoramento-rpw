package br.com.rpw.monitoramento.api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "SITUACAO_CAMERA")
public class SituacaoCamera {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_TURNO")
	private Turno turno;

	@ManyToOne
	@JoinColumn(name = "ID_CAMERA")
	private Camera camera;
	
	@ManyToOne
	@JoinColumn(name = "ID_CLIENTE")
	private Cliente cliente;

	@Column(name = "DESLIGADA")
	private Boolean desligada;
	
	@Column(name = "LIGADA")
	private Boolean ligada;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_AVALIACAO_DESLIGADA")
	private Date dataHoraDesligada;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_AVALIACAO_LIGADA")
	private Date dataHoraLigada;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public Boolean getLigada() {
		return ligada;
	}

	public void setLigada(Boolean ligada) {
		this.ligada = ligada;
	}

	public Boolean getDesligada() {
		return desligada;
	}

	public void setDesligada(Boolean desligada) {
		this.desligada = desligada;
	}

	public Date getDataHoraDesligada() {
		return dataHoraDesligada;
	}

	public void setDataHoraDesligada(Date dataHoraDesligada) {
		this.dataHoraDesligada = dataHoraDesligada;
	}

	public Date getDataHoraLigada() {
		return dataHoraLigada;
	}

	public void setDataHoraLigada(Date dataHoraLigada) {
		this.dataHoraLigada = dataHoraLigada;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
