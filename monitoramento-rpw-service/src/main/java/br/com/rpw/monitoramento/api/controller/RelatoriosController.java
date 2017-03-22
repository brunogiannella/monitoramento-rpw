package br.com.rpw.monitoramento.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rpw.monitoramento.api.dto.TurnoDTO;
import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.RelatorioMensal;
import br.com.rpw.monitoramento.api.model.RestObject;
import br.com.rpw.monitoramento.api.service.impl.RelatorioService;
import br.com.rpw.monitoramento.api.service.impl.TurnoService;
import br.com.rpw.monitoramento.api.util.TokenUtil;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/relatorios")
public class RelatoriosController {
	
	@Autowired
	private TurnoService turnoService;
	
	@Autowired
	private RelatorioService relatorioService;
	
	@RequestMapping(value="/{idCliente}/mensal/{ano}/{mes}", method = RequestMethod.GET)
	public RestObject gerarRelatorioMensal(@PathVariable("idCliente") Long idCliente, @PathVariable("ano") String ano, @PathVariable("mes") String mes, @RequestHeader(value="x-acess-token") String token) { 
		try {
			
			if(TokenUtil.simpleValidToken(token)) {
				Cliente cliente = new Cliente();
				cliente.setId(idCliente);
				
				RelatorioMensal relatorio = relatorioService.consultarRelatorioMensal(cliente, mes, ano);
				
				if(relatorio == null) {
					return new RestObject(200, true, "Ocorreu um problema ao gerar o relatório.", null);
				}
				
				return new RestObject(200, true, "Relatório gerado com sucesso", relatorio);
			} else {
				return new RestObject(401, false, "Token inválido.", null);
			}
			
			
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um problema ao gerar o relatório: " + e.getMessage(), null);
		}
	}
	
	@RequestMapping(value="/diario/turno/{id}", method = RequestMethod.GET)
	public RestObject gerarRelatorioDiario(@PathVariable("id") Long idTurno, @RequestHeader(value="x-acess-token") String token) { 
		try {
			
			if(TokenUtil.simpleValidToken(token)) {
				TurnoDTO turno = turnoService.consultarTurnoDetalhado(idTurno, true);
				
				if(turno == null) {
					return new RestObject(200, true, "O turno informado não existe.", null);
				}
								
				return new RestObject(200, true, "Turno consultado com sucesso", turno);
			} else {
				return new RestObject(401, false, "Token inválido.", null);
			}
			
		} catch(Exception e) {
			return new RestObject(500, false, "Ocorreu um erro na consulta: " + e.getMessage(), null);
		}
	}
	
}
