package br.com.rpw.monitoramento.api.dto;

public class CameraDTO {

	private Integer numeroCamera;
	private String descricaoCamera;
	private String localizacaoCamera;
	private String tipoCamera;

	public Integer getNumeroCamera() {
		return numeroCamera;
	}

	public void setNumeroCamera(Integer numeroCamera) {
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

}
