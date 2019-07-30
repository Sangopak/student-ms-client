package com.sango.microservices.client.exception;

import java.util.Date;

public class CommonExceptionResponse {

	private Date timestamp;
	private String errorMessage;
	private String errorDetails;
	
	public CommonExceptionResponse(Date timestamp, String errorMessage, String errorDetails) {
		super();
		this.timestamp = timestamp;
		this.errorMessage = errorMessage;
		this.errorDetails = errorDetails;
	}

	public CommonExceptionResponse() {
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getErrorDetails() {
		return errorDetails;
	}
	
	

}
