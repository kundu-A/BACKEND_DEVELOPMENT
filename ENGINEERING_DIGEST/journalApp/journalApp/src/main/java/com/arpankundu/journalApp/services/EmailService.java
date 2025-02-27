package com.arpankundu.journalApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.arpankundu.journalApp.exceptionHandler.EmailSendingException;
import com.arpankundu.journalApp.models.Email;
import com.arpankundu.journalApp.models.MailOTP;
import com.arpankundu.journalApp.repository.EmailRepository;

@Service
public class EmailService {

	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired
	EmailRepository emailRepo;
	
	@Autowired
	MailOTPService mailService;
	
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
	@Async
	public void sendOTP(MailOTP mailRequest) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(mailRequest.getEmail());
			message.setSubject("One-Time-Password to validate your mail");
			message.setText("Your OTP is: "+mailService.generateOTP(mailRequest.getEmail()));
			message.setFrom(fromMail);
			javaMailSender.send(message);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			throw new EmailSendingException("Failed to send OTP email");
		}
		
	}
	@Async
	public void welcomeEmail(String mailTo) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(mailTo);
			message.setSubject(getEmailSubject());
			message.setText(getEmailBody());
			message.setFrom(fromMail);
			javaMailSender.send(message);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			throw new EmailSendingException("Failed to send OTP email");
		}
	}
	public String getEmailBody() {
		return "Your registration with Jouranl Application has been successfully completed!\n" +
                "\n" +
                "Please note, this is a system-generated email—no replies will be monitored.\n" +
                "\n" +
                "Thank you for joining us!\n" +
                "\n" +
                "Best regards,\n" +
                "Team Journal Application";
	}
	public String getEmailSubject()
    {
        return "Registration Successful - Journal Application";
    }

}
