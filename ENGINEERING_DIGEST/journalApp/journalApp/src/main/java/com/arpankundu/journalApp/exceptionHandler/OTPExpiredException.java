package com.arpankundu.journalApp.exceptionHandler;

public class OTPExpiredException extends RuntimeException{

	public OTPExpiredException(String message) {
        super(message);
    }
}
