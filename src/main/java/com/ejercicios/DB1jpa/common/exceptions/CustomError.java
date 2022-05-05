package com.ejercicios.DB1jpa.common.exceptions;

import java.util.Date;

public class CustomError {
	private Date timestamp;
	private String mensaje;
	private String httpCodeMessage;

	public CustomError(Date timestamp, String message, String details, String httpCodeMessage) {
		super();
		this.timestamp = timestamp;
		this.mensaje = message;
		this.httpCodeMessage=httpCodeMessage;
	}

	public String getHttpCodeMessage() {
		return httpCodeMessage;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMensaje() {
		return mensaje;
	}

}
