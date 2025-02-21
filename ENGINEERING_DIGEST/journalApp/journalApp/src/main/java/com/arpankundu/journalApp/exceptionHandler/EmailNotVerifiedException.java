package com.arpankundu.journalApp.exceptionHandler;

public class EmailNotVerifiedException extends RuntimeException{
	 public EmailNotVerifiedException(String message) {
	        super(message);
	    }
}
