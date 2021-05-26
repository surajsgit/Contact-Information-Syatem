package com.evolenthealth.contacts.errorhandler;

import java.util.Date;

public class ErrorResonse {
	private Date timestamp;
	private String status;
	private String path;
	private String message;
	
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorResonse(Date timestamp, String status, String path) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.path = path;
	}
	
	
}
