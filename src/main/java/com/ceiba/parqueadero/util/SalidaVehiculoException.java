package com.ceiba.parqueadero.util;

public class SalidaVehiculoException extends Exception {

	private static final long serialVersionUID = 1L;
	private final int status;

	public SalidaVehiculoException(String message, int status) {
		super(message);
		this.status = status;
	}

	public int getStatus() {
		return status;
	}
}
