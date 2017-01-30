package br.com.rpw.monitoramento.api.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import br.com.rpw.monitoramento.api.dao.impl.CameraDaoImpl;
import br.com.rpw.monitoramento.api.dao.impl.ClienteDaoImpl;
import br.com.rpw.monitoramento.api.dao.impl.OcorrenciaDaoImpl;
import br.com.rpw.monitoramento.api.dao.impl.SituacaoCameraDaoImpl;
import br.com.rpw.monitoramento.api.dto.CampoCadastroOcorrenciaDTO;
import br.com.rpw.monitoramento.api.dto.ClienteDTO;
import br.com.rpw.monitoramento.api.dto.GrupoOcorrenciasDto;
import br.com.rpw.monitoramento.api.dto.ImagemCameraDTO;
import br.com.rpw.monitoramento.api.dto.OcorrenciaDTO;
import br.com.rpw.monitoramento.api.model.Camera;
import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.Ocorrencia;
import br.com.rpw.monitoramento.api.model.RelatorioMensal;
import br.com.rpw.monitoramento.api.model.SituacaoCamera;
import br.com.rpw.monitoramento.api.service.IRelatorioService;

@Service
@Transactional
public class RelatorioService implements IRelatorioService {

	@Autowired
	private OcorrenciaDaoImpl ocorrenciaDaoImpl;
	
	@Autowired
	private ClienteDaoImpl clienteDaoImpl;
	
	@Autowired
	private SituacaoCameraDaoImpl situacaoCameraDaoImpl;
	
	@Autowired
	private CameraDaoImpl cameraDaoImpl;

	@Override
	public RelatorioMensal consultarRelatorioMensal(Cliente cliente, String mes, String ano) throws ParseException {
		RelatorioMensal relatorioMensal = new RelatorioMensal();
		relatorioMensal.setMes(mes);
		relatorioMensal.setAno(ano);
		
		Cliente clienteMes = clienteDaoImpl.consultarCliente(cliente.getId());
		List<Ocorrencia> ocorrenciasMensalCliente = ocorrenciaDaoImpl.listarOcorrencias(cliente, mes, ano);
		List<SituacaoCamera> imagensCameraMensal = situacaoCameraDaoImpl.listarSituacaoCamerasMensal(clienteMes, mes, ano);
		
		relatorioMensal.setGrupoOcorrencias(converterOcorrenciasEmGrupoOcorrencias(ocorrenciasMensalCliente));
		relatorioMensal.setCliente(converterClienteEmClienteDTO(clienteMes));
		relatorioMensal.setImagemCamera(converterSituacaoCameraEmImagemCamera(imagensCameraMensal, mes, ano));
		return relatorioMensal;
	}
	
	private ClienteDTO converterClienteEmClienteDTO(Cliente cliente) {
		ClienteDTO clienteDto = new ClienteDTO();
		clienteDto.setNome(cliente.getNome());
		
		return clienteDto;
	}
	
	private List<GrupoOcorrenciasDto> converterOcorrenciasEmGrupoOcorrencias(List<Ocorrencia> ocorrencias) {
		Map<String, List<OcorrenciaDTO>> gruposOcorrencia = new HashMap<String, List<OcorrenciaDTO>>();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		if(ocorrencias != null) {
			for(Ocorrencia ocorrencia : ocorrencias) {
				OcorrenciaDTO ocorrenciaDto = new OcorrenciaDTO();
				
				ocorrenciaDto.setIdOcorrencia(ocorrencia.getId());
				ocorrenciaDto.setNomeUsuario(ocorrencia.getUsuario().getNome());
				ocorrenciaDto.setIdTipoOcorrencia(ocorrencia.getTipoOcorrencia().getId());
				ocorrenciaDto.setDescTipoOcorrencia(ocorrencia.getTipoOcorrencia().getDescricao());
				ocorrenciaDto.setDataCadastro(formato.format(ocorrencia.getDataCadastro()));
				
				Gson gson = new GsonBuilder().create();
				List<CampoCadastroOcorrenciaDTO> campos = gson.fromJson(ocorrencia.getValores(), new TypeToken<ArrayList<CampoCadastroOcorrenciaDTO>>(){}.getType());
				ocorrenciaDto.setCampos(campos);
				
				if(campos != null) {
					ocorrenciaDto.setResumoOcorrencia("");
					for(CampoCadastroOcorrenciaDTO camposCadastro : campos) {
						if(camposCadastro.getTipo().equals("EQUIPAMENTOS")) {
							Camera camera = cameraDaoImpl.consultarCamera(Long.parseLong(camposCadastro.getValor()));
							ocorrenciaDto.setResumoOcorrencia(ocorrenciaDto.getResumoOcorrencia() + camposCadastro.getDescricao() + ": " + camera.getDescricaoCamera() + "; ");
						} else {
							ocorrenciaDto.setResumoOcorrencia(ocorrenciaDto.getResumoOcorrencia() + camposCadastro.getDescricao() + ": " + camposCadastro.getValor() + "; " );
						}
						
					}
				}
				
				if(!gruposOcorrencia.containsKey(ocorrenciaDto.getDescTipoOcorrencia())) {
					gruposOcorrencia.put(ocorrenciaDto.getDescTipoOcorrencia(), new ArrayList<OcorrenciaDTO>());
				}
				gruposOcorrencia.get(ocorrenciaDto.getDescTipoOcorrencia()).add(ocorrenciaDto);
			}
		}
		
		List<GrupoOcorrenciasDto> gruposOcorrenciaList = new ArrayList<GrupoOcorrenciasDto>();
		for (String entry : gruposOcorrencia.keySet()) {
			GrupoOcorrenciasDto grupoOcorrencia = new GrupoOcorrenciasDto();
			grupoOcorrencia.setDescricao(entry);
			grupoOcorrencia.setOcorrenciasDto(gruposOcorrencia.get(entry));
							
			gruposOcorrenciaList.add(grupoOcorrencia);
		}
		
		return gruposOcorrenciaList;
	}
	
	private List<ImagemCameraDTO> converterSituacaoCameraEmImagemCamera(List<SituacaoCamera> situacoesCamera, String mes, String ano) throws ParseException {
		List<SituacaoCamera> situacoesCameraTurno = situacoesCamera;
		Map<Long, Integer> mapHorasDesligadas = new HashMap<Long, Integer>();
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	    Date maxDate = null;
	    
	    if(mes.equals("12")) {
	    	String myDateEnd = "01/01/"+String.valueOf((Integer.parseInt(ano) + 1));
		    maxDate = formatter.parse(myDateEnd + " 00:01");
	    } else {
	    	
	    	String mesSeguinte = "";
	    	
	    	if(Integer.parseInt(mes) >= 10) {
	    		mesSeguinte = String.valueOf((Integer.parseInt(mes) + 1));
	    	} else {
	    		mesSeguinte = "0" + String.valueOf((Integer.parseInt(mes) + 1));
	    	}
	    	
	    	String myDateEnd = "01/"+mesSeguinte+"/"+ano;
		    maxDate = formatter.parse(myDateEnd + " 00:01");
	    }
		
		for(SituacaoCamera situacao : situacoesCameraTurno) {
			Integer minutos = 0;
			if(situacao.getDataHoraLigada() == null) {
				DateTime dataInicial = new DateTime(situacao.getDataHoraDesligada());
				DateTime dataFinal = new DateTime(maxDate);
				minutos = minutos + Minutes.minutesBetween(dataInicial, dataFinal).getMinutes();
			} else {
				DateTime dataInicial = new DateTime(situacao.getDataHoraDesligada());
				DateTime dataFinal = new DateTime(situacao.getDataHoraLigada());
				minutos = minutos + Minutes.minutesBetween(dataInicial, dataFinal).getMinutes();
			}
			
			if(mapHorasDesligadas.containsKey(situacao.getCamera().getId())) {
				mapHorasDesligadas.put(situacao.getCamera().getId(), mapHorasDesligadas.get(situacao.getCamera().getId()) + minutos);
			} else {
				mapHorasDesligadas.put(situacao.getCamera().getId(), minutos);
			}
		}
		
		List<ImagemCameraDTO> imagensCamera = new ArrayList<ImagemCameraDTO>();
		for (Long codCamera  : mapHorasDesligadas.keySet()) {
		    ImagemCameraDTO imagemCameraDTO = new ImagemCameraDTO();
		    
		    Camera camera = cameraDaoImpl.consultarCamera(codCamera);
		    if(camera == null) {
		    	continue;
		    }
		    
		    imagemCameraDTO.setDescricaoCamera(camera.getDescricaoCamera());
		    
		    Integer minutos = mapHorasDesligadas.get(codCamera);
		    imagemCameraDTO.setHorasFora(new Float(minutos/60));
		    
		    imagensCamera.add(imagemCameraDTO);
		}
		
		return imagensCamera;
	}
	
}