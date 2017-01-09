package br.com.rpw.monitoramento.api.dto;

import java.io.Serializable;

public class ImagemCameraDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5308027003435655266L;
	
	private String descricaoCamera;
	private Float horasFora;

	public String getDescricaoCamera() {
		return descricaoCamera;
	}

	public void setDescricaoCamera(String descricaoCamera) {
		this.descricaoCamera = descricaoCamera;
	}

	public Float getHorasFora() {
		return horasFora;
	}

	public void setHorasFora(Float horasFora) {
		this.horasFora = horasFora;
	}

}
