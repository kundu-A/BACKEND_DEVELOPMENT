package com.arpankundu.journalApp.exceptionHandler;

public class InvalidOTPException extends RuntimeException{

	public InvalidOTPException(String message) {
        super(message);
    }
}
