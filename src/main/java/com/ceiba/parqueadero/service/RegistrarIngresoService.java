package com.ceiba.parqueadero.service;

import com.ceiba.parqueadero.util.RestResponse;

public interface RegistrarIngresoService {

	RestResponse registrarIngresoVehiculo(String vehiculoJson);
	
}
