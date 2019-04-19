package com.ceiba.parqueadero.util;

public class Response {
	
	private Integer responseCode;
	private String message;

	public Response(Integer responseCode) {
		
		this.responseCode = responseCode;
	}

	public Response(Integer responseCode, String message) {
		
		this.responseCode = responseCode;
		this.message = message;
	}

	public Integer getResponseCode() {

		return responseCode;
	}

	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	

}
