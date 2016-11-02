package br.com.rpw.monitoramento.api.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.rpw.monitoramento.api.dao.impl.OcorrenciaDaoImpl;
import br.com.rpw.monitoramento.api.dto.OcorrenciaDTO;
import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.Ocorrencia;
import br.com.rpw.monitoramento.api.model.TipoOcorrencia;
import br.com.rpw.monitoramento.api.model.Turno;
import br.com.rpw.monitoramento.api.model.Usuario;
import br.com.rpw.monitoramento.api.service.IOcorrenciaService;

@Service
@Transactional
public class OcorrenciaService implements IOcorrenciaService {

	@Autowired
	private OcorrenciaDaoImpl ocorrenciaDaoImpl;

	@Override
	public void cadastrarOcorrencia(OcorrenciaDTO ocorrenciaDto)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, ParseException {
		ocorrenciaDaoImpl.salvarOcorrencia(converterOcorrenciaDTOEmOcorrencia(ocorrenciaDto));
	}

	@Override
	public Ocorrencia consultarOcorrencia(Long idOcorrencia)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Ocorrencia ocorrencia = new Ocorrencia();
		ocorrencia.setId(idOcorrencia);
		
		return ocorrenciaDaoImpl.consultarOcorrencia(ocorrencia);
	}

	@Override
	public List<Ocorrencia> consultarOcorrencias(Turno turno)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return ocorrenciaDaoImpl.listarOcorrencias(turno);
	}
	
	private Ocorrencia converterOcorrenciaDTOEmOcorrencia(OcorrenciaDTO ocorrenciaDto) {
		Ocorrencia ocorrencia = new Ocorrencia();
		ocorrencia.setDataCadastro(new Date());
		
		Cliente cliente = new Cliente();
		cliente.setId(ocorrenciaDto.getIdCliente());
		ocorrencia.setCliente(cliente);
		
		Usuario usuario = new Usuario();
		usuario.setId(ocorrenciaDto.getCodigoUsuario());
		ocorrencia.setUsuario(usuario);
		
		Turno turno = new Turno();
		turno.setId(ocorrenciaDto.getIdTurno());
		ocorrencia.setTurno(turno);
		
		TipoOcorrencia tipoOcorrencia = new TipoOcorrencia();
		tipoOcorrencia.setId(ocorrenciaDto.getIdTipoOcorrencia());
		ocorrencia.setTipoOcorrencia(tipoOcorrencia);
		
		Gson gson = new GsonBuilder().create();
		String valoresJson = gson.toJson(ocorrenciaDto.getCampos());
		ocorrencia.setValores(valoresJson);
		
		return ocorrencia;
	}

}
