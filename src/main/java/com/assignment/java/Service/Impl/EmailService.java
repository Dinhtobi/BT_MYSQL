package com.assignment.java.Service.Impl;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.assignment.java.Entities.User;
import com.assignment.java.Exception.NotFoundException;
import com.assignment.java.Repository.UserRepository;
import com.assignment.java.Service.IEmailService;
import com.assignment.java.Utils.AppConstants;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService implements IEmailService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public void sendRemoveEmail(int id) throws UnsupportedEncodingException, MessagingException, MailException, NotFoundException {
		User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
		String toAddress = user.getEmail();
		String fromAddress = AppConstants.MAIL_SERVER;
		String senderName = "Final Java";
		String subject = "Notify";
		String content = "Dear [[name]],<br>"
		        + "You didn't verify account, so we removed your account!:<br>"
		        + "Thank you,<br>"
		        + "Final Java.";

		MimeMessage message =mailSender.createMimeMessage();
		MimeMessageHelper helper =new MimeMessageHelper(message);
		
		helper.setFrom(fromAddress,senderName);
		helper.setTo(toAddress);
		helper.setSubject(subject);
		
		content = content.replace("[[name]]", user.getName());
		
		helper.setText(content,true);
		mailSender.send(message);
		
		
	}

	@Override
	public void sendVerificationEmail(int id, String siteUrl) throws UnsupportedEncodingException, MessagingException {
		User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
		String toAddress = user.getEmail();
		String fromAddress = "dinhnguyen2002asd@gmail.com";
		String senderName = "Final Java";
		String subject = "Please verify your registration";
		String content = "Dear [[name]],<br>"
		        + "Please click the link below to verify your registration:<br>"
		        + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
		        + "Thank you,<br>"
		        + "Final Java.";

		MimeMessage message =mailSender.createMimeMessage();
		MimeMessageHelper helper =new MimeMessageHelper(message);
		
		helper.setFrom(fromAddress,senderName);
		helper.setTo(toAddress);
		helper.setSubject(subject);
		
		content = content.replace("[[name]]", user.getName());
		String verifyUrl = siteUrl + "/verify?code=" + user.getVerificationCode()+"&id=" +user.getId();
		content = content.replace("[[URL]]" , verifyUrl);
		
		helper.setText(content,true);
		mailSender.send(message);
	}

	
}
