package br.com.rpw.monitoramento.api.service;

import org.apache.commons.mail.EmailException;

public interface IEmailService {
	void enviarEmail(String to, String subject, String message) throws EmailException;
}
