package com.dueeuro.smtp.service;

import java.util.Properties;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import com.dueeuro.smtp.dto.EmailDto;

@Component
public class EmailSenderImpl implements EmailSender {
	@Autowired Environment enviroment;
	
	@Autowired JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	String username;
	@Value("${spring.mail.password}")
	String password;

	@Bean
	@Override
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername(enviroment.getProperty("spring.mail.username"));
		mailSender.setPassword(enviroment.getProperty("spring.mail.password"));

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");

		return mailSender;
	}

	@Override
	public void inviaEmail(EmailDto emailDto) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(emailDto.getDestinatario());
		message.setSubject(emailDto.getOggetto());
		message.setText(emailDto.getTesto());			
		javaMailSender.send(message);
		
	}

	
	
	
}
