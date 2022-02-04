package edu.institution;

public class LinkedInException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LinkedInException() {
		super();
	}
	
	public LinkedInException(String message) {
		super(message);
	}
	
	public LinkedInException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public LinkedInException(Throwable cause) {
		super(cause);
	}
	
	public LinkedInException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super (message, cause, enableSuppression, writableStackTrace);
	}
	
}
