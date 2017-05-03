package br.com.rpw.monitoramento.api.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import br.com.rpw.monitoramento.api.constantes.InformanteOcorrenciaEnum;
import br.com.rpw.monitoramento.api.constantes.TipoCameraEnum;
import br.com.rpw.monitoramento.api.dao.impl.CameraDaoImpl;
import br.com.rpw.monitoramento.api.dao.impl.ClienteDaoImpl;
import br.com.rpw.monitoramento.api.dao.impl.ClienteTipoOcorrenciaDaoImpl;
import br.com.rpw.monitoramento.api.dao.impl.OcorrenciaDaoImpl;
import br.com.rpw.monitoramento.api.dao.impl.RastreabilidadeDaoImpl;
import br.com.rpw.monitoramento.api.dao.impl.SituacaoCameraDaoImpl;
import br.com.rpw.monitoramento.api.dto.CampoCadastroOcorrenciaDTO;
import br.com.rpw.monitoramento.api.dto.ClienteDTO;
import br.com.rpw.monitoramento.api.dto.GrupoEquipamento;
import br.com.rpw.monitoramento.api.dto.GrupoOcorrenciasDto;
import br.com.rpw.monitoramento.api.dto.OcorrenciaDTO;
import br.com.rpw.monitoramento.api.dto.QuantidadeHorasEquipamentoRelatorio;
import br.com.rpw.monitoramento.api.dto.QuantidadeOcorrencias;
import br.com.rpw.monitoramento.api.dto.QuantidadeOcorrenciasMesDetalhado;
import br.com.rpw.monitoramento.api.model.Camera;
import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.ClienteTipoOcorrencia;
import br.com.rpw.monitoramento.api.model.Ocorrencia;
import br.com.rpw.monitoramento.api.model.Rastreabilidade;
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
	
	@Autowired
	private ClienteTipoOcorrenciaDaoImpl clienteTipoOcorrenciaDaoImpl;
	
	@Autowired
	private RastreabilidadeDaoImpl rastreabilidadeDaoImpl;

	@Override
	public RelatorioMensal consultarRelatorioMensal(Cliente cliente, String mes, String ano) throws ParseException {
		RelatorioMensal relatorioMensal = new RelatorioMensal();
		relatorioMensal.setMes(mes);
		relatorioMensal.setAno(ano);
		
		Cliente clienteMes = clienteDaoImpl.consultarCliente(cliente.getId());
		List<Ocorrencia> ocorrenciasMensalCliente = ocorrenciaDaoImpl.listarOcorrencias(cliente, mes, ano);
		List<ClienteTipoOcorrencia> tiposOcorrenciaCliente = clienteTipoOcorrenciaDaoImpl.listarTipoOcorrencias(clienteMes);
		List<QuantidadeOcorrencias> quantidadeOcorrencias = new ArrayList<QuantidadeOcorrencias>();
		List<QuantidadeOcorrenciasMesDetalhado> quantidadesOcorrenciasMesDetalhadoMonitoramento = new ArrayList<QuantidadeOcorrenciasMesDetalhado>();
		List<QuantidadeOcorrenciasMesDetalhado> quantidadesOcorrenciasMesDetalhadoSeguranca = new ArrayList<QuantidadeOcorrenciasMesDetalhado>();
		
		if(tiposOcorrenciaCliente != null) {
			for(ClienteTipoOcorrencia tipoOcorrencia : tiposOcorrenciaCliente) {
				try {
					QuantidadeOcorrencias quantidadeOcorrencia = new QuantidadeOcorrencias();
					quantidadeOcorrencia.setDescricaoTipoOcorrencia(tipoOcorrencia.getTipoOcorrencia().getDescricao());
					quantidadeOcorrencia.setQuantidadeOcorrencias(ocorrenciaDaoImpl.consultarQuantidadeOcorrenciasClienteTipoOcorrencia(clienteMes, tipoOcorrencia.getTipoOcorrencia(), Integer.parseInt(mes), Integer.parseInt(ano)).intValue());
					quantidadeOcorrencias.add(quantidadeOcorrencia);
					
					QuantidadeOcorrenciasMesDetalhado quantidadeOcorrenciasMesDetalhadoMonitoramento = new QuantidadeOcorrenciasMesDetalhado();
					quantidadeOcorrenciasMesDetalhadoMonitoramento.setDescricaoTipoOcorrencia(tipoOcorrencia.getTipoOcorrencia().getDescricao());
					
					QuantidadeOcorrenciasMesDetalhado quantidadeOcorrenciasMesDetalhadoSeguranca = new QuantidadeOcorrenciasMesDetalhado();
					quantidadeOcorrenciasMesDetalhadoSeguranca.setDescricaoTipoOcorrencia(tipoOcorrencia.getTipoOcorrencia().getDescricao());
					
					List<Ocorrencia> ocorrenciasPeriodo = ocorrenciaDaoImpl.consultarOcorrencia(clienteMes, tipoOcorrencia.getTipoOcorrencia(), Integer.parseInt(mes), Integer.parseInt(ano));
					Map<Integer, Integer> quantidadesSeguranca = new HashMap<Integer, Integer>();
					Map<Integer, Integer> quantidadesMonitoramento = new HashMap<Integer, Integer>();
					
					for(Ocorrencia ocorrencia : ocorrenciasPeriodo) {
						Calendar cal = Calendar.getInstance();
						cal.setTime(ocorrencia.getDataCadastro());
						int day = cal.get(Calendar.DAY_OF_MONTH);

						if(InformanteOcorrenciaEnum.SEGURANCA.equals(ocorrencia.getInformanteOcorrencia())) {
							if(quantidadesSeguranca.containsKey(day)) {
								quantidadesSeguranca.put(day, quantidadesSeguranca.get(day) + 1);
							} else {
								quantidadesSeguranca.put(day, 1);
							}
						} else {
							if(quantidadesMonitoramento.containsKey(day)) {
								quantidadesMonitoramento.put(day, quantidadesMonitoramento.get(day) + 1);
							} else {
								quantidadesMonitoramento.put(day, 1);
							}
						}
						
					}
					
					Integer total = 0;
					for(int i = 0; i <= 31; i++) {
						Integer quantidade = 0;
						if(quantidadesMonitoramento.get(i) != null) {
							quantidade = quantidadesMonitoramento.get(i);
							total = total + quantidade;
						}
						quantidadeOcorrenciasMesDetalhadoMonitoramento.getQuantidadeOcorrenciasDia().add(quantidade);
						quantidadeOcorrenciasMesDetalhadoMonitoramento.setTotal(total);
					}
					
					total = 0;
					for(int i = 0; i <= 31; i++) {
						Integer quantidade = 0;
						if(quantidadesSeguranca.get(i) != null) {
							quantidade = quantidadesSeguranca.get(i);
							total = total + quantidade;
						}
						quantidadeOcorrenciasMesDetalhadoSeguranca.getQuantidadeOcorrenciasDia().add(quantidade);
						quantidadeOcorrenciasMesDetalhadoSeguranca.setTotal(total);
					}
					
					quantidadesOcorrenciasMesDetalhadoMonitoramento.add(quantidadeOcorrenciasMesDetalhadoMonitoramento);
					quantidadesOcorrenciasMesDetalhadoSeguranca.add(quantidadeOcorrenciasMesDetalhadoSeguranca);
				} catch (Exception e) {

					Rastreabilidade rastreabilidade = new Rastreabilidade();
					rastreabilidade.setFuncao("Gerar relatório mensal - ClienteTipoOcorrencia");
					rastreabilidade.setDataHora(new Date());
					rastreabilidade.setException(e.getMessage());
					rastreabilidade.setComplemento("ID Cliente: " + tipoOcorrencia.getCliente().getId());

					rastreabilidadeDaoImpl.salvarRastreabilidade(rastreabilidade);

					continue;
				}
			}
		}

		Collections.sort(quantidadeOcorrencias, new Comparator<QuantidadeOcorrencias>() {
            @Override
            public int compare(QuantidadeOcorrencias primeiro, QuantidadeOcorrencias segundo) {
                return primeiro.getQuantidadeOcorrencias() > segundo.getQuantidadeOcorrencias() ? -1 : (primeiro.getQuantidadeOcorrencias() < segundo.getQuantidadeOcorrencias()) ? 1 : 0;
            }
        });
		

		List<GrupoEquipamento> gruposEquip = new ArrayList<GrupoEquipamento>();
		List<String> gruposCamera = cameraDaoImpl.listarCamerasGroupNumeroCamera(clienteMes);
		if(gruposCamera != null) {
			for(String grupo : gruposCamera) {
				try {
					GrupoEquipamento grupoEquipamento = new GrupoEquipamento();
					grupoEquipamento.setDescricaoGrupoEquipamento(grupo);
					
					List<Camera> camerasNumero = cameraDaoImpl.listarCamerasPorClienteENumero(clienteMes, grupo);
					
					for(Camera camera : camerasNumero) {
						grupoEquipamento.getHorasInoperantes().add(calcularQuantidadeHorasInoperantesEquipamento(camera, mes, ano));
					}
					
					gruposEquip.add(grupoEquipamento);
				} catch (Exception e) {

					Rastreabilidade rastreabilidade = new Rastreabilidade();
					rastreabilidade.setFuncao("Gerar relatório mensal - GrupoEquipamento");
					rastreabilidade.setDataHora(new Date());
					rastreabilidade.setException(e.getMessage());
					rastreabilidade.setComplemento("Grupo: " + grupo);

					rastreabilidadeDaoImpl.salvarRastreabilidade(rastreabilidade);

					continue;
				}
			}
		}
		
		GrupoEquipamento grupoEquipamentoCFTV = new GrupoEquipamento();
		grupoEquipamentoCFTV.setDescricaoGrupoEquipamento("Sistema de Segurança eletronica: CFTV");
		List<Camera> camerasNumeroCFTV = cameraDaoImpl.listarCameras(clienteMes, TipoCameraEnum.CFTV);
		
		for(Camera camera : camerasNumeroCFTV) {
			grupoEquipamentoCFTV.getHorasInoperantes().add(calcularQuantidadeHorasInoperantesEquipamento(camera, mes, ano));
		}
		
		gruposEquip.add(grupoEquipamentoCFTV);
		
		GrupoEquipamento grupoEquipamentoPerimetral = new GrupoEquipamento();
		grupoEquipamentoPerimetral.setDescricaoGrupoEquipamento("PROTEÇÃO PERIMETRAL");
		List<Camera> camerasNumeroPerimetral = cameraDaoImpl.listarCameras(clienteMes, TipoCameraEnum.PERIMETRAL);
		
		for(Camera camera : camerasNumeroPerimetral) {
			grupoEquipamentoPerimetral.getHorasInoperantes().add(calcularQuantidadeHorasInoperantesEquipamento(camera, mes, ano));
		}
		
		gruposEquip.add(grupoEquipamentoPerimetral);
		
		GrupoEquipamento grupoEquipamentoControleAcesso = new GrupoEquipamento();
		grupoEquipamentoControleAcesso.setDescricaoGrupoEquipamento("SISTEMA DE CONTROLE DE ACESSO");
		List<Camera> camerasNumeroControleAcesso = cameraDaoImpl.listarCameras(clienteMes, TipoCameraEnum.CONTROLE_DE_ACESSO);
		
		for(Camera camera : camerasNumeroControleAcesso) {
			grupoEquipamentoControleAcesso.getHorasInoperantes().add(calcularQuantidadeHorasInoperantesEquipamento(camera, mes, ano));
		}
		
		gruposEquip.add(grupoEquipamentoControleAcesso);
		
		relatorioMensal.setGrupoOcorrencias(converterOcorrenciasEmGrupoOcorrencias(ocorrenciasMensalCliente));
		relatorioMensal.setCliente(converterClienteEmClienteDTO(clienteMes));
		relatorioMensal.setQuantidadeOcorrencias(quantidadeOcorrencias);
		relatorioMensal.setQuantidadesOcorrenciasMesDetalhadoMonitoramento(quantidadesOcorrenciasMesDetalhadoMonitoramento);
		relatorioMensal.setQuantidadesOcorrenciasMesDetalhadoSeguranca(quantidadesOcorrenciasMesDetalhadoSeguranca);
		relatorioMensal.setGruposEquipamento(gruposEquip);
		return relatorioMensal;
	}
	
	private QuantidadeHorasEquipamentoRelatorio calcularQuantidadeHorasInoperantesEquipamento(Camera camera, String mes, String ano) {
		QuantidadeHorasEquipamentoRelatorio quantidadeHoras = new QuantidadeHorasEquipamentoRelatorio();
		quantidadeHoras.setDescricaoEquipamento(camera.getDescricaoCamera());
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 30);
		cal.set(Calendar.MONTH, Integer.parseInt(mes)- 1);
		cal.set(Calendar.YEAR, Integer.parseInt(ano));
		
		Date dataFinal = cal.getTime();
		
		for(int i = 1; i <= 31; i++) {
			Calendar calInicio = Calendar.getInstance();
			calInicio.set(Calendar.DAY_OF_MONTH, (i - 1));
			calInicio.set(Calendar.MONTH, Integer.parseInt(mes) - 1);
			calInicio.set(Calendar.YEAR, Integer.parseInt(ano));
			
			Date dataInicio = calInicio.getTime();
			List<SituacaoCamera> situacoesDia = situacaoCameraDaoImpl.consultarSituacaoCameraData(camera, dataInicio, dataFinal);
			
			long horasDia = 0;
			for(SituacaoCamera situacao : situacoesDia) {
				try {
					Calendar calDesligada = Calendar.getInstance();
					calDesligada.setTime(situacao.getDataHoraDesligada());
					if(calDesligada.get(Calendar.DAY_OF_MONTH) == i) {
						Calendar calLigada = Calendar.getInstance();
						
						if(situacao.getDataHoraLigada() == null) {
							calLigada.set(Calendar.DAY_OF_MONTH, calDesligada.get(Calendar.DAY_OF_MONTH));
							calLigada.set(Calendar.MONTH, calDesligada.get(Calendar.MONTH));
							calLigada.set(Calendar.YEAR, calDesligada.get(Calendar.YEAR));
							calLigada.set(Calendar.HOUR_OF_DAY, 23);
							calLigada.set(Calendar.MINUTE, 59);
						} else {
							calLigada.setTime(situacao.getDataHoraLigada());
							
							if(calDesligada.get(Calendar.DAY_OF_MONTH) != calLigada.get(Calendar.DAY_OF_MONTH)) {
								calLigada.set(Calendar.DAY_OF_MONTH, calDesligada.get(Calendar.DAY_OF_MONTH));
								calLigada.set(Calendar.MONTH, calDesligada.get(Calendar.MONTH));
								calLigada.set(Calendar.YEAR, calDesligada.get(Calendar.YEAR));
								calLigada.set(Calendar.HOUR_OF_DAY, 23);
								calLigada.set(Calendar.MINUTE, 59);
							}
						}
						
						horasDia = horasDia + ((calLigada.getTimeInMillis() - calDesligada.getTimeInMillis()) / 3600000);
					}
				} catch (Exception e) {
					Rastreabilidade rastreabilidade = new Rastreabilidade();
					rastreabilidade.setFuncao("Gerar relatório mensal - SituacaoCamera");
					rastreabilidade.setDataHora(new Date());
					rastreabilidade.setException(e.getMessage());
					rastreabilidade.setComplemento("ID Situacao: " + situacao.getId());

					rastreabilidadeDaoImpl.salvarRastreabilidade(rastreabilidade);

					continue;
				}
			}
			
			quantidadeHoras.setQuantidadeHorasMes(quantidadeHoras.getQuantidadeHorasMes() + horasDia);
			quantidadeHoras.getQuantidadeHorasDia().add(horasDia);
		}
		
		return quantidadeHoras;
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
				try {
					if(ocorrencia.getTipoOcorrencia().getAtivo() && ocorrencia.getTipoOcorrencia().getRelatorioMensal()) {
						OcorrenciaDTO ocorrenciaDto = new OcorrenciaDTO();
						
						ocorrenciaDto.setIdOcorrencia(ocorrencia.getId());
						ocorrenciaDto.setNomeUsuario(ocorrencia.getUsuario().getNome());
						ocorrenciaDto.setIdTipoOcorrencia(ocorrencia.getTipoOcorrencia().getId());
						ocorrenciaDto.setDescTipoOcorrencia(ocorrencia.getTipoOcorrencia().getDescricao());
						ocorrenciaDto.setDataCadastro(formato.format(ocorrencia.getDataCadastro()));
						ocorrencia.setDestacada(ocorrencia.getDestacada());
						
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
				} catch (Exception e) {
					Rastreabilidade rastreabilidade = new Rastreabilidade();
					rastreabilidade.setFuncao("Gerar relatório mensal - converterOcorrenciasEmGrupoOcorrencias");
					rastreabilidade.setDataHora(new Date());
					rastreabilidade.setException(e.getMessage());
					rastreabilidade.setComplemento("ID Ocorrencia: " + ocorrencia.getId());

					rastreabilidadeDaoImpl.salvarRastreabilidade(rastreabilidade);

					continue;
				}
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
	
}
