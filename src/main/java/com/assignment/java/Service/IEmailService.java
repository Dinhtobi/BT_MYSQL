package com.assignment.java.Service;

import java.io.UnsupportedEncodingException;

import jakarta.mail.MessagingException;

public interface IEmailService {
	void sendRemoveEmail(int id) throws UnsupportedEncodingException, MessagingException;
	void sendVerificationEmail(int id, String siteUrl) throws UnsupportedEncodingException, MessagingException;
}
