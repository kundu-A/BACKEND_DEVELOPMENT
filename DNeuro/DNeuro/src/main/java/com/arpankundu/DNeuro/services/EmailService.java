package com.arpankundu.DNeuro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.arpankundu.DNeuro.components.OTP;
import com.arpankundu.DNeuro.repository.PublicRepo;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private PublicRepo userRepo;
	
	@Autowired
	private OTPService otpService;
	
	private static final String fromMail = "kunduarpan43@gmail.com";
	
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
	        throw new RuntimeException("Failed to send Welcome email");
	    }
	}
	public String getEmailBody(String name) { 
	    return "<div style='font-family: Arial, sans-serif; padding: 20px; border: 1px solid #ddd; border-radius: 10px;'>"
	            + "<h2 style='color: #2c3e50;'>Welcome to the Journal Application!</h2>"
	            + "<p style='font-size: 16px;'>Dear "+name+",</p>"
	            + "<p style='font-size: 16px;'>Your registration with the <strong>Journal Application</strong> has been successfully completed!</p>"
	            + "<p style='color: #e74c3c; font-size: 14px;'>Please note, this is a system-generated email—no replies will be monitored.</p>"
	            + "<p style='margin-top: 20px;'>Thank you for joining us!</p>"
	            + "<p><strong>Best regards,</strong><br>Team DNeuro</p>"
	            + "</div>";
	}
	public String getEmailSubject() {
	    return "🎉 Registration Successful - DNeuro Application";
	}
	
	public void sendOTP(OTP request) {
	    try {
	        MimeMessage message = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
	        
	        String otp = otpService.generateOTP(request.getEmail());
	        String name="User";
	        if(userRepo.existsByEmail(request.getEmail()))
	        	name=userRepo.findUsersByEmail(request.getEmail()).getName();

	        String htmlContent = "<div style='font-family: Arial, sans-serif; padding: 15px; border: 1px solid #ddd; border-radius: 10px;'>"
	                            + "<h2 style='color: #2c3e50;'>One-Time Password (OTP) Verification</h2>"
	                            + "<p style='font-size: 16px;'>Dear "+name+",</p>"
	                            + "<p style='font-size: 16px;'>Your OTP for email verification is: "
	                            + "<strong style='color: #e74c3c; font-size: 20px;'>" + otp + "</strong></p>"
	                            + "<p style='font-size: 14px; color: #7f8c8d;'>This OTP is valid for 1 minutes. Do not share it with anyone.</p>"
	                            + "<p style='margin-top: 20px;'>Regards,<br><strong>Journal-Application Team</strong></p>"
	                            + "</div>";

	        helper.setTo(request.getEmail());
	        helper.setSubject("One-Time Password (OTP) Verification");
	        helper.setText(htmlContent, true);
	        helper.setFrom(fromMail);
	        
	        javaMailSender.send(message);
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	        throw new RuntimeException("Failed to send OTP email");
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
	        throw new RuntimeException("Failed to send OTP email");
	    }
	}
}
