package com.arpankundu.journalApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.arpankundu.journalApp.exceptionHandler.EmailSendingException;
import com.arpankundu.journalApp.models.Email;
import com.arpankundu.journalApp.models.MailOTP;
import com.arpankundu.journalApp.repository.EmailRepository;
import com.arpankundu.journalApp.repository.UserRepo;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired
	EmailRepository emailRepo;
	
	@Autowired
	MailOTPService mailService;
	
	@Autowired
	UserRepo userRepo;
	
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
	        MimeMessage message = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
	        
	        String otp = mailService.generateOTP(mailRequest.getEmail());
	        String name="User";
	        if(userRepo.existsByEmail(mailRequest.getEmail()))
	        	name=userRepo.findUsersByEmail(mailRequest.getEmail()).getName();

	        String htmlContent = "<div style='font-family: Arial, sans-serif; padding: 15px; border: 1px solid #ddd; border-radius: 10px;'>"
	                            + "<h2 style='color: #2c3e50;'>One-Time Password (OTP) Verification</h2>"
	                            + "<p style='font-size: 16px;'>Dear "+name+",</p>"
	                            + "<p style='font-size: 16px;'>Your OTP for email verification is: "
	                            + "<strong style='color: #e74c3c; font-size: 20px;'>" + otp + "</strong></p>"
	                            + "<p style='font-size: 14px; color: #7f8c8d;'>This OTP is valid for 1 minutes. Do not share it with anyone.</p>"
	                            + "<p style='margin-top: 20px;'>Regards,<br><strong>Journal-Application Team</strong></p>"
	                            + "</div>";

	        helper.setTo(mailRequest.getEmail());
	        helper.setSubject("One-Time Password (OTP) Verification");
	        helper.setText(htmlContent, true);
	        helper.setFrom(fromMail);
	        
	        javaMailSender.send(message);
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	        throw new EmailSendingException("Failed to send OTP email");
	    }
	}
	@Async
	public void welcomeEmail(String mailTo) {
	    try {
	        MimeMessage message = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

	        String name=userRepo.findUsersByEmail(mailTo).getName();
	        helper.setTo(mailTo);
	        helper.setSubject(getEmailSubject());
	        helper.setText(getEmailBody(name), true);  // 'true' enables HTML rendering
	        helper.setFrom(fromMail);

	        javaMailSender.send(message);
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	        throw new EmailSendingException("Failed to send Welcome email");
	    }
	}

	public String getEmailBody(String name) { 
	    return "<div style='font-family: Arial, sans-serif; padding: 20px; border: 1px solid #ddd; border-radius: 10px;'>"
	            + "<h2 style='color: #2c3e50;'>Welcome to the Journal Application!</h2>"
	            + "<p style='font-size: 16px;'>Dear "+name+",</p>"
	            + "<p style='font-size: 16px;'>Your registration with the <strong>Journal Application</strong> has been successfully completed!</p>"
	            + "<p style='color: #e74c3c; font-size: 14px;'>Please note, this is a system-generated email—no replies will be monitored.</p>"
	            + "<p style='margin-top: 20px;'>Thank you for joining us!</p>"
	            + "<p><strong>Best regards,</strong><br>Team Journal Application</p>"
	            + "</div>";
	}

	public String getEmailSubject() {
	    return "🎉 Registration Successful - Journal Application";
	}
	
	@Async
	public void alertEmail(String email) {
		try {
	        MimeMessage message = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

	        String otp = mailService.generateOTP(email);
	        String name=userRepo.findUsersByEmail(email).getName();
	        
	        helper.setTo(email);
	        helper.setSubject(alterEmailSubject());
	        helper.setText(alterEmailBody(otp,name), true);  // 'true' enables HTML rendering
	        helper.setFrom(fromMail);

	        javaMailSender.send(message);
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	        throw new EmailSendingException("Failed to send Welcome email");
	    }
	}
	
	public String alterEmailBody(String otp,String name) {
		return "<div style='font-family: Arial, sans-serif; padding: 20px; border: 1px solid #ddd; border-radius: 10px; background-color: #f9f9f9;'>"
	            + "<h2 style='color: #d63031;'>⚠ Password Reset Attempt ⚠</h2>"
	            + "<p style='font-size: 16px; color: #2c3e50;'>Dear "+name+",</p>"
	            + "<p style='font-size: 16px;'>We noticed a request to reset your password using the <strong>Forgot Password</strong> option.</p>"
	            + "<p style='font-size: 16px;'>If this was you, please proceed with the next steps.</p>"
	            + "<p style='color: #e74c3c; font-size: 14px;'>🚨 If you did not request this, please ignore this email and ensure your account security.</p>"
	            + "<p style='font-size: 16px;'>Your OTP for password reset is: "
	            + "<strong style='color: #e74c3c; font-size: 20px;'>" + otp + "</strong></p>"
	            + "<p style='font-size: 14px; color: #7f8c8d;'>This OTP is valid for 1 minute. Do not share it with anyone.</p>"
	            + "<p style='margin-top: 20px;'>Stay secure and vigilant!</p>"
	            + "<p><strong>Best regards,</strong><br>Team Journal Application</p>"
	            + "</div>";
	}
	
	public String alterEmailSubject() {
	    return "🔐 Password Reset Alert – Your OTP is on the Way!";
	}
	
	@Async
	public void changePasswordOTP(String email) {
		try {
	        MimeMessage message = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
	        
	        String otp = mailService.generateOTP(email);
	        String name=userRepo.findUsersByEmail(email).getName();

	        String htmlContent = "<div style='font-family: Arial, sans-serif; padding: 15px; border: 1px solid #ddd; border-radius: 10px;'>"
	                + "<h2 style='color: #2c3e50;'>Change Password - OTP Verification</h2>"
	                + "<p style='font-size: 16px;'>Dear "+name+",</p>"
	                + "<p style='font-size: 16px;'>You have requested to change your password. Use the OTP below to proceed:</p>"
	                + "<p style='font-size: 16px;'><strong style='color: #e74c3c; font-size: 20px;'>" + otp + "</strong></p>"
	                + "<p style='font-size: 14px; color: #7f8c8d;'>This OTP is valid for 1 minute. Do not share it with anyone.</p>"
	                + "<p style='margin-top: 20px;'>If you did not request this change, please ignore this email.</p>"
	                + "<p style='margin-top: 20px;'>Regards,<br><strong>Journal-Application Team</strong></p>"
	                + "</div>";

	        helper.setTo(email);
	        helper.setSubject("One-Time Password (OTP) Verification");
	        helper.setText(htmlContent, true);
	        helper.setFrom(fromMail);
	        
	        javaMailSender.send(message);
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	        throw new EmailSendingException("Failed to send OTP email");
	    }
	}
	
	@Async
	public void successfullPasswordChangingMail(String email) {
		try {
	        MimeMessage message = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

	        String name=userRepo.findUsersByEmail(email).getName();
	        
	        String htmlContent = "<div style='font-family: Arial, sans-serif; padding: 15px; border: 1px solid #ddd; border-radius: 10px;'>"
	                + "<h2 style='color: #2c3e50;'>Password Changed Successfully</h2>"
	                + "<p style='font-size: 16px;'>Dear "+name+",</p>"
	                + "<p style='font-size: 16px;'>You have successfully changed your password. If this was you, no further action is needed.</p>"
	                + "<p style='font-size: 14px; color: #7f8c8d;'>If you did not request this change, please reset your password immediately or contact support.</p>"
	                + "<p style='margin-top: 20px;'>Regards,<br><strong>Journal-Application Team</strong></p>"
	                + "</div>";

	        helper.setTo(email);
	        helper.setSubject("Your Request is fullfilled now!!");
	        helper.setText(htmlContent, true);
	        helper.setFrom(fromMail);
	        
	        javaMailSender.send(message);
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	        throw new EmailSendingException("Failed to send OTP email");
	    }
	}
}
