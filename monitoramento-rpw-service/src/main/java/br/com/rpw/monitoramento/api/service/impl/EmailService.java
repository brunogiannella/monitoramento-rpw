package br.com.rpw.monitoramento.api.service.impl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.mail.EmailException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rpw.monitoramento.api.service.IEmailService;

@Service
@Transactional
public class EmailService implements IEmailService {

	@Override
	public void enviarEmail(String to, String subject, String messageSend) throws EmailException {
//		SimpleEmail email = new SimpleEmail();
//		email.setHostName("smtp.mobinit.com.br");
//		email.setSmtpPort(587);
//		email.setAuthentication("bruno.melo@mobinit.com.br", "eyd6bj7c");
//		email.addTo(to, to); //destinatário
//		email.setFrom("bruno.melo@mobin.com.br", "RPW Monitoramento"); // remetente
//		email.setSubject(subject); // assunto do e-mail
//		email.setMsg(message); //conteudo do e-mail
//		email.send(); //envia o e-mail
		
		final String username = "bruno.giannellam@gmail.com";
		final String password = "#Idkfa1406!@";

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("bruno.giannellam@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(messageSend);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	
}
