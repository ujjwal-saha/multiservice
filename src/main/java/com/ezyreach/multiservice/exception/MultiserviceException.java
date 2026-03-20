package com.ezyreach.multiservice.exception;

public class MultiserviceException extends RuntimeException{
    private static final long serialVersionUID = 1639470787868405344L;
	public MultiserviceException(String message) {
		super(message);
	}
	
	public MultiserviceException(String message,Throwable th) {
		super(message,th);
	}
	
	public MultiserviceException(Throwable th) {
		super(th);
	}

}
