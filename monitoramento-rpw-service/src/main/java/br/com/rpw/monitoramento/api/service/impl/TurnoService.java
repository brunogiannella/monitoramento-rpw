package br.com.rpw.monitoramento.api.service.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
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

import br.com.rpw.monitoramento.api.constantes.PeriodoEnum;
import br.com.rpw.monitoramento.api.constantes.StatusTurnoEnum;
import br.com.rpw.monitoramento.api.dao.impl.CameraDaoImpl;
import br.com.rpw.monitoramento.api.dao.impl.OcorrenciaDaoImpl;
import br.com.rpw.monitoramento.api.dao.impl.SituacaoCameraDaoImpl;
import br.com.rpw.monitoramento.api.dao.impl.TurnoDaoImpl;
import br.com.rpw.monitoramento.api.dto.CampoCadastroOcorrenciaDTO;
import br.com.rpw.monitoramento.api.dto.DetalheInoperanciaCameraDTO;
import br.com.rpw.monitoramento.api.dto.GrupoOcorrenciasDto;
import br.com.rpw.monitoramento.api.dto.ImagemCameraDTO;
import br.com.rpw.monitoramento.api.dto.OcorrenciaDTO;
import br.com.rpw.monitoramento.api.dto.TurnoDTO;
import br.com.rpw.monitoramento.api.model.Camera;
import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.Ocorrencia;
import br.com.rpw.monitoramento.api.model.SituacaoCamera;
import br.com.rpw.monitoramento.api.model.Turno;
import br.com.rpw.monitoramento.api.model.Usuario;
import br.com.rpw.monitoramento.api.service.ITurnoService;

@Service
@Transactional
public class TurnoService implements ITurnoService {

	@Autowired
	private TurnoDaoImpl turnoDaoImpl;
	
	@Autowired
	private OcorrenciaDaoImpl ocorrenciaDaoImpl;
	
	@Autowired
	private CameraDaoImpl cameraDaoImpl;
	
	@Autowired
	private SituacaoCameraDaoImpl situacaoCameraDaoImpl;

	@Override
	public Long iniciarTurno(TurnoDTO iniciarTurnoRequestDTO)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, ParseException {
		Turno turno = converterIniciarTurnoRequestDTOEmTurno(iniciarTurnoRequestDTO);
		turnoDaoImpl.salvarTurno(turno);
		return turno.getId();
	}

	@Override
	public boolean finalizarTurno(TurnoDTO turnoDTO) throws NoSuchAlgorithmException, UnsupportedEncodingException {
	
		try {
			Turno turno = turnoDaoImpl.consultarTurno(turnoDTO.getId());
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			turno.setDataFim(formato.parse(turnoDTO.getDataFim().replace("T", " ")));
			turno.setStatus(StatusTurnoEnum.AGUARDANDO_VALIDACAO);
			turnoDaoImpl.atualizarTurno(turno);
			
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	@Override
	public Turno consultarTurno(Long idTurno) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return turnoDaoImpl.consultarTurno(idTurno);
	}

	@Override
	public List<Turno> consultarTurnos(Long idUsuario, StatusTurnoEnum status) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Usuario usuario = new Usuario();
		usuario.setId(idUsuario);
		return turnoDaoImpl.listarTurnos(usuario, status);
	}
	
	@Override
	public List<Turno> listarUltimosDezTurnosCliente(Cliente cliente) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return turnoDaoImpl.listarUltimosDezTurnosCliente(cliente);
	}
	
	@Override
	public TurnoDTO consultarTurnoDetalhado(Long idTurno) {
		Turno turno = turnoDaoImpl.consultarTurno(idTurno);
		turno.setOcorrencias(ocorrenciaDaoImpl.listarOcorrencias(turno));
		TurnoDTO turnoDTO = converterTurnoEmTurnoDTO(turno);
		
		return turnoDTO;
	}
	
	private Turno converterIniciarTurnoRequestDTOEmTurno(TurnoDTO iniciarTurnoRequestDTO) throws ParseException {
		Turno turno = new Turno();
		
		turno.setLiderSeguranca(iniciarTurnoRequestDTO.getLiderSeguranca());
		
		Cliente cliente = new Cliente();
		cliente.setId(iniciarTurnoRequestDTO.getIdCliente());
		turno.setCliente(cliente);
		
		Usuario usuario = new Usuario();
		usuario.setId(iniciarTurnoRequestDTO.getIdUsuario());
		turno.setUsuario(usuario);
		
//		CondicoesClimaticasEnum condicoesClimaticasEnum = null;
//		for(CondicoesClimaticasEnum tipo : CondicoesClimaticasEnum.values()) {
//			if(tipo.getDescricao().equals(iniciarTurnoRequestDTO.getCondicaoClimatica())) {
//				condicoesClimaticasEnum = tipo;
//				break;
//			}
//		}
//		turno.setCondicaoClimatica(condicoesClimaticasEnum);
		
		PeriodoEnum periodoEnum = null;
		for(PeriodoEnum tipo : PeriodoEnum.values()) {
			if(tipo.getDescricao().equals(iniciarTurnoRequestDTO.getPeriodo())) {
				periodoEnum = tipo;
				break;
			}
		}
		turno.setPeriodo(periodoEnum);
		
//		TempoEnum tempoEnum = null;
//		for(TempoEnum tipo : TempoEnum.values()) {
//			if(tipo.getDescricao().equals(iniciarTurnoRequestDTO.getTempo())) {
//				tempoEnum = tipo;
//				break;
//			}
//		}
//		turno.setTempo(tempoEnum);
		turno.setStatus(StatusTurnoEnum.EM_ANDAMENTO);
		
		String operadores = "";
		for(String operador : iniciarTurnoRequestDTO.getOperadores()) {
			operadores = operadores + operador + ";";
		}
		turno.setOperadores(operadores);
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		turno.setDataInicio(formato.parse(iniciarTurnoRequestDTO.getDataInicio().replace("T", " ")));
		
		if(iniciarTurnoRequestDTO.getDataFim() != null) {
			turno.setDataFim(formato.parse(iniciarTurnoRequestDTO.getDataFim().replace("T", " ")));
		}
		
		return turno;
	}

	public TurnoDTO converterTurnoEmTurnoDTO(Turno turno) {
		TurnoDTO turnoDTO = new TurnoDTO();
		turnoDTO.setId(turno.getId());
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		turnoDTO.setDataInicio(formato.format(turno.getDataInicio()));
		
		if(turno.getDataFim() != null) {
			turnoDTO.setDataFim(formato.format(turno.getDataFim()));
		}
		
		//turnoDTO.setCondicaoClimatica(turno.getCondicaoClimatica().getDescricao());
		turnoDTO.setIdCliente(turno.getCliente().getId());
		turnoDTO.setIdUsuario(turno.getUsuario().getId());
		turnoDTO.setPeriodo(turno.getPeriodo().getDescricao());
		turnoDTO.setStatus(turno.getStatus().getDescricao());
		//turnoDTO.setTempo(turno.getTempo().getDescricao());
		turnoDTO.setNomeCliente(turno.getCliente().getNome());
		turnoDTO.setNomeUsuario(turno.getUsuario().getNome());
		turnoDTO.setLiderSeguranca(turno.getLiderSeguranca());
		
		if(turno.getOperadores() != null) {
			turnoDTO.setOperadores(new ArrayList<String>());
			String[] operadores = turno.getOperadores().split(";");
			for(String oper : operadores) {
				turnoDTO.getOperadores().add(oper);
			}
		}
		
		if(turno.getOcorrencias() != null) {
			
			Map<String, List<OcorrenciaDTO>> gruposOcorrencia = new HashMap<String, List<OcorrenciaDTO>>();
			
			for(Ocorrencia ocorrencia : turno.getOcorrencias()) {
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
							if(camposCadastro.getValor() != null) {
								Camera camera = cameraDaoImpl.consultarCamera(Long.parseLong(camposCadastro.getValor()));
								ocorrenciaDto.setResumoOcorrencia(ocorrenciaDto.getResumoOcorrencia() + camposCadastro.getDescricao() + ": " + camera.getDescricaoCamera() + "; ");
							} else {
								ocorrenciaDto.setResumoOcorrencia(ocorrenciaDto.getResumoOcorrencia() + camposCadastro.getDescricao() + ": Não informado; ");
							}
							
							
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
			
			turnoDTO.setOcorrenciasDto(new ArrayList<GrupoOcorrenciasDto>());
			for (String entry : gruposOcorrencia.keySet()) {
				GrupoOcorrenciasDto grupoOcorrencia = new GrupoOcorrenciasDto();
				grupoOcorrencia.setDescricao(entry);
				grupoOcorrencia.setOcorrenciasDto(gruposOcorrencia.get(entry));
								
				turnoDTO.getOcorrenciasDto().add(grupoOcorrencia);
			}
		}
		
		List<DetalheInoperanciaCameraDTO> detalhesInoperanciaCamera = new ArrayList<DetalheInoperanciaCameraDTO>();
		List<SituacaoCamera> situacoesCameraTurno = situacaoCameraDaoImpl.consultarSituacaoCameraTurno(turno);
		Map<Long, Integer> mapHorasDesligadas = new HashMap<Long, Integer>();
		
		for(SituacaoCamera situacao : situacoesCameraTurno) {
			
			DetalheInoperanciaCameraDTO detalheInoperancia = new DetalheInoperanciaCameraDTO();
			detalheInoperancia.setDescricaoCamera(situacao.getCamera().getDescricaoCamera());
			
			Integer minutos = 0;
			if(situacao.getDataHoraLigada() == null) {
				DateTime dataInicial = new DateTime(situacao.getDataHoraDesligada());
				DateTime dataFinal = new DateTime(turno.getDataFim());
				minutos = minutos + Minutes.minutesBetween(dataInicial, dataFinal).getMinutes();
				
				detalheInoperancia.setInicio(formato.format(situacao.getDataHoraDesligada()));
				
				if(turno.getDataFim() == null) {
					detalheInoperancia.setFim(formato.format(new Date()));
				} else {
					detalheInoperancia.setFim(formato.format(turno.getDataFim()));
				}
				
			} else {
				DateTime dataInicial = new DateTime(situacao.getDataHoraDesligada());
				DateTime dataFinal = new DateTime(situacao.getDataHoraLigada());
				minutos = minutos + Minutes.minutesBetween(dataInicial, dataFinal).getMinutes();
				
				detalheInoperancia.setInicio(formato.format(situacao.getDataHoraDesligada()));
				detalheInoperancia.setFim(formato.format(situacao.getDataHoraLigada()));
			}
			
			detalhesInoperanciaCamera.add(detalheInoperancia);
			
			if(mapHorasDesligadas.containsKey(situacao.getCamera().getId())) {
				mapHorasDesligadas.put(situacao.getCamera().getId(), mapHorasDesligadas.get(situacao.getCamera().getId()) + minutos);
			} else {
				mapHorasDesligadas.put(situacao.getCamera().getId(), minutos);
			}
		}
		
		turnoDTO.setDetalhesInoperanciaCamera(detalhesInoperanciaCamera);
		
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
		
		turnoDTO.setImagemCameraDto(imagensCamera);

		return turnoDTO;
	}

	@Override
	public List<Turno> consultarTurnoAnterior(Cliente cliente) {
		return turnoDaoImpl.consultarTurnoAnterior(cliente);
	}

	@Override
	public List<Turno> consultarTurnosPendentes() {
		List<Turno> turnosAguardandoAprovacao = this.turnoDaoImpl.consultarTurno(StatusTurnoEnum.AGUARDANDO_VALIDACAO, false);
		List<Turno> turnosAprovado = this.turnoDaoImpl.consultarTurno(StatusTurnoEnum.APROVADO, false);
		
		List<Turno> turnosPendentes = new ArrayList<Turno>();
		turnosPendentes.addAll(turnosAguardandoAprovacao);
		turnosPendentes.addAll(turnosAprovado);
		
		return turnosPendentes;
	}
	
	@Override
	public List<Turno> consultarTurnosAndamento() {
		List<Turno> turnosAndamento = this.turnoDaoImpl.consultarTurno(StatusTurnoEnum.EM_ANDAMENTO, false);		
		return turnosAndamento;
	}

	@Override
	public Object consultarQuantidadeTurnosPendentes() {
		return turnoDaoImpl.consultarQuantidadeTurnosPendentes();
	}
	
	@Override
	public Object consultarQuantidadeTurnosAndamento() {
		return turnoDaoImpl.consultarQuantidadeTurnosAndamento();
	}
	
	@Override
	public BigInteger consultarQuantidadeTurnosClienteData(Cliente cliente, Integer mes, Integer ano) {
		return turnoDaoImpl.consultarQuantidadeTurnosClienteData(cliente, mes, ano);
	}

	@Override
	public Boolean aprovarTurno(Long idturno) {
		Turno turno = turnoDaoImpl.consultarTurno(idturno);
		
		if(turno != null) {
			turno.setStatus(StatusTurnoEnum.APROVADO);
			turnoDaoImpl.atualizarTurno(turno);
			return true;
		}
		
		return false;
	}

	@Override
	public List<Turno> consultarTurnosAndamentoCliente(Long idCliente) {
		return this.turnoDaoImpl.consultarTurnosCliente(StatusTurnoEnum.EM_ANDAMENTO, idCliente);
	}

}
