package com.dueeuro.smtp.service;

import org.springframework.mail.javamail.JavaMailSender;

import com.dueeuro.smtp.dto.EmailDto;

public interface EmailSender {

	public JavaMailSender getJavaMailSender();
	
	public void inviaEmail(EmailDto emailDto);

}
