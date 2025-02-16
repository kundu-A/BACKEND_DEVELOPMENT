package com.arpankundu.journalApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.arpankundu.journalApp.models.Email;
import com.arpankundu.journalApp.repository.EmailRepository;

@Service
public class MailOTPService {

	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired
	EmailRepository emailRepo;
	
	private static final String fromMail = "kunduarpan43@gmail.com";
	
	public void sendeEmail(Email emailRequest) {
		SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailRequest.getTo());
        message.setSubject(emailRequest.getSubject());
        message.setText(emailRequest.getBody());
        message.setFrom(fromMail);
        emailRepo.save(emailRequest);
        javaMailSender.send(message);
		
	}

}
