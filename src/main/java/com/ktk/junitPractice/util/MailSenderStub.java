package com.ktk.junitPractice.util;

import org.springframework.stereotype.Component;

@Component
public class MailSenderStub implements MailSender{

	@Override
	public boolean send() {
		return true;
	}
	
}
