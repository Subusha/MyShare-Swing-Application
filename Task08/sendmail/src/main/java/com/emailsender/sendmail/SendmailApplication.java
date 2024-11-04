package com.emailsender.sendmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SendmailApplication {

	@Autowired
	private sendEmail senderservice;

	public static void main(String[] args) {
		//SpringApplication.run(SendmailApplication.class, args);
	}




	@EventListener(ApplicationReadyEvent.class)
	public void sendEmail(){
		senderservice.send(EmailForm.email,EmailForm.subject,EmailForm.msg);

	}

}


