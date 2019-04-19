package com.ceiba.parqueadero.util;

public class ValidarVehiculoException extends Exception {
	
	
	private static final long serialVersionUID = 1L;



	 private final int status;

	  public ValidarVehiculoException(String message, int status) {
	    super(message);
	    this.status = status;
	  }

	  public int getStatus() {
	    return status;
	  }

}
