package com.dueeuro.smtp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dueeuro.smtp.dto.EmailDto;
import com.dueeuro.smtp.service.EmailSender;

@RestController
@RequestMapping(path = "/api/v1")
public class MailController {

	@Autowired
	public EmailSender emailSender;

	@PostMapping("/smtp/invia")
	public void sendSimpleMessage(@Valid @RequestBody EmailDto emailDto) {
		//MODIFICARE IL METODO SOTTO PER LO SCOPO SPECIFICO;
		emailSender.inviaEmail(emailDto);

	}
}
