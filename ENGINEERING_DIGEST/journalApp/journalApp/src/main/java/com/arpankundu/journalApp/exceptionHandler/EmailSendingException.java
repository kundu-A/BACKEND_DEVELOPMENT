package com.arpankundu.journalApp.exceptionHandler;

public class EmailSendingException extends RuntimeException{

	public EmailSendingException(String message) {
		super(message);
	}
}
