package br.com.rpw.monitoramento.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.rpw.monitoramento.api.dto.TurnoDTO;
import br.com.rpw.monitoramento.api.model.Cliente;
import br.com.rpw.monitoramento.api.model.LogEntry;
import br.com.rpw.monitoramento.api.model.RelatorioMensal;
import br.com.rpw.monitoramento.api.model.RestObject;
import br.com.rpw.monitoramento.api.model.Turno;
import br.com.rpw.monitoramento.api.service.impl.EmailService;
import br.com.rpw.monitoramento.api.service.impl.OcorrenciaService;
import br.com.rpw.monitoramento.api.service.impl.RelatorioService;
import br.com.rpw.monitoramento.api.service.impl.TurnoService;
import br.com.rpw.monitoramento.api.service.impl.UsuarioService;
import br.com.rpw.monitoramento.api.util.TokenUtil;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/relatorios")
public class RelatoriosController {
	
	private static final String JASPER_REPORT_LOGS_KEY = "logsReport";
	
	@Autowired
	private TurnoService turnoService;
	
	@Autowired
	private RelatorioService relatorioService;
	
	@RequestMapping(value="/{idCliente}/mensal/{ano}/{mes}", method = RequestMethod.GET)
	public RestObject consultarTurno(@PathVariable("idCliente") Long idCliente, @PathVariable("ano") String ano, @PathVariable("mes") String mes, @RequestHeader(value="x-acess-token") String token) { 
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
	
	@RequestMapping(value="/relatorios/{idCliente}/{idTurno}")
	public ModelAndView consultarRelatorio(@PathVariable Long idTurno) { 
		
		TurnoDTO turnoDto = turnoService.consultarTurnoDetalhado(idTurno);
		
		JRDataSource dataSource = new JRBeanCollectionDataSource(prepareEntries());

		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("datasource", dataSource);
		parameterMap.put("logTable", dataSource);

		
		return new ModelAndView(JASPER_REPORT_LOGS_KEY, parameterMap);
	}
	
	private static List<LogEntry> prepareEntries() {
		List<LogEntry> items = new ArrayList<LogEntry>();

		items.add(newLogEntry("TypeA", "NameA", "255.255.255.200", new Date(System.currentTimeMillis())));
		items.add(newLogEntry("TypeB", "NameB", "255.255.255.201", new Date(System.currentTimeMillis())));
		items.add(newLogEntry("TypeC", "NameC", "255.255.255.202", new Date(System.currentTimeMillis())));
		items.add(newLogEntry("TypeD", "NameD", "255.255.255.203", new Date(System.currentTimeMillis())));

		return items;
	}

	private static LogEntry newLogEntry(String logType, String name, String ip, Date date) {
		LogEntry logEntry = new LogEntry();
		logEntry.setLogType(logType);
		logEntry.setName(name);
		logEntry.setIp(ip);
		logEntry.setDate(date);

		return logEntry;
	}
	
}
