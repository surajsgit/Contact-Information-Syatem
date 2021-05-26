package com.evolenthealth.contacts.errorhandler;

public class ContactNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3611417885963284488L;

	public ContactNotFoundException(String message){
		super(message);
	}
	
}
