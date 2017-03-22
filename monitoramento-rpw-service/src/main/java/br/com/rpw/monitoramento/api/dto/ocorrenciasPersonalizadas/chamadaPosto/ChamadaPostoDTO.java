package br.com.rpw.monitoramento.api.dto.ocorrenciasPersonalizadas.chamadaPosto;

import java.util.ArrayList;
import java.util.List;

public class ChamadaPostoDTO {
	private List<EfetivoDTO> efetivos = new ArrayList<EfetivoDTO>();

	public List<EfetivoDTO> getEfetivos() {
		return efetivos;
	}

	public void setEfetivos(List<EfetivoDTO> efetivos) {
		this.efetivos = efetivos;
	}

}
