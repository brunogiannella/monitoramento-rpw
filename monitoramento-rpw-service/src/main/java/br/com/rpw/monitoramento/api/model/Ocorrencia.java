package br.com.rpw.monitoramento.api.model;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.rpw.monitoramento.api.constantes.InformanteOcorrenciaEnum;

@Entity
@Table(name = "OCORRENCIA")
public class Ocorrencia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8170605316397886267L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_TURNO")
	private Turno turno;

	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "ID_CLIENTE")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "ID_TIPO_OCORRENCIA")
	private TipoOcorrencia tipoOcorrencia;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_CADASTRO")
	private Date dataCadastro;
	
	@Column(name = "VALORES", length = 600)
	private String valores;
	
	@Column(name = "INFORMANTE_OCORRENCIA")
	@Enumerated(EnumType.STRING)
	private InformanteOcorrenciaEnum informanteOcorrencia;

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

	public TipoOcorrencia getTipoOcorrencia() {
		return tipoOcorrencia;
	}

	public void setTipoOcorrencia(TipoOcorrencia tipoOcorrencia) {
		this.tipoOcorrencia = tipoOcorrencia;
	}

	public String getValores() {
		return valores;
	}

	public void setValores(String valores) {
		this.valores = valores;
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

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public InformanteOcorrenciaEnum getInformanteOcorrencia() {
		return informanteOcorrencia;
	}

	public void setInformanteOcorrencia(InformanteOcorrenciaEnum informanteOcorrencia) {
		this.informanteOcorrencia = informanteOcorrencia;
	}
	
}
