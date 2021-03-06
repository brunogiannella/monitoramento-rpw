package br.com.rpw.monitoramento.api.dto;

public class CameraDTO {

	private Long id;
	private String numeroCamera;
	private String descricaoCamera;
	private String localizacaoCamera;
	private Long idCliente;
	private String tipoCamera;

	public String getNumeroCamera() {
		return numeroCamera;
	}

	public void setNumeroCamera(String numeroCamera) {
		this.numeroCamera = numeroCamera;
	}

	public String getDescricaoCamera() {
		return descricaoCamera;
	}

	public void setDescricaoCamera(String descricaoCamera) {
		this.descricaoCamera = descricaoCamera;
	}

	public String getLocalizacaoCamera() {
		return localizacaoCamera;
	}

	public void setLocalizacaoCamera(String localizacaoCamera) {
		this.localizacaoCamera = localizacaoCamera;
	}

	public String getTipoCamera() {
		return tipoCamera;
	}

	public void setTipoCamera(String tipoCamera) {
		this.tipoCamera = tipoCamera;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	
}
