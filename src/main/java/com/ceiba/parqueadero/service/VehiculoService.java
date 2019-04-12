package com.ceiba.parqueadero.service;

import com.ceiba.parqueadero.model.Vehiculo;

public interface VehiculoService {

	/**
	 * Guarda vehiculo
	 * @param vehiculo
	 * @return
	 */
	Vehiculo save(Vehiculo vehiculo);
	/**
	 * Borra Vehiculo por id
	 * @param id
	 */
	void deleteVehiculoById(Long id);
	
	/**
	 * buscar vehiculo por placa
	 * @param vehiculo
	 * @return
	 */
	Vehiculo findByPlaca(Vehiculo vehiculo);
	
	/**
	 * Valida y corrige el Json
	 * @param vehiculoJson
	 * @return
	 */
	Vehiculo convertirJsonAVehiculo(String vehiculoJson) throws Exception;
}
