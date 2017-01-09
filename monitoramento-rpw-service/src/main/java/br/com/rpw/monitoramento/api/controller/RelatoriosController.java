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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.rpw.monitoramento.api.dto.TurnoDTO;
import br.com.rpw.monitoramento.api.model.LogEntry;
import br.com.rpw.monitoramento.api.service.impl.EmailService;
import br.com.rpw.monitoramento.api.service.impl.TurnoService;
import br.com.rpw.monitoramento.api.service.impl.UsuarioService;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/relatorios")
public class RelatoriosController {
	
	private static final String JASPER_REPORT_LOGS_KEY = "logsReport";
	
	@Autowired
	private TurnoService turnoService;
	
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
