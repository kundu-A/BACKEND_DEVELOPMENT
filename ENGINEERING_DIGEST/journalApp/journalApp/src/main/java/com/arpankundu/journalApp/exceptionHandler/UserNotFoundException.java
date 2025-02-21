package com.arpankundu.journalApp.exceptionHandler;

public class UserNotFoundException extends RuntimeException{
	public UserNotFoundException(String message) {
		super(message);
	}

}
