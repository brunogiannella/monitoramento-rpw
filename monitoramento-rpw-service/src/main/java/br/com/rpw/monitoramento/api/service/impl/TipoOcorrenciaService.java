package br.com.rpw.monitoramento.api.service.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rpw.monitoramento.api.constantes.TipoCampoEnum;
import br.com.rpw.monitoramento.api.dao.impl.CampoOcorrenciaDaoImpl;
import br.com.rpw.monitoramento.api.dao.impl.TipoOcorrenciaDaoImpl;
import br.com.rpw.monitoramento.api.dao.impl.TipoOcorrenciaPersonalizadaDaoImpl;
import br.com.rpw.monitoramento.api.dto.CampoOcorrenciaDTO;
import br.com.rpw.monitoramento.api.dto.TipoOcorrenciaDTO;
import br.com.rpw.monitoramento.api.model.CampoOcorrencia;
import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.TipoOcorrencia;
import br.com.rpw.monitoramento.api.model.TipoOcorrenciaPersonalizada;
import br.com.rpw.monitoramento.api.service.ITipoOcorrenciaService;

@Service
@Transactional
public class TipoOcorrenciaService implements ITipoOcorrenciaService {

	@Autowired
	private TipoOcorrenciaDaoImpl tipoOcorrenciaDaoImpl;
	
	@Autowired
	private TipoOcorrenciaPersonalizadaDaoImpl tipoOcorrenciaPersonalizadaDaoImpl;
	
	@Autowired
	private CampoOcorrenciaDaoImpl campoOcorrenciaDaoImpl;
	
	@Override
	public Long cadastrarTipoOcorrencia(TipoOcorrenciaDTO tipoOcorrenciaDTO)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, ParseException {
		
		TipoOcorrencia tipoOcorrencia = converterTipoOcorrenciaDTOEmTipoOcorrencia(tipoOcorrenciaDTO);
		tipoOcorrenciaDaoImpl.salvarTipoOcorrencia(tipoOcorrencia);
		
		int indice = 0;
		for(CampoOcorrenciaDTO camposDto : tipoOcorrenciaDTO.getCampos()) {
			CampoOcorrencia campo = new CampoOcorrencia();
			campo.setDescricao(camposDto.getDescricao());
			campo.setOrdem(camposDto.getOrdem());
			
			if(TipoCampoEnum.TEXTO.getDescricao().equals(camposDto.getTipoCampo())) {
				campo.setTipoCampo(TipoCampoEnum.TEXTO);
			} else if(TipoCampoEnum.DATA.getDescricao().equals(camposDto.getTipoCampo())) {
				campo.setTipoCampo(TipoCampoEnum.DATA);
			} else if(TipoCampoEnum.EQUIPAMENTOS.getDescricao().equals(camposDto.getTipoCampo())) {
				campo.setTipoCampo(TipoCampoEnum.EQUIPAMENTOS);
			}
			
			campo.setOrdem(indice);
			campo.setTipoOcorrencia(tipoOcorrencia);
			
			campoOcorrenciaDaoImpl.salvarCampoOcorrencia(campo);
			indice++;
		}
		
		return tipoOcorrencia.getId();
	}
	
	@Override
	public Long cadastrarTipoOcorrenciaPersonalizada(TipoOcorrenciaDTO tipoOcorrenciaDTO)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, ParseException {
		TipoOcorrenciaPersonalizada tipoOcorrencia = converterTipoOcorrenciaDTOEmTipoOcorrenciaPersonalizada(tipoOcorrenciaDTO);
		tipoOcorrenciaPersonalizadaDaoImpl.salvarTipoOcorrencia(tipoOcorrencia);
		
		return tipoOcorrencia.getId();
	}

	@Override
	public TipoOcorrencia consultarTipoOcorrencia(Long idTipoOcorrencia)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return tipoOcorrenciaDaoImpl.consultarTipoOcorrencia(idTipoOcorrencia);
	}
	
	@Override
	public TipoOcorrenciaPersonalizada consultarTipoOcorrenciaPersonalizada(Long idTipoOcorrencia)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return tipoOcorrenciaPersonalizadaDaoImpl.consultarTipoOcorrencia(idTipoOcorrencia);
	}

	@Override
	public List<TipoOcorrenciaDTO> listarTiposOcorrencia() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		List<TipoOcorrencia> tiposOcorrencia = tipoOcorrenciaDaoImpl.listarTipoOcorrencias();
		
		List<TipoOcorrenciaDTO> tiposOcorrenciaDto = new ArrayList<TipoOcorrenciaDTO>();
		
		for(TipoOcorrencia tipo : tiposOcorrencia) {
			TipoOcorrenciaDTO tipoDto = new TipoOcorrenciaDTO();
			tipoDto.setId(tipo.getId());
			tipoDto.setDescricao(tipo.getDescricao());
			
			List<CampoOcorrencia> campos = campoOcorrenciaDaoImpl.consultarCamposOcorrencia(tipo);
			
			if(campos != null) {
				tipoDto.setQuantidadeCampos(campos.size());
			}
			
			tiposOcorrenciaDto.add(tipoDto);
		}
		
		return tiposOcorrenciaDto;
	}
	
	@Override
	public List<TipoOcorrenciaDTO> listarTiposOcorrenciaPersonalizada() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		List<TipoOcorrenciaPersonalizada> tiposOcorrencia = tipoOcorrenciaPersonalizadaDaoImpl.listarTipoOcorrencias();
		
		List<TipoOcorrenciaDTO> tiposOcorrenciaDto = new ArrayList<TipoOcorrenciaDTO>();
		
		for(TipoOcorrenciaPersonalizada tipo : tiposOcorrencia) {
			TipoOcorrenciaDTO tipoDto = new TipoOcorrenciaDTO();
			tipoDto.setId(tipo.getId());
			tipoDto.setDescricao(tipo.getDescricao());
			tiposOcorrenciaDto.add(tipoDto);
		}
		
		return tiposOcorrenciaDto;
	}

	@Override
	public List<TipoOcorrencia> listarTiposOcorrencia(Cliente cliente)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private static TipoOcorrencia converterTipoOcorrenciaDTOEmTipoOcorrencia(TipoOcorrenciaDTO tipoOcorrenciaDTO) {
		TipoOcorrencia tipoOcorrencia = new TipoOcorrencia();
		tipoOcorrencia.setDescricao(tipoOcorrenciaDTO.getDescricao());
		return tipoOcorrencia;
	}
	
	private TipoOcorrenciaPersonalizada converterTipoOcorrenciaDTOEmTipoOcorrenciaPersonalizada(
			TipoOcorrenciaDTO tipoOcorrenciaDTO) {
		TipoOcorrenciaPersonalizada tipoOcorrencia = new TipoOcorrenciaPersonalizada();
		tipoOcorrencia.setDescricao(tipoOcorrenciaDTO.getDescricao());
		return tipoOcorrencia;
	}

	@Override
	public void removerTipoOcorrencia(Long idTipoOcorrencia) {
		tipoOcorrenciaDaoImpl.deleteTipoOcorrencia(idTipoOcorrencia);
	}
	
	@Override
	public void removerTipoOcorrenciaPersonalizada(Long idTipoOcorrencia) {
		tipoOcorrenciaPersonalizadaDaoImpl.deleteTipoOcorrencia(idTipoOcorrencia);
	}

	@Override
	public BigInteger consultarQuantidadeTipoOcorrenciasAtivas() {
		return tipoOcorrenciaDaoImpl.consultarQuantidadeTipoOcorrenciasAtivas();
	}

	@Override
	public BigInteger consultarQuantidadeTipoOcorrenciasPersonalizadasAtivas() {
		return tipoOcorrenciaPersonalizadaDaoImpl.consultarQuantidadeTipoOcorrenciaPersonalizadassAtivas();
	}

}
