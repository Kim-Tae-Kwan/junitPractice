package com.ktk.junitPractice.util;

import lombok.RequiredArgsConstructor;

//@RequiredArgsConstructor
public class MailSenderAdapter implements MailSender{

//	private Mail mail;
	
	@Override
	public boolean send() {
//		return mail.sendMail();
		return true;
	}
	
}