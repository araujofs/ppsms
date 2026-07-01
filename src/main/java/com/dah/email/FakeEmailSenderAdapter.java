package com.dah.email;

import com.dah.records.EmailMessage;

public class FakeEmailSenderAdapter implements EmailSender {

	@Override
	public void send(EmailMessage message) {
    System.out.println("Email enviado!");
    System.out.println(message);
	}
}
