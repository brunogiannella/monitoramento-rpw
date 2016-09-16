package br.com.rpw.monitoramento.api.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rpw.monitoramento.api.constantes.TipoCampoEnum;
import br.com.rpw.monitoramento.api.dao.impl.TipoOcorrenciaDaoImpl;
import br.com.rpw.monitoramento.api.dto.CampoOcorrenciaDTO;
import br.com.rpw.monitoramento.api.dto.TipoOcorrenciaDTO;
import br.com.rpw.monitoramento.api.model.CampoOcorrencia;
import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.TipoOcorrencia;
import br.com.rpw.monitoramento.api.service.ITipoOcorrenciaService;

@Service
@Transactional
public class TipoOcorrenciaService implements ITipoOcorrenciaService {

	@Autowired
	private TipoOcorrenciaDaoImpl tipoOcorrenciaDaoImpl;

	@Override
	public Long cadastrarTipoOcorrencia(TipoOcorrenciaDTO tipoOcorrenciaDTO)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, ParseException {
		
		TipoOcorrencia tipoOcorrencia = converterTipoOcorrenciaDTOEmTipoOcorrencia(tipoOcorrenciaDTO);
		tipoOcorrenciaDaoImpl.salvarTipoOcorrencia(tipoOcorrencia);
		
		int indice = 0;
		tipoOcorrencia.setCampos(new HashSet<CampoOcorrencia>());
		for(CampoOcorrenciaDTO camposDto : tipoOcorrenciaDTO.getCampos()) {
			CampoOcorrencia campo = new CampoOcorrencia();
			campo.setDescricao(camposDto.getDescricao());
			campo.setOrdem(camposDto.getOrdem());
			
			if(TipoCampoEnum.TEXTO.getDescricao().equals(camposDto.getTipoCampo())) {
				campo.setTipoCampo(TipoCampoEnum.TEXTO);
			} else if(TipoCampoEnum.DATA.getDescricao().equals(camposDto.getTipoCampo())) {
				campo.setTipoCampo(TipoCampoEnum.DATA);
			}
			
			campo.setOrdem(indice);
			campo.setTipoOcorrencia(tipoOcorrencia);
			tipoOcorrencia.getCampos().add(campo);
			
			indice++;
		}
		
		tipoOcorrenciaDaoImpl.atualizarTipoOcorrencia(tipoOcorrencia);
		
		return tipoOcorrencia.getId();
	}

	@Override
	public TipoOcorrencia consultarTipoOcorrencia(Long idTipoOcorrencia)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return tipoOcorrenciaDaoImpl.consultarTipoOcorrencia(idTipoOcorrencia);
	}

	@Override
	public List<TipoOcorrencia> listarTiposOcorrencia() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return tipoOcorrenciaDaoImpl.listarTipoOcorrencias();
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

}
